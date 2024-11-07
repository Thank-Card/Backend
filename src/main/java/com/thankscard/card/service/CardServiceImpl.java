package com.thankscard.card.service;

import com.thankscard.card.domain.Card;
import com.thankscard.card.dto.CardDetailResponseDTO;
import com.thankscard.card.dto.CardRequestDTO;
import com.thankscard.card.dto.CardSendResponseDTO;
import com.thankscard.card.dto.CardSimpleResponseDTO;
import com.thankscard.card.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;

    // 로그인 정보 불필요
    // 카드 작성
    @Override
    public CardSendResponseDTO createCard(CardRequestDTO cardRequestDTO, Long userId) {

        Card card = new Card();

        String sendUser = String.valueOf(userId);

        if (userId == null) {
            // 401
            return null;
        }

        
        
        return CardSendResponseDTO.builder().build();
    }

    // 카드 간략 조회
    @Override
    public CardSimpleResponseDTO getCardInfoByCardId(String cardId, Long userId) {

        Card card = cardRepository.findById(cardId).orElse(null);

        if (card == null) {
            return null;
            // ID에 해당하는 카드 없음 예외 처리 404
        }

        if (card.getRecvUser() != null) {

            // 권한 없음 예외 처리 401 or 403
            if (userId == null) {
                return null;
                // 401
            }

            if (!userId.equals(card.getRecvUser())) {
                return null;
                // 403
            }
            return null;

        }

        return CardSimpleResponseDTO.from(card);
    }

    // 카드 상세 조회
    @Override
    public CardDetailResponseDTO getCardDetailByCardId(String cardId, Long userId) {

        Card card = cardRepository.findById(cardId).orElse(null);

        if (card == null) {
            return null;
            // ID에 해당하는 카드 없음 예외 처리 404
        }

        if (card.getRecvUser() != null) {

            // 권한 없음 예외 처리 401 or 403
            if (userId == null) {
                return null;
                // 401
            }

            if (!userId.equals(card.getRecvUser())) {
                return null;
                //403
            }
            return null;

        }

        return CardDetailResponseDTO.from(card);
    }
}
