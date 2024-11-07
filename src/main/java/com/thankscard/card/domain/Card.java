package com.thankscard.card.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Card {

    @Id
    private String id;

    @Column(columnDefinition = "TEXT")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card_image_id")
    private CardImage cardImage;

    @Column(name = "user_image")
    private String userImage;

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    private String sendUser;
    private String recvUser;

    @PrePersist
    public void prePersist() {
        this.id = UUID.randomUUID().toString().replaceAll("-", "");
    }
}
