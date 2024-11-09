package com.thankscard.member.controller;

import com.thankscard.member.domain.CustomUserDetails;
import com.thankscard.member.dto.UserDto;
import com.thankscard.member.dto.UserEditDto;
import com.thankscard.member.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Iterator;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/")
    public String mainP() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iter = authorities.iterator();
        GrantedAuthority auth = iter.next();
        String role = auth.getAuthority();

        return "Main Controller : "+name;
    }

    // 사용자 정보 조회
    @GetMapping("/api/users/user-info")
    public ResponseEntity<UserDto> getUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        UserDto userDto = userService.getUser(userDetails.getUser());

        return ResponseEntity.ok().body(userDto);
    }

    // 사용자 정보 수정
    @PatchMapping("/api/users/user-info")
    public ResponseEntity<UserDto> updateUserInfo(@RequestBody UserEditDto userDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        UserDto user = userService.updateUser(userDetails.getUser(), userDto);

        return ResponseEntity.ok().body(user);


    }

    // 회원 탈퇴
    @DeleteMapping("/api/users")
    public ResponseEntity<Void> deleteUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        userService.deleteUser(userDetails.getUser());

        return ResponseEntity.ok().build();
    }

}
