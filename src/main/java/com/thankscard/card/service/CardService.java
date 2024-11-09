package com.thankscard.card.service;

import com.thankscard.card.dto.*;
import com.thankscard.member.domain.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface CardService {

    // 로그인 정보 불필요
    // 카드 작성
    CardSendResponseDTO createCard(CardRequestDTO cardRequestDTO, MultipartFile image, User sendUser) throws IOException;
    // 카드 간략 조회
    CardSimpleResponseDTO getCardInfoByCardId(String cardId, User recvUser);
    // 카드 상세 조회
    CardDetailResponseDTO getCardDetailByCardId(String cardId, User recvUser);


    // 로그인 정보 필요
    // 주고 받은 카드 개수 조회
    CardCountResponseDTO getAllCardCountByUser(User user);

    // 받은 전체 카드 목록 조회
    List<CardSimpleResponseDTO> getAllCardByUserId(User user );

    // 연도별 받은 카드 목록 조회
    List<CardSimpleResponseDTO> getAllCardsByUserAndYear(User user, Integer year);

    // 카드 수신
    void assignCardByUser(User user, String cardId);
}
