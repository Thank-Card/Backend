package com.thankscard.card.service;

import com.thankscard.card.dto.CardImageResponseDTO;

import java.util.List;

public interface CardImageService {

    // 로그인 정보 불필요
    // 모든 카드 이미지 조회
    List<CardImageResponseDTO> findAllCardImages();

    // 카테고리에 해당하는 카드 이미지 조회
    List<CardImageResponseDTO> findAllCardImagesByCategoryId(Long categoryId);

    // 요즘 인기있는 카드 이미지 조회
    List<CardImageResponseDTO> findAllCardImagesByPopular();
}
