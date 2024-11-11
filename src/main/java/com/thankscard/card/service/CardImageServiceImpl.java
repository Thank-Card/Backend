package com.thankscard.card.service;

import com.thankscard.card.domain.CardImage;
import com.thankscard.card.domain.Category;
import com.thankscard.card.dto.CardCategoryRequestDTO;
import com.thankscard.card.dto.CardCategoryResponseDTO;
import com.thankscard.card.dto.CardImageResponseDTO;
import com.thankscard.card.exception.CardException;
import com.thankscard.card.exception.CardStatus;
import com.thankscard.card.repository.CardImageRepository;
import com.thankscard.card.repository.CategoryRepository;
import com.thankscard.global.s3.service.S3UploadService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CardImageServiceImpl implements CardImageService {

    private final CardImageRepository cardImageRepository;
    private final CategoryRepository categoryRepository;
    private final S3UploadService s3UploadService;

    private final String dirPath = "card";

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

    @Override
    public void createCardImage(MultipartFile image, Long categoryId) throws IOException {

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CardException(CardStatus.CARD_CATEGORY_NOT_FOUND)
                );

        String imgPath = null;
        if (image != null) {
            imgPath = s3UploadService.uploadImage(image, dirPath);
        }

        cardImageRepository.save(CardImage.builder()
                .category(category)
                .imageUrl(imgPath)
                .build());
    }

    @Override
    public void updateCardImage(MultipartFile image, Long categoryId, Long imageId) throws IOException {

        CardImage cardImage = cardImageRepository.findById(imageId)
                .orElseThrow(() -> new CardException(CardStatus.CARD_IMAGE_NOT_FOUND));

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CardException(CardStatus.CARD_CATEGORY_NOT_FOUND)
                );

        String imgPath = cardImage.getImageUrl();
        if (image != null) {
            imgPath = s3UploadService.uploadImage(image, dirPath);
        }

        cardImageRepository.save(
                CardImage.builder()
                        .imageUrl(imgPath)
                        .category(category)
                        .build());
    }

    @Override
    public void deleteCardImage(Long imageId) {

        CardImage cardImage = cardImageRepository.findById(imageId)
                .orElseThrow(() -> new CardException(CardStatus.CARD_IMAGE_NOT_FOUND));

        cardImageRepository.delete(cardImage);
    }

    @Override
    public List<CardCategoryResponseDTO> getCardImageCategory() {

        List<Category> categories = categoryRepository.findAll();

        return categories.stream()
                .map(CardCategoryResponseDTO::from)
                .toList();
    }

    @Override
    public void createCardImageCategory(CardCategoryRequestDTO categoryRequestDTO) {

        categoryRepository.save(Category.builder()
                .categoryName(categoryRequestDTO.categoryName())
                .build());
    }

    @Override
    public void updateCardImageCategory(CardCategoryRequestDTO categoryRequestDTO, Long categoryId) {

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CardException(CardStatus.CARD_CATEGORY_NOT_FOUND));

        if (categoryRequestDTO.categoryName() != null) {

            category.setCategoryName(categoryRequestDTO.categoryName());
        }

        categoryRepository.save(category);
    }

    @Override
    public void deleteCardImageCategory(Long categoryId) {

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CardException(CardStatus.CARD_CATEGORY_NOT_FOUND));

        categoryRepository.delete(category);
    }
}
