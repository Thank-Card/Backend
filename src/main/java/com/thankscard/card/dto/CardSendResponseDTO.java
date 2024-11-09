package com.thankscard.card.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record CardSendResponseDTO(

        @Schema(description = "카드 ID", example = "9b1deb4d3b7d4bad9bdd2b0d7b3dcb6d") String id
) {


}
