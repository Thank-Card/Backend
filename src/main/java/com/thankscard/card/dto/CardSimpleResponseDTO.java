package com.thankscard.card.dto;

import com.thankscard.card.domain.Card;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record CardSimpleResponseDTO(
        @Schema(description = "카드 ID", example = "9b1deb4d3b7d4bad9bdd2b0d7b3dcb6d") String id,
        @Schema(description = "카드 이미지 URL", example = "https://www.example.com/image") String cardImageUrl,
        @Schema(description = "사용자 첨부 이미지 URL", example = "https://www.example.com/image") String userImage,
        @Schema(description = "작성 시간", example = "2024-11-05T12:33:46.091Z") LocalDateTime createdAt,
        @Schema(description = "보낸유저", example = "김멋사") String sendUser,
        @Schema(description = "받는유저", example = "이멋사") String recvUser,
        @Schema(description = "보낸이 이름", example = "김멋사") String from,

        @Schema(description = "받는이 이름", example = "이멋사") String dear
) {

    public static CardSimpleResponseDTO from(Card card) {
        String name;
        return CardSimpleResponseDTO.builder()
                .id(card.getId())
                .cardImageUrl(card.getCardImage().getImageUrl())
                .userImage(card.getUserImage())
                .createdAt(card.getCreatedAt())
                .sendUser(card.getSendUser()!=null?card.getSendUser().getName():null)
                .recvUser(card.getRecvUser()!=null?card.getRecvUser().getName():null)
                .from(card.getFrom())
                .dear(card.getDear())
                .build();
    }
}
