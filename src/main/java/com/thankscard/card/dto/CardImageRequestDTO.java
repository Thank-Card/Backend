package com.thankscard.card.dto;

import com.thankscard.card.domain.CardImage;
import com.thankscard.card.domain.Category;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record CardImageRequestDTO(

        @Schema(description = "카드 기본 이미지 URL", example = "http://www.example.com/image") String imageUrl,
        @Schema(description = "카드 카테고리 ID", example = "1") Long categoryId) {

    public static CardImageRequestDTO from(CardImage cardImage) {

        return CardImageRequestDTO.builder()
                .imageUrl(cardImage.getImageUrl())
                .categoryId(cardImage.getCategory().getId())
                .build();
    }
}
