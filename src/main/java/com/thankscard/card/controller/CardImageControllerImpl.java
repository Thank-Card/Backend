package com.thankscard.card.controller;

import com.thankscard.card.dto.CardImageResponseDTO;
import com.thankscard.card.service.CardImageService;
import com.thankscard.global.api.GlobalApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cards/images")
public class CardImageControllerImpl implements CardImageController {

    private final CardImageService cardImageService;

    // 카드 이미지 관련
    @Override
    @GetMapping
    public GlobalApiResponse<List<CardImageResponseDTO>> getAllCardImageList() {

        return GlobalApiResponse.onSuccess(cardImageService.findAllCardImages());
    }

    @Override
    @GetMapping("/categorys/{categoryId}")
    public GlobalApiResponse<List<CardImageResponseDTO>> getCardImageListByCategory(@PathVariable Long categoryId) {

        return GlobalApiResponse.onSuccess(cardImageService.findAllCardImagesByCategoryId(categoryId));
    }

    @Override
    @GetMapping("/populars")
    public GlobalApiResponse<List<CardImageResponseDTO>> getCardImageListByPopular() {

        return GlobalApiResponse.onSuccess(cardImageService.findAllCardImagesByPopular());
    }


}
