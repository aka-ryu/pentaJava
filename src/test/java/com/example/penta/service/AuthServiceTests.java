package com.example.penta.service;

import com.example.penta.dto.protocol.request.auth.RegisterReqDTO;
import com.example.penta.entity.PersonalAccessToken;
import com.example.penta.entity.User;
import com.example.penta.entity.UserProfile;
import com.example.penta.repository.*;
import com.example.penta.security.TokenProvider;
import com.example.penta.service.etc.UpdateJWTService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
@Log4j2
public class AuthServiceTests {

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
    private UpdateJWTService updateAtService;

    @Autowired
    private NftAssetRepository nftAssetRepository;

    @Autowired
    private NftMarketRepository nftMarketRepository;

    // req wallet_address, blockchain, signature
    @Test
    public void registerUser() {

        // 테스트 데이터 생성
        RegisterReqDTO registerReqDTO = RegisterReqDTO.builder()
                .wallet_address("0xE33f5e0C73B19C13F873AC9Ccf1e17F4735cD2F1")
                .blockchain("ethereum")
                .signature("test")
                .build();

        // 지갑주소 소문자 변경
        String toLower = registerReqDTO.getWallet_address().toLowerCase();

        // npe Optional
        Optional<User> optionalUser = userRepositroy.findByWalletAddressAndBlockchain(registerReqDTO.getWallet_address(), registerReqDTO.getBlockchain());

        // 기존회원이 아니면 회원가입
        if (optionalUser.isEmpty()) {
            String uuid = UUID.randomUUID().toString();

            User user = User.builder()
                    .walletAddress(registerReqDTO.getWallet_address())
                    .blockchain(registerReqDTO.getBlockchain())
                    .uuidUser(uuid)
                    .depositFund(null)
                    .rememberToken(null)
                    .isCertified(0)
                    .build();

            userRepositroy.save(user);
            System.out.println("신규회원 " + user);

            UserProfile userProfile = UserProfile.builder()
                    .user(user)
                    .userName("NoName")
                    .build();
            userProfileRepository.save(userProfile);
            System.out.println("유저프로필도 가입 완료");
            //기존 회원이라면
        } else {
            User user = optionalUser.get();
            System.out.println("기존회원 " + user);
        }
    }

    //로그인
    @Test
    public void loginUser() {

        // 테스트 loginRequest 생성
        RegisterReqDTO registerReqDTO = RegisterReqDTO.builder()
                .wallet_address("0xE33f5e0C73B19C13F873AC9Ccf1e17F4735cD2F1")
                .blockchain("ethereum")
                .signature("test")
                .build();

        // 테스트 User 생성
        Optional<User> optionalUser = userRepositroy.findByWalletAddressAndBlockchain(registerReqDTO.getWallet_address(), registerReqDTO.getBlockchain());

        if(optionalUser.isEmpty()) {
            System.out.println("유저없음");
            throw new RuntimeException("유저없음");
        }

        User user = optionalUser.get();

        if(!user.getBlockchain().equals(registerReqDTO.getBlockchain())) {
            System.out.println("블록체인 주소 다름");
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

    }

    // 프로필셋팅
//    @Test
//    public void testTokenInfo() {
//        LoginRequestDTO loginRequestDTO = LoginRequestDTO.builder()
//                .wallet_address("0xE33f5e0C73B19C13F873AC9Ccf1e17F4735cD2F1")
//                .blockchain("ethereum")
//                .build();
//
//        Optional<User> optionalUser = userRepositroy.findByWalletAddressAndBlockchain(loginRequestDTO.getWallet_address(), loginRequestDTO.getBlockchain());
//
//        if(optionalUser.isEmpty()) {
//            System.out.println("유저없음");
//            throw new RuntimeException("유저없음");
//        }
//
//        User user = optionalUser.get();
//    }

    @Test
    public void ListTests() {

        List<Integer> intList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            intList.add(i);
            intList.add(null);
        }

        System.out.println(intList);
        System.out.println(intList.get(2));
        while(intList.remove(null));
        System.out.println("삭제완료");
        System.out.println(intList);
        System.out.println(intList.get(2));




    }
}

