package com.thankscard.card.dto;

import com.thankscard.card.domain.Card;
import com.thankscard.card.domain.CardImage;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record CardDetailResponseDTO(

        @Schema(description = "카드 ID", example = "9b1deb4d3b7d4bad9bdd2b0d7b3dcb6d") String id,
        @Schema(description = "내용", example = "4호선톤에 참여하신 모든 분들 올 한해도 수고 많으셨습니다!") String content,
        @Schema(description = "카드 이미지 URL", example = "https://www.example.com/image") String cardImageUrl,
        @Schema(description = "사용자 첨부 이미지 URL", example = "https://www.example.com/image") String userImage,
        @Schema(description = "작성 시간", example = "2024-11-05T12:33:46.091Z") LocalDateTime createdAt,
        @Schema(description = "보낸이", example = "김멋사") String sendUser,
        @Schema(description = "받는이", example = "이멋사") String recvUser
) {

    public static CardDetailResponseDTO from(Card card) {

        return CardDetailResponseDTO.builder()
                .id(card.getId())
                .content(card.getContent())
                .cardImageUrl(card.getCardImage().getImageUrl())
                .userImage(card.getUserImage())
                .createdAt(card.getCreatedAt())
                .sendUser(card.getSendUser())
                .recvUser(card.getRecvUser())
                .build();
    }
}
