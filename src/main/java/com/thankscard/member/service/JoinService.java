package com.thankscard.member.service;

import com.thankscard.member.domain.User;
import com.thankscard.member.dto.JoinDto;
import com.thankscard.member.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class JoinService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public void userJoin(JoinDto joinDto) {

        String loginId = joinDto.getLoginId();
        String name = joinDto.getName();
        String password = joinDto.getPassword();

        Boolean isExist = userRepository.existsByLoginId(loginId);

        if (isExist) {

            throw new DataIntegrityViolationException("User already exist");
        }

        User user = new User();

        user.setLoginId(loginId);
        user.setName(name);
        user.setPassword(bCryptPasswordEncoder.encode(password));
        user.setRole("ROLE_USER");

        userRepository.save(user);
    }

    public void userLogout(String username) {

        User user = userRepository.findByLoginId(username);
        user.setRefreshToken(null);

        userRepository.save(user);
    }

    public void userLogin(User user, String refreshToken) {

        user.setRefreshToken(refreshToken);
        userRepository.save(user);
    }
}
