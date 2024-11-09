package com.thankscard.card.repository;

import com.thankscard.card.domain.Card;
import com.thankscard.member.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CardRepository extends JpaRepository<Card, String> {

    @Query("SELECT c FROM Card c WHERE c.recvUser = :user AND YEAR(c.receivedAt) = :year")
    List<Card> findAllByRecvUserAndYear(@Param("user") User user, @Param("year") Integer year);

    List<Card> findAllByRecvUser(User recvUser);

    int countBySendUser(User sendUser);

    int countByRecvUser(User user);
}
