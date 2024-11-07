package com.thankscard.member.service;

import com.thankscard.global.jwt.JWTUtil;
import com.thankscard.member.domain.User;
import com.thankscard.member.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final JWTUtil jwtUtil;
    private final UserRepository userRepository;

    public String reissueAccessToken(String loginId) {
        User user = userRepository.findByLoginId(loginId);
        String accessToken = null;

        if (jwtUtil.isExpired(user.getRefreshToken())) {

            accessToken = jwtUtil.createJwt(user.getLoginId(), user.getRole(), 1000*60*60*3L);
        }

        return accessToken;
    }
}
