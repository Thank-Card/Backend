package com.thankscard.card.repository;

import com.thankscard.card.domain.CardImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CardImageRepository extends JpaRepository<CardImage, Long> {

    List<CardImage> findAllByCategoryId(Long categoryId);

    @Query("SELECT c.cardImage " +
            "FROM Card c " +
            "GROUP BY c.cardImage " +
            "HAVING COUNT(c.cardImage) >= 10 " +
            "ORDER BY COUNT(c.cardImage) DESC")
    List<CardImage> findTop5ByCardImage();
}
