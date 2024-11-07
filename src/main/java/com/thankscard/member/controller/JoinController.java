package com.thankscard.member.controller;

import com.thankscard.member.dto.JoinDto;
import com.thankscard.member.dto.LoginDto;
import com.thankscard.member.service.JoinService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class JoinController {

    private final JoinService joinService;

    @PostMapping("/join")
    public ResponseEntity<String> userJoin(@RequestBody JoinDto joinDto) {
        try {
            joinService.userJoin(joinDto);
            return ResponseEntity.ok("User registered successfully!");
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e){
            log.error("Join Failed : {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/login")
    public void login(@RequestBody LoginDto loginDto) {

    }

    @PostMapping("/logout")
    public ResponseEntity<String> userLogout() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        try {

            String username = authentication.getName();
            joinService.userLogout(username);

            return ResponseEntity.ok("User logged out!");
        } catch (Exception e) {
            log.error("Logout Failed : {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


}
