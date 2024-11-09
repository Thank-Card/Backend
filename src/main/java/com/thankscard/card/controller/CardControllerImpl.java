package com.thankscard.card.controller;

import com.thankscard.card.dto.*;
import com.thankscard.card.service.CardService;
import com.thankscard.global.api.GlobalApiResponse;
import com.thankscard.member.domain.User;
import com.thankscard.member.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cards")
public class CardControllerImpl implements CardController {

    private final CardService cardService;
    private final UserService userService;

    // 로그인 정보 불필요
    // 카드 전송
    @Override
    @PostMapping(value = "/send", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public GlobalApiResponse<CardSendResponseDTO> sendCard(
            @RequestPart("card") CardRequestDTO cardRequestDTO,
            @RequestPart(value = "image", required = false) MultipartFile image) throws IOException {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getUserByAuthentication(authentication);

        return GlobalApiResponse.onSuccess(cardService.createCard(cardRequestDTO, image, user));
    }

    // 카드 간략 조회(작성 년도, 이미지, 보낸이)
    @Override
    @GetMapping("/{cardId}/simple")
    public GlobalApiResponse<CardSimpleResponseDTO> getCardInfoByCardId(@PathVariable String cardId) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getUserByAuthentication(authentication);

        return GlobalApiResponse.onSuccess(cardService.getCardInfoByCardId(cardId, user));
    }

    // 카드 상세 조회(이미지, 작성일, 내용, 보낸이)
    @Override
    @GetMapping("/{cardId}/detail")
    public GlobalApiResponse<CardDetailResponseDTO> getCardDetailByCardId(@PathVariable String cardId) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getUserByAuthentication(authentication);

        return GlobalApiResponse.onSuccess(cardService.getCardDetailByCardId(cardId, user));
    }


    // 로그인 정보 필요
    // 주고 받은 카드 개수 조회
    @Override
    @GetMapping("/counts")
    public GlobalApiResponse<CardCountResponseDTO> getAllCardCountByUserId() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getUserByAuthentication(authentication);

        return GlobalApiResponse.onSuccess(cardService.getAllCardCountByUser(user));
    }

    // 받은 전체 카드 목록 조회
    @Override
    @GetMapping
    public GlobalApiResponse<List<CardSimpleResponseDTO>> getAllCardByUserId() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getUserByAuthentication(authentication);

        return GlobalApiResponse.onSuccess(cardService.getAllCardByUserId(user));
    }

    // 연도별 받은 카드 목록 조회
    @Override
    @GetMapping("/year/{year}")
    public GlobalApiResponse<List<CardSimpleResponseDTO>> getAllCardsByUserIdAndYear(@PathVariable Integer year) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getUserByAuthentication(authentication);

        return GlobalApiResponse.onSuccess(cardService.getAllCardsByUserAndYear(user, year));
    }

    // 카드 수신
    @Override
    @PatchMapping("/recv/{cardId}")
    public GlobalApiResponse<Void> assignCard(@PathVariable String cardId) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getUserByAuthentication(authentication);

        cardService.assignCardByUser(user, cardId);

        return GlobalApiResponse.onSuccess(null);
    }
}
