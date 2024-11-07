package com.thankscard.member.service;

import com.thankscard.member.domain.CustomUserDetails;
import com.thankscard.member.domain.User;
import com.thankscard.member.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {

        //DB에서 조회
        User userData = userRepository.findByLoginId(loginId);

        if (userData != null) {

            //UserDetails에 담아서 return하면 AutneticationManager가 검증 함
            return new CustomUserDetails(userData);
        }
        return null;
    }

    public void setRefreshToken(User user, String refreshToken) {

        user.setRefreshToken(refreshToken);
        userRepository.save(user);
    }


}
