package com.thankscard.member.service;

import com.thankscard.member.domain.User;
import com.thankscard.member.dto.UserDto;
import com.thankscard.member.dto.UserEditDto;
import com.thankscard.member.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserDto getUser(User user) {

        UserDto userDto = new UserDto();
        userDto.setLoginId(user.getLoginId());
        userDto.setName(user.getName());

        return userDto;
    }

    public UserDto updateUser(User user, UserEditDto userDto) {

        if(userDto.getName() != null) {
            user.setName(userDto.getName());
        }
        if(userDto.getPassword() != null) {
            user.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        }

        User updatedUser = userRepository.save(user);

        UserDto updatedUserDto = new UserDto();
        updatedUserDto.setLoginId(updatedUser.getLoginId());
        updatedUserDto.setName(updatedUser.getName());

        return updatedUserDto;
    }

    public void deleteUser(User user) {
        userRepository.delete(user);
    }
}
