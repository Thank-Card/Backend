package com.thankscard.member.controller;

import com.thankscard.member.service.TokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/tokens")
public class TokenController {

    private final TokenService tokenService;

    @PostMapping("/reissue")
    public ResponseEntity<String> reissueAccessToken() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String accessToken = tokenService.reissueAccessToken(authentication.getName());

        if (accessToken == null) {
            log.error("Token could not be reissued");
            throw new RuntimeException();
        }

        return ResponseEntity.ok().body("{\"access_token\":\"" + accessToken + "\"}");
    }
}
