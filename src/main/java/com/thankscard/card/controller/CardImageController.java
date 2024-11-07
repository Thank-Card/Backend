package com.thankscard.card.controller;

import com.thankscard.card.dto.CardImageResponseDTO;
import com.thankscard.global.api.GlobalApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Tag(name = "감사 카드 이미지 API", description = "감사 카드 이미지 조회 관련 API")
public interface CardImageController {

    // 로그인 정보 불필요
    // 모든 카드 이미지 조회
    @Operation(summary = "카드 이미지 조회")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "카드 이미지 조회 성공"
            )})
    GlobalApiResponse<List<CardImageResponseDTO>> getAllCardImageList();

    // 카테고리에 해당하는 카드 이미지 조회
    @Operation(summary = "특정 카테고리 카드 이미지 조회")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "특정 카테고리 카드 이미지 조회 성공"
            )})
    GlobalApiResponse<List<CardImageResponseDTO>> getCardImageListByCategory(@PathVariable Long categoryId);

    // 요즘 인기있는 카드 이미지 조회
    @Operation(summary = "인기 카드 이미지 조회")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "인기 카드 이미지 조회 성공"
            )})
    GlobalApiResponse<List<CardImageResponseDTO>> getCardImageListByPopular();

    // 카드 이미지 생성


    // 카드 카테고리 생성
    

}
