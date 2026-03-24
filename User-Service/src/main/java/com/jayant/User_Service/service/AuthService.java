package com.jayant.User_Service.service;


import com.jayant.User_Service.dto.SignupRequestDto;
import com.jayant.User_Service.dto.UserDto;
import com.jayant.User_Service.entity.User;
import com.jayant.User_Service.exception.BadRequestException;
import com.jayant.User_Service.exception.ResourceNotFoundException;
import com.jayant.User_Service.repository.UserRepository;
import com.jayant.User_Service.utils.PasswordUtil;
import com.jayant.User_Service.dto.LoginRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class  AuthService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final JwtService jwtService;

    public UserDto signUp(SignupRequestDto signupRequestDto) {
        boolean exists = userRepository.existsByEmail(signupRequestDto.getEmail());
        if(exists) {
            throw new BadRequestException("User already exists, cannot signup again.");
        }

        User user = modelMapper.map(signupRequestDto, User.class);
        user.setPassword(PasswordUtil.hashPassword(signupRequestDto.getPassword()));

        User savedUser = userRepository.save(user);
        return modelMapper.map(savedUser, UserDto.class);
    }

    public String login(LoginRequestDto loginRequestDto){
        User user =userRepository.findByEmail(loginRequestDto.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + loginRequestDto.getEmail()));
        boolean isPasswordMatch = PasswordUtil.checkPassword(loginRequestDto.getPassword() , user.getPassword());
        if(!isPasswordMatch){
            throw new BadRequestException("Incorrect Password");
        }
        return jwtService.generateAccessToken(user);
    }


}
