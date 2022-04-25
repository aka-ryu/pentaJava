package com.example.penta.service;

import com.example.penta.dto.UserDTO;
import com.example.penta.dto.protocol.request.LoginRequestDTO;
import com.example.penta.dto.protocol.response.LoginResponseDTO;
import com.example.penta.dto.protocol.response.ResponseDTO;
import com.example.penta.entity.PersonalAccessToken;
import com.example.penta.entity.User;
import com.example.penta.entity.UserProfile;
import com.example.penta.repository.PersonalAccessTokenRepository;
import com.example.penta.repository.UserProfileRepository;
import com.example.penta.repository.UserRepositroy;
import com.example.penta.security.TokenProvider;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Log4j2
public class AuthService {

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


    public User registerUser(LoginRequestDTO loginRequestDTO) {

        // 테스트 데이터 생성
//        LoginRequestDTO loginRequestDTO = LoginRequestDTO.builder()
//                .wallet_address("0xE33f5e0C73B19C13F873AC9Ccf1e17F4735cD2F1")
//                .blockchain("ethereum")
//                .signature("test")
//                .build();

        // 지갑주소 소문자 변경
        String toLower = loginRequestDTO.getWallet_address().toLowerCase();

        // npe Optional
        Optional<User> optionalUser = userRepositroy.findByWalletAddressAndBlockchain(loginRequestDTO.getWallet_address(), loginRequestDTO.getBlockchain());

        // 기존회원이 아니면 회원가입
        if (optionalUser.isEmpty()) {
            String uuid = UUID.randomUUID().toString();

            User user = User.builder()
                    .walletAddress(loginRequestDTO.getWallet_address())
                    .blockchain(loginRequestDTO.getBlockchain())
                    .uuidUser(uuid)
                    .depositFund(null)
                    .rememberToken(null)
                    .isCertified(0)
                    .build();

            userRepositroy.save(user);

            UserProfile userProfile = UserProfile.builder()
                    .user(user)
                    .userName("NoName")
                    .build();

            userProfileRepository.save(userProfile);

            return user;
            //기존 회원이라면
        } else {
            User user = optionalUser.get();
            return user;
        }
    }


    public LoginResponseDTO loginUser(LoginRequestDTO loginRequestDTO, User user) {

//        // 테스트 loginRequest 생성
//        LoginRequestDTO loginRequestDTO = LoginRequestDTO.builder()
//                .wallet_address("0xE33f5e0C73B19C13F873AC9Ccf1e17F4735cD2F1")
//                .blockchain("ethereum")
//                .signature("test")
//                .build();
//
//        // 테스트 User 생성
//        Optional<User> optionalUser = userRepositroy.findByWalletAddressAndBlockchain(loginRequestDTO.getWallet_address(), loginRequestDTO.getBlockchain());
//
//        if(optionalUser.isEmpty()) {
//            System.out.println("유저없음");
//            throw new RuntimeException("유저없음");
//        }
//
//        User user = optionalUser.get();

        if(!user.getBlockchain().equals(loginRequestDTO.getBlockchain())) {
            log.warn("블록체인 주소 다름");
            throw new RuntimeException("블록체인 주소 다름");
        }

        // jwt 토큰생성
        String token = tokenProvider.create(user.getWalletAddress());
        System.out.println(tokenProvider.validateAndGetUserId(token));

        // 이미 저장된 엑세스토큰이 있는지 확인
        Optional<PersonalAccessToken>  optionalPersonalAccessToken = personalAccessTokenRepository.findByTokenableId(user.getId());

        // 없으면 신규발급
        if(optionalPersonalAccessToken.isEmpty()) {
            System.out.println("신규토큰발급");
            PersonalAccessToken personalAccessToken = PersonalAccessToken.builder()
                    .tokenableType("JwtToken")
                    .tokenableId(user.getId())
                    .name("pentasquare")
                    .token(user.getWalletAddress())
                    .build();
            personalAccessTokenRepository.save(personalAccessToken);
            updateAtService.updatePersonalAccessToken(personalAccessToken);


            // 있으면 만료시간체크 후 재생성
        } else {
            System.out.println("기존토큰갱신");
            PersonalAccessToken personalAccessToken = optionalPersonalAccessToken.get();
            updateAtService.updatePersonalAccessToken(personalAccessToken);
        }

        LoginResponseDTO loginResponseDTO = LoginResponseDTO.builder()
                .token(token)
                .is_certified(user.getIsCertified())
                .build();

        return loginResponseDTO;
    }


    public void logoutUser(String token) {
        String getToken = tokenProvider.validateAndGetUserId(token);

        Optional<PersonalAccessToken> optionalPersonalAccessToken = personalAccessTokenRepository.findByToken(getToken);

        if(optionalPersonalAccessToken.isEmpty()) {
            log.warn("해당 토큰 없음");
            throw new RuntimeException("해당 토큰 없음");
        }

        PersonalAccessToken personalAccessToken = optionalPersonalAccessToken.get();
        personalAccessTokenRepository.delete(personalAccessToken);
    }

}

