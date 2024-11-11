package com.thankscard.card.service;

import com.thankscard.card.dto.CardCategoryRequestDTO;
import com.thankscard.card.dto.CardCategoryResponseDTO;
import com.thankscard.card.dto.CardImageResponseDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface CardImageService {

    // 로그인 정보 불필요
    // 모든 카드 이미지 조회
    List<CardImageResponseDTO> findAllCardImages();

    // 카테고리에 해당하는 카드 이미지 조회
    List<CardImageResponseDTO> findAllCardImagesByCategoryId(Long categoryId);

    // 요즘 인기있는 카드 이미지 조회
    List<CardImageResponseDTO> findAllCardImagesByPopular();


    // 카드 이미지 생성
    void createCardImage(MultipartFile image, Long categoryId) throws IOException;

    // 카드 이미지 수정
    void updateCardImage(MultipartFile image, Long categoryId, Long imageId) throws IOException;

    // 카드 이미지 삭제
    void deleteCardImage(Long imageId);

    // 카드 이미지 카테고리 조회
    List<CardCategoryResponseDTO> getCardImageCategory();

    // 카드 이미지 카테고리 생성
    void createCardImageCategory(CardCategoryRequestDTO categoryRequestDTO);

    // 카드 이미지 카테고리 수정
    void updateCardImageCategory(CardCategoryRequestDTO categoryRequestDTO, Long categoryId);

    // 카드 이미지 카테고리 삭제
    void deleteCardImageCategory(Long categoryId);
}
