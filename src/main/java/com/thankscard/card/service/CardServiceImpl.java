package com.thankscard.card.service;

import com.thankscard.card.domain.Card;
import com.thankscard.card.domain.CardImage;
import com.thankscard.card.dto.*;
import com.thankscard.card.exception.CardException;
import com.thankscard.card.exception.CardStatus;
import com.thankscard.card.repository.CardImageRepository;
import com.thankscard.card.repository.CardRepository;
import com.thankscard.global.api.code.ErrorStatus;
import com.thankscard.global.s3.service.S3UploadService;
import com.thankscard.member.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;
    private final CardImageRepository cardImageRepository;
    private final S3UploadService s3UploadService;
    private final String dirPath = "image";

    // 로그인 정보 불필요
    // 카드 작성
    @Override
    public CardSendResponseDTO createCard(CardRequestDTO cardRequestDTO, MultipartFile image, User sendUser) throws IOException {

        // 로그인 확인
        if (sendUser == null) {
            throw new CardException(ErrorStatus.TOKEN_USER_UNAUTHORIZED);
        }

        // 사용자 이미지 저장
        String imgPath = null;
        if (image != null) {
            imgPath = s3UploadService.uploadImage(image, dirPath);
        }

        // 카드 이미지 확인
        CardImage cardImage = cardImageRepository.findById(cardRequestDTO.cardImageId())
                .orElseThrow(() -> new CardException(CardStatus.CARD_IMAGE_NOT_FOUND));


        Card card = Card.builder()
                .sendUser(sendUser)
                .recvTempUser(cardRequestDTO.recvTempUser())
                .cardImage(cardImage)
                .userImage(imgPath)
                .content(cardRequestDTO.content())
                .build();

        Card savedCard = cardRepository.save(card);
        
        return CardSendResponseDTO.builder()
                .id(savedCard.getId())
                .build();
    }

    // 카드 간략 조회
    @Override
    public CardSimpleResponseDTO getCardInfoByCardId(String cardId, User recvUser) {

        Card card = cardRepository.findById(cardId)
                .orElseThrow(() -> new CardException(CardStatus.CARD_NOT_FOUND));

        if (card.getRecvUser() != null) {

            // 로그인 확인
            if (recvUser == null) {
                throw new CardException(ErrorStatus.TOKEN_USER_UNAUTHORIZED); // 401
            }

            if (!recvUser.equals(card.getRecvUser())) {
                throw new CardException(ErrorStatus.TOKEN_USER_UNAUTHORIZED); // 403
            }
        }

        return CardSimpleResponseDTO.from(card);
    }

    // 카드 상세 조회
    @Override
    public CardDetailResponseDTO getCardDetailByCardId(String cardId, User recvUser) {

        Card card = cardRepository.findById(cardId)
                .orElseThrow(() -> new CardException(CardStatus.CARD_NOT_FOUND));


        if (card.getRecvUser() != null) {

            // 로그인 확인
            if (recvUser == null) {
                throw new CardException(ErrorStatus.TOKEN_USER_UNAUTHORIZED); // 401
            }

            if (!recvUser.equals(card.getRecvUser())) {
                throw new CardException(ErrorStatus.TOKEN_USER_UNAUTHORIZED); // 403
            }
        }

        return CardDetailResponseDTO.from(card);
    }

    // 카드 주고받은 개수 조회
    @Override
    public CardCountResponseDTO getAllCardCountByUser(User user) {

        if (user == null) {
            throw new CardException(ErrorStatus.TOKEN_USER_UNAUTHORIZED); // 401
        }

        int sendCount = cardRepository.countBySendUser(user);
        int recvCount = cardRepository.countByRecvUser(user);

        return CardCountResponseDTO.builder()
                .sendCount(sendCount)
                .recvCount(recvCount)
                .build();
    }

    // 받은 모든 카드 조회
    @Override
    public List<CardSimpleResponseDTO> getAllCardByUserId(User user) {

        if (user == null) {
            throw new CardException(ErrorStatus.TOKEN_USER_UNAUTHORIZED); // 401
        }

        List<Card> cardList = cardRepository.findAllByRecvUser(user);

        return cardList.stream()
                .map(CardSimpleResponseDTO::from)
                .collect(Collectors.toList());
    }

    // 연도별 받은 모든 카드 조회
    @Override
    public List<CardSimpleResponseDTO> getAllCardsByUserAndYear(User user, Integer year) {

        if (user == null) {
            throw new CardException(ErrorStatus.TOKEN_USER_UNAUTHORIZED); // 401
        }

        List<Card> cardList = cardRepository.findAllByRecvUserAndYear(user, year);

        return cardList.stream()
                .map(CardSimpleResponseDTO::from)
                .collect(Collectors.toList());
    }

    @Override
    public void assignCardByUser(User user, String cardId) {

        if (user == null) {
            throw new CardException(ErrorStatus.TOKEN_USER_UNAUTHORIZED); //401
        }

        if (cardId == null) {
            throw new CardException(CardStatus.CARD_NOT_FOUND);
        }

        Card card = cardRepository.findById(cardId)
                .orElseThrow(() -> new CardException(CardStatus.CARD_NOT_FOUND));

        card.assignToRecvUser(user);
        cardRepository.save(card);
    }
}
