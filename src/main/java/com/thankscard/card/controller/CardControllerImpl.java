package com.thankscard.card.controller;

import com.thankscard.card.dto.*;
import com.thankscard.card.service.CardService;
import com.thankscard.global.api.GlobalApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cards")
public class CardControllerImpl implements CardController {

    private final CardService cardService;

    // 로그인 정보 불필요
    // 카드 전송
    @Override
    @PostMapping
    public GlobalApiResponse<CardSendResponseDTO> sendCard(@RequestBody CardRequestDTO cardRequestDTO) {

        return null;
    }

    // 카드 간략 조회(작성 년도, 이미지, 보낸이)
    @Override
    @GetMapping("/{cardId}/simple")
    public GlobalApiResponse<CardSimpleResponseDTO> getCardInfoByCardId(@PathVariable String cardId) {

        CardSimpleResponseDTO cardList = null;

        return GlobalApiResponse.onSuccess(cardList);
    }

    // 카드 상세 조회(이미지, 작성일, 내용, 보낸이)
    @Override
    @GetMapping("/{cardId}/detail")
    public GlobalApiResponse<CardDetailResponseDTO> getCardDetailByCardId(@PathVariable String cardId) {
        return null;
    }


    // 로그인 정보 필요
    // 주고 받은 카드 개수 조회
    @Override
    @GetMapping("/counts")
    public GlobalApiResponse<CardCountResponseDTO> getAllCardCountByUserId() {
        return null;
    }

    // 받은 전체 카드 목록 조회
    @Override
    @GetMapping
    public GlobalApiResponse<List<CardSimpleResponseDTO>> getAllCardByUserId() {
        return null;
    }

    // 연도별 받은 카드 목록 조회
    @Override
    @GetMapping("/year/{year}")
    public GlobalApiResponse<List<CardSimpleResponseDTO>> getAllCardsByUserIdAndYear(@PathVariable Integer year) {
        return null;
    }
}
