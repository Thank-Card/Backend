package com.thankscard.card.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "주고 받은 카드 개수")
public record CardCountResponseDTO(

        @Schema(description = "개수", example = "0") Integer count
) {
}
