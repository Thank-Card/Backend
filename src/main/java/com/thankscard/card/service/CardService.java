package com.thankscard.card.service;

import com.thankscard.card.domain.Card;
import com.thankscard.card.dto.CardDetailResponseDTO;
import com.thankscard.card.dto.CardRequestDTO;
import com.thankscard.card.dto.CardSendResponseDTO;
import com.thankscard.card.dto.CardSimpleResponseDTO;

public interface CardService {

    // 로그인 정보 불필요
    // 카드 작성
    CardSendResponseDTO createCard(CardRequestDTO cardRequestDTO, Long userId);
    // 카드 간략 조회
    CardSimpleResponseDTO getCardInfoByCardId(String cardId, Long userId);
    // 카드 상세 조회
    CardDetailResponseDTO getCardDetailByCardId(String cardId, Long userId);


    // 로그인 정보 필요

}
