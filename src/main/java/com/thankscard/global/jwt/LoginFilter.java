package com.thankscard.global.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thankscard.member.domain.CustomUserDetails;
import com.thankscard.member.service.CustomUserDetailService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

@Slf4j
public class LoginFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;
    private final ObjectMapper objectMapper;
    private final CustomUserDetailService customUserDetailService;

    public LoginFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil, ObjectMapper objectMapper, CustomUserDetailService customUserDetailService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.objectMapper = objectMapper;
        this.customUserDetailService = customUserDetailService;
        super.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/api/users/login", "POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        //클라이언트 요청에서 loginId, password 추출
        Map<String, String> loginRequest = null;
        try {
            loginRequest = objectMapper.readValue(request.getInputStream(), Map.class);
        } catch (IOException e) {
            log.error("AttemptAuthentication Mapping Error : {}", e.getMessage());
            try {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // 400 상태 코드 설정
                response.setCharacterEncoding("UTF-8"); // 응답 인코딩 설정
                response.setContentType("application/json; charset=UTF-8"); // JSON 형식과 인코딩 설정
                response.getWriter().write("{\"error\": \"잘못된 형식의 로그인 요청입니다.\"}");
            } catch (IOException ex) {
                log.error("AttemptAuthentication Mapping Error : {}", ex.getMessage());
            }

            return null;
        }

        String loginId = loginRequest.get("loginId");
        String password = loginRequest.get("password");

        //스프링 시큐리티에서 loginId와 password를 검증하기 위해서는 token에 담아야 함
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(loginId, password, null);

        //token에 담은 검증을 위한 AuthenticationManager로 전달
        return authenticationManager.authenticate(authToken);
    }

    //로그인 성공시 실행하는 메소드 (여기서 JWT를 발급)
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) {
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();

        String username = customUserDetails.getUsername();

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
        GrantedAuthority auth = iterator.next();

        String role = auth.getAuthority();

        String refreshToken = customUserDetails.getRefreshToken();
        String accessToken = null;

        if (refreshToken != null && !refreshToken.isEmpty() && !jwtUtil.isExpired(refreshToken)) {

            accessToken = jwtUtil.createJwt(username, role, 1000*60*60*3L);
        } else {

            refreshToken = jwtUtil.createJwt(username, role, 1000*60*60*12L);
            accessToken = jwtUtil.createJwt(username, role, 1000*60*60*3L);

            customUserDetailService.setRefreshToken(customUserDetails.getUser(), refreshToken);
        }

        response.addHeader("Authorization", "Bearer " + accessToken);

    }

    //로그인 실패시 실행하는 메소드 (JWT 발급 안함)
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) {
        response.setStatus(401);
    }
}
