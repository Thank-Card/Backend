package com.thankscard.card.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record CardRequestDTO(
        @Schema(description = "내용", example = "4호선톤에 참여하신 모든 분들 올 한해도 수고 많으셨습니다!") String content,
        @Schema(description = "카드 이미지 ID", example = "1") Long cardImageId,
        @Schema(description = "사용자 첨부 이미지 URL", example = "http://www.example.com/image") String userImage,
        @Schema(description = "보낸이", example = "김멋사") String sendUser
) {

}
