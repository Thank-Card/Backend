package com.thankscard.card.controller;

import com.thankscard.card.dto.CardCategoryRequestDTO;
import com.thankscard.card.dto.CardCategoryResponseDTO;
import com.thankscard.card.dto.CardImageResponseDTO;
import com.thankscard.card.service.CardImageService;
import com.thankscard.global.api.GlobalApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    @Override
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public GlobalApiResponse<Void> createCardImage(
            @RequestPart(value = "image", required = false) MultipartFile cardImage,
            @RequestPart(value = "categoryId") Long categoryId
    ) throws IOException {

        cardImageService.createCardImage(cardImage, categoryId);
        return GlobalApiResponse.onSuccess(null);
    }

    @Override
    @PatchMapping(value = "/{imageId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public GlobalApiResponse<Void> updateCardImage(
            @RequestPart(value = "image", required = false) MultipartFile cardImage,
            @RequestPart(value = "categoryId") Long categoryId,
            @PathVariable Long imageId
    ) throws IOException {

        cardImageService.updateCardImage(cardImage, categoryId, imageId);
        return GlobalApiResponse.onSuccess(null);
    }

    @Override
    @DeleteMapping("/{imageId}")
    public GlobalApiResponse<Void> deleteCardImage(@PathVariable Long imageId) {

        cardImageService.deleteCardImage(imageId);
        return GlobalApiResponse.onSuccess(null);
    }

    @Override
    @GetMapping("/categorys")
    public GlobalApiResponse<List<CardCategoryResponseDTO>> getCardImageCategory() {

        return GlobalApiResponse.onSuccess(cardImageService.getCardImageCategory());
    }

    @Override
    @PostMapping("/categorys")
    public GlobalApiResponse<Void> createCardImageCategory(@RequestBody CardCategoryRequestDTO categoryRequestDTO) {

        cardImageService.createCardImageCategory(categoryRequestDTO);
        return GlobalApiResponse.onSuccess(null);
    }

    @Override
    @PatchMapping("/categorys/{categoryId}")
    public GlobalApiResponse<Void> updateCardImageCategory(@RequestBody CardCategoryRequestDTO categoryRequestDTO, @PathVariable Long categoryId) {

        cardImageService.updateCardImageCategory(categoryRequestDTO, categoryId);
        return GlobalApiResponse.onSuccess(null);
    }

    @Override
    @DeleteMapping("/categorys/{categoryId}")
    public GlobalApiResponse<Void> deleteCardImageCategory(@PathVariable Long categoryId) {

        cardImageService.deleteCardImageCategory(categoryId);
        return GlobalApiResponse.onSuccess(null);
    }


}
