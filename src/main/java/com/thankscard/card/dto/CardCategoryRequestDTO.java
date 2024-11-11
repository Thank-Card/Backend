package com.thankscard.card.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record CardCategoryRequestDTO (
        @Schema(description = "카테고리 이름", example = "크리스마스") String categoryName
) {
}
