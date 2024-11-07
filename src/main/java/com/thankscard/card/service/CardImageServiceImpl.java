package com.thankscard.card.service;

import com.thankscard.card.domain.CardImage;
import com.thankscard.card.dto.CardImageResponseDTO;
import com.thankscard.card.repository.CardImageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CardImageServiceImpl implements CardImageService {

    private final CardImageRepository cardImageRepository;

    // 로그인 정보 불필요
    // 모든 카드 이미지 조회
    @Override
    public List<CardImageResponseDTO> findAllCardImages() {

        List<CardImage> cardImages = cardImageRepository.findAll();

        return cardImages.stream()
                .map(CardImageResponseDTO::from)
                .toList();
    }

    // 카테고리에 해당하는 카드 이미지 조회
    @Override
    public List<CardImageResponseDTO> findAllCardImagesByCategoryId(Long categoryId) {

        List<CardImage> cardImages = cardImageRepository.findAllByCategoryId(categoryId);

        return cardImages.stream()
                .map(CardImageResponseDTO::from)
                .toList();
    }

    // 요즘 인기있는 카드 이미지 조회
    @Override
    public List<CardImageResponseDTO> findAllCardImagesByPopular() {

        List<CardImage> cardImages = cardImageRepository.findTop5ByCardImage();

        return cardImages.stream()
                .map(CardImageResponseDTO::from)
                .toList();
    }
}
