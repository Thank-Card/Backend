package com.thankscard.card.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(description = "주고 받은 카드 개수")
public record CardCountResponseDTO(

        @Schema(description = "보낸 개수", example = "0") Integer sendCount,
        @Schema(description = "받은 개수", example = "0") Integer recvCount
) {
}
