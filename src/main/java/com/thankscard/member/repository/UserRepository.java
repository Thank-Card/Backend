package com.thankscard.member.repository;


import com.thankscard.member.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByLoginId(String loginId);
    boolean existsByLoginId(String loginId);
}
