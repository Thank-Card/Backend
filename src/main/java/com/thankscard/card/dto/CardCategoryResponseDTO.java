package com.thankscard.card.dto;

import com.thankscard.card.domain.Category;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record CardCategoryResponseDTO(
        @Schema(description = "카테고리 아이디", example = "1") Long id,
        @Schema(description = "카테고리 이름", example = "크리스마스") String categoryName

) {

    public static CardCategoryResponseDTO from (Category category) {

        return CardCategoryResponseDTO.builder()
                .id(category.getId())
                .categoryName(category.getCategoryName())
                .build();
    }

}
