package com.thankscard.card.validation.validator;

import com.thankscard.card.exception.CardStatus;
import com.thankscard.card.repository.CardImageRepository;
import com.thankscard.card.validation.annotation.ExistCardImage;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CardImageExistValidator implements ConstraintValidator<ExistCardImage, Long> {

    private CardImageRepository cardImageRepository;

    @Override
    public void initialize(ExistCardImage constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long id, ConstraintValidatorContext constraintValidatorContext) {
        boolean isValid = cardImageRepository.existsById(id);

        if (!isValid) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate(CardStatus.CARD_IMAGE_NOT_FOUND.toString()).addConstraintViolation();
        }

        return isValid;
    }
}
