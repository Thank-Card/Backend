package com.thankscard.card.controller;

import com.thankscard.card.dto.CardCategoryRequestDTO;
import com.thankscard.card.dto.CardCategoryResponseDTO;
import com.thankscard.card.dto.CardImageResponseDTO;
import com.thankscard.global.api.GlobalApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
    @Operation(summary = "카드 이미지 생성")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "카드 이미지 생성 성공"
            )})
    GlobalApiResponse<Void> createCardImage(
            @RequestPart(value = "image", required = false) MultipartFile cardImage,
            @RequestPart(value = "categoryId") Long categoryId
    ) throws IOException;

    // 카드 이미지 수정
    @Operation(summary = "카드 이미지 수정")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "카드 이미지 수정 성공"
            )})
    GlobalApiResponse<Void> updateCardImage(
            @RequestPart(value = "image", required = false) MultipartFile cardImage,
            @RequestPart(value= "categoryId") Long categoryId,
            @PathVariable Long imageId
    ) throws IOException;

    // 카드 이미지 삭제
    @Operation(summary = "카드 이미지 삭제")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "카드 이미지 생성 성공"
            )})
    GlobalApiResponse<Void> deleteCardImage(@PathVariable Long imageId);

    // 카드 카테고리 조회
    @Operation(summary = "카드 카테고리 조회")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "카드 카테고리 조회 성공"
            )})
    GlobalApiResponse<List<CardCategoryResponseDTO>> getCardImageCategory();

    // 카드 카테고리 생성
    @Operation(summary = "카드 카테고리 생성")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "카드 카테고리 생성 성공"
            )})
    GlobalApiResponse<Void> createCardImageCategory(@RequestBody CardCategoryRequestDTO categoryRequestDTO);

    // 카드 카테고리 수정
    @Operation(summary = "카드 카테고리 수정")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "카드 카테고리 수정 성공"
            )})
    GlobalApiResponse<Void> updateCardImageCategory(@RequestBody CardCategoryRequestDTO categoryRequestDTO, @PathVariable Long categoryId);

    // 카드 카테고리 삭제
    @Operation(summary = "카드 카테고리 삭제")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "카드 카테고리 삭제 성공"
            )})
    GlobalApiResponse<Void> deleteCardImageCategory(@PathVariable Long categoryId);

}
