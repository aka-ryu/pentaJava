package com.example.penta.service;

import com.example.penta.dto.RequestDTO;
import com.example.penta.dto.ResponseDTO;
import com.example.penta.dto.UserDTO;
import com.example.penta.entity.PersonalAccessToken;
import com.example.penta.entity.User;
import com.example.penta.entity.UserProfile;
import com.example.penta.repository.PersonalAccessTokenRepository;
import com.example.penta.repository.UserProfileRepository;
import com.example.penta.repository.UserRepositroy;
import com.example.penta.security.TokenProvider;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Log4j2
public class UserService {

    @Autowired
    private UserRepositroy userRepositroy;

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private PersonalAccessTokenRepository personalAccessTokenRepository;

    @Autowired
    private UpdateAtService updateAtService;


    //유저 회원가입
    public void registerUser(RequestDTO requestDTO) {

        // 지갑주소 소문자변경
        requestDTO.setWalletaddress(requestDTO.getWalletaddress().toLowerCase());

        // 필수값 유효성검사
        if (requestDTO.getBlockchain() == null || requestDTO.getWalletaddress() == null) {
            log.info("Error validation");
            throw new RuntimeException("Error validation");
        }

        // 이미 존재하는 회원인지 체크
        User getUser = userRepositroy.findByWalletAddressAndBlockchain(
                requestDTO.getWalletaddress(),
                requestDTO.getBlockchain()
        );


        // 유저가 이미 있다면 login 메서드로 이동
        if (getUser != null) {
            UserDTO userDTO = modelMapper.map(getUser, UserDTO.class);
            loginUser(userDTO, requestDTO);
            log.info("이미가입했음");

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


                userRepositroy.save(registerUser);

                UserProfile userProfile = UserProfile.builder()
                        .user(registerUser)
                        .userName("NoName")
                        .build();


                userProfileRepository.save(userProfile);

                User user = userRepositroy.findByIdAndWalletAddressAndBlockchain(
                        registerUser.getId(),
                        registerUser.getWalletAddress(),
                        registerUser.getBlockchain()
                );

                UserDTO userDTO = modelMapper.map(user, UserDTO.class);
                loginUser(userDTO, requestDTO);

            } catch (Exception e) {
                log.info(e.getMessage());
            }

        }

    }

    public void loginUser(UserDTO userDTO, RequestDTO requestDTO) {

        // 유효성검사
        User checkUser = userRepositroy.findByWalletAddressAndBlockchain(requestDTO.getWalletaddress(), requestDTO.getBlockchain());

        if (checkUser == null) {
            log.info("Unauthorised.");
            throw new RuntimeException("Unauthorised.");
        }

        // 검사 후 유저엔티티 호출
        User user = userRepositroy.findByIdAndWalletAddressAndBlockchain(userDTO.getId(), userDTO.getWalletAddress(), userDTO.getBlockchain());

        if (user == null || !requestDTO.getBlockchain().equals(checkUser.getBlockchain())) {
            log.info("Unauthorised.");
            throw new RuntimeException("Unauthorised.");
        }

        // jwt 발행
        int min = 111111;
        int max = 999999;
        int nonce = (int) ((Math.random() * (max - min)) + min);
        System.out.println(nonce);
        String token = tokenProvider.create(user.getWalletAddress());


        // 발행한 토큰 PersonalAccessToken 엔티티 저장
        PersonalAccessToken personalAccessToken = PersonalAccessToken.builder()
                .tokenableType("JwtToken")
                .tokenableId(user.getId())
                .name("pentasquare")
                .token(tokenProvider.validateAndGetUserId(token))
                .build();

        personalAccessTokenRepository.save(personalAccessToken);

        // 토큰 마지막 사용메서드 체크
        updateAtService.updatePersonalAccessToken(personalAccessToken);

//        ResponseDTO responseDTO = ResponseDTO.builder()
//
//                .build();
    }

}

