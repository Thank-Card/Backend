package com.thankscard.card.controller;

import com.thankscard.card.dto.*;
import com.thankscard.global.api.GlobalApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Tag(name = "감사 카드 API", description = "감사 카드 전송, 조회 관련 API")
public interface CardController {

    // 로그인 정보 불필요
    // 카드 전송
    @Operation(summary = "카드 전송")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "카드 전송 성공"
            )})
    GlobalApiResponse<CardSendResponseDTO> sendCard(@RequestBody CardRequestDTO cardRequestDTO);

    // 카드 간략 조회(작성 년도, 이미지, 보낸이)
    @Operation(summary = "카드 간략 조회")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "카드 간략 조회 성공"
            )})
    GlobalApiResponse<CardSimpleResponseDTO> getCardInfoByCardId(@PathVariable String cardId);

    // 카드 상세 조회(이미지, 작성일, 내용, 보낸이)
    @Operation(summary = "카드 상세 조회")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "카드 상세 조회 성공"
            )})
    GlobalApiResponse<CardDetailResponseDTO> getCardDetailByCardId(@PathVariable String cardId);

    // 로그인 정보 필요
    // 주고 받은 카드 개수 조회
    @Operation(summary = "주고 받은 카드 개수 조회")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "주고 받은 카드 개수 조회 성공"
            )})
    GlobalApiResponse<CardCountResponseDTO> getAllCardCountByUserId();

    // 받은 전체 카드 목록 조회
    @Operation(summary = "받은 카드 목록 전체 조회")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "받은 카드 목록 전체 조회 성공"
            )})
    GlobalApiResponse<List<CardSimpleResponseDTO>> getAllCardByUserId();

    // 연도별 받은 카드 목록 조회
    @Operation(summary = "연도별 받은 카드 목록 조회")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "연도별 받은 카드 목록 조회 성공"
            )})
    GlobalApiResponse<List<CardSimpleResponseDTO>> getAllCardsByUserIdAndYear(@PathVariable Integer year);

}
