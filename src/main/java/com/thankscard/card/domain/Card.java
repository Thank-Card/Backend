package com.thankscard.card.domain;

import com.thankscard.member.domain.User;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
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

    private String recvTempUser;

    @Column(name = "received_at")
    private LocalDateTime receivedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "send_user_id", nullable = false)
    private User sendUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recv_user_id")
    private User recvUser;


    @PrePersist
    public void prePersist() {
        this.id = UUID.randomUUID().toString().replaceAll("-", "");
    }

    public void assignToRecvUser(User recvUser) {
        if (recvUser != null) {
            this.recvUser = recvUser;
            this.recvTempUser = null;
            this.receivedAt = LocalDateTime.now();
        }
    }
}
