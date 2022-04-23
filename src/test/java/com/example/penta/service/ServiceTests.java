package com.example.penta.service;

import com.example.penta.dto.RequestDTO;
import com.example.penta.dto.UserDTO;
import com.example.penta.entity.PersonalAccessToken;
import com.example.penta.entity.User;
import com.example.penta.entity.UserProfile;
import com.example.penta.repository.PersonalAccessTokenRepository;
import com.example.penta.repository.UserProfileRepository;
import com.example.penta.repository.UserRepositroy;
import com.example.penta.security.TokenProvider;
import jdk.swing.interop.SwingInterOpUtils;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
@Log4j2
public class ServiceTests {

    @Autowired
    private UserRepositroy userRepositroy;

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    private PersonalAccessTokenRepository personalAccessTokenRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private UpdateAtService updateAtService;

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
            System.out.println("유저이미있음");
            loginUser(userDTO, requestDTO);

            //유저가 없다면 User, UserProfile 생성 후 login 메서드로 이동
        } else {
            String uuid = UUID.randomUUID().toString();

            try {
                User registerUser = User.builder()
                        .walletAddress(requestDTO.getWalletaddress())
                        .blockchain(requestDTO.getBlockchain())
                        .uuidUser(uuid)
                        .isCertified(0)
                        .build();

                System.out.println("유저가입--------------");
                System.out.println(userRepositroy.save(registerUser));

                UserProfile userProfile = UserProfile.builder()
                        .user(registerUser)
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
                loginUser(userDTO, requestDTO);

            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("에러당~");
            }

        }

    }

    @Test
    public void loginUser(UserDTO userDTO, RequestDTO requestDTO) {


        System.out.println("login 진입");
        // 유효성검사
        User checkUser = userRepositroy.findByWalletAddressAndBlockchain(requestDTO.getWalletaddress(), requestDTO.getBlockchain());
        System.out.println("checkUser 검사");
        System.out.println(checkUser);
        if(checkUser == null ) {
            log.info("Unauthorised.");
            log.info("로그인벨리데이션");
            throw new RuntimeException("Unauthorised.");
        }
        
        // 검사 후 유저엔티티 호출
        User user = userRepositroy.findByIdAndWalletAddressAndBlockchain(userDTO.getId(), userDTO.getWalletAddress(), userDTO.getBlockchain());

        if (user == null || !requestDTO.getBlockchain().equals(checkUser.getBlockchain())) {
            log.info("Unauthorised.");
            throw new RuntimeException("Unauthorised.");
        }
        
        // jwt 발행
        String token = tokenProvider.create(user.getWalletAddress());

        System.out.println("토큰 바디");
        System.out.println(token);
        System.out.println(tokenProvider.getTokenBody(token));



        // 발행한 토큰 PersonalAccessToken 엔티티 저장
        PersonalAccessToken personalAccessToken = PersonalAccessToken.builder()
                .tokenableType("JwtToken")
                .tokenableId(user.getId())
                .name("pentasquare")
                .token(tokenProvider.validateAndGetUserId(token))
                .build();

        personalAccessTokenRepository.save(personalAccessToken);

        // 토큰 마지막 인증시간 체크
        updateAtService.updatePersonalAccessToken(personalAccessToken);

        System.out.println("토큰발행까지 완료 띠~");
    }
}
