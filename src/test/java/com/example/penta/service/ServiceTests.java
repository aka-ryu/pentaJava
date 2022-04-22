package com.example.penta.service;

import com.example.penta.dto.RequestDTO;
import com.example.penta.dto.UserDTO;
import com.example.penta.entity.User;
import com.example.penta.entity.UserProfile;
import com.example.penta.repository.UserProfileRepository;
import com.example.penta.repository.UserRepositroy;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
public class ServiceTests {

    @Autowired
    private UserRepositroy userRepositroy;

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    private ModelMapper modelMapper;

    //유저 회원가입
    @Test
    public void registerUser(){

        RequestDTO requestDTO = RequestDTO.builder()
                .walletaddress("asdfsadfasdf")
                .blockchain("dagasd")
                .signature("asdasd")
                .build();

        // 필수값 유효성검사
        if(requestDTO.getBlockchain() == null || requestDTO.getWalletaddress() == null) {
            System.out.println("Error validation");
            throw new RuntimeException("Error validation");
        }

        // 이미 존재하는 회원인지 체크
        User getUser = userRepositroy.findByWalletAddressAndBlockchain(
                requestDTO.getWalletaddress(),
                requestDTO.getBlockchain()
        );


        // 유저가 이미 있다면 login 메서드로 이동
        if(getUser != null) {
            UserDTO userDTO = modelMapper.map(getUser, UserDTO.class);
            loginUser(userDTO);
            System.out.println("유저이미있음");

            //유저가 없다면 User, UserProfile 생성 후 login 메서드로 이동
        } else {
            String uuid = UUID.randomUUID().toString();

            try {
                User registerUser = User.builder()
                        .walletAddress(requestDTO.getWalletaddress())
                        .blockchain(requestDTO.getBlockchain())
                        .uuidUser(uuid)
                        .build();

                System.out.println("유저가입--------------");
                System.out.println(userRepositroy.save(registerUser));

                UserProfile userProfile = UserProfile.builder()
                        .id(registerUser.getId())
                        .userName("NoName")
                        .build();

                System.out.println("유저 프로필 가입=============");
                System.out.println(userProfileRepository.save(userProfile));

                User user = userRepositroy.findByIdAndWalletAddressAndBlockchain(
                        registerUser.getId(),
                        registerUser.getWalletAddress(),
                        registerUser.getBlockchain()
                );

                UserDTO userDTO = modelMapper.map(user, UserDTO.class);
                loginUser(userDTO);

            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("에러당~");
            }

        }

    }

    public void loginUser(UserDTO userDTO) {

        System.out.println("ㅎㅇ");
    }
}
