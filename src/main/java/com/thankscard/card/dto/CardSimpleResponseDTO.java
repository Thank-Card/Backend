package com.thankscard.card.dto;

import com.thankscard.card.domain.Card;
import com.thankscard.card.domain.CardImage;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record CardSimpleResponseDTO(
        @Schema(description = "카드 ID", example = "9b1deb4d3b7d4bad9bdd2b0d7b3dcb6d") String id,
        @Schema(description = "카드 이미지 URL", example = "https://www.example.com/image") String cardImageUrl,
        @Schema(description = "작성 시간", example = "2024-11-05T12:33:46.091Z") LocalDateTime createdAt,
        @Schema(description = "보낸이", example = "김멋사") String sendUser,
        @Schema(description = "받는이", example = "이멋사") String recvUser
) {

    public static CardSimpleResponseDTO from(Card card) {
        return CardSimpleResponseDTO.builder()
                .id(card.getId())
                .cardImageUrl(card.getCardImage().getImageUrl())
                .createdAt(card.getCreatedAt())
                .sendUser(card.getSendUser())
                .recvUser(card.getRecvUser())
                .build();
    }
}
