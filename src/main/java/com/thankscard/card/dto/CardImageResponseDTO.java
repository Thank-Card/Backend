package com.thankscard.card.dto;

import com.thankscard.card.domain.CardImage;
import com.thankscard.card.domain.Category;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record CardImageResponseDTO (
        @Schema(description = "카드 이미지 아이디", example = "1") Long id,
        @Schema(description = "카드 기본 이미지 URL", example = "http://www.example.com/image") String imageUrl,
        @Schema(description = "카드 카테고리 ID", example = "1") Long categoryId,
        @Schema(description = "카드 카테고리 이름", example = "크리스마스") String categoryName
) {

    public static CardImageResponseDTO from(CardImage cardImage) {

        return CardImageResponseDTO.builder()
                .id(cardImage.getId())
                .imageUrl(cardImage.getImageUrl())
                .categoryId(cardImage.getCategory().getId())
                .categoryName(cardImage.getCategory().getCategoryName())
                .build();
    }
}