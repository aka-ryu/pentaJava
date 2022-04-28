package com.example.penta.service;

import com.example.penta.dto.protocol.request.auth.RegisterReqDTO;
import com.example.penta.dto.protocol.response.*;
import com.example.penta.entity.*;
import com.example.penta.repository.*;
import com.example.penta.security.TokenProvider;
import com.example.penta.service.etc.UpdateJWTService;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private UpdateJWTService updateAtService;

    @Autowired
    private NftAssetRepository nftAssetRepository;

    @Autowired
    private NftMarketRepository nftMarketRepository;

    @Autowired
    private UserFavoriteRepository userFavoriteRepository;

    @Autowired
    private NftEventHistoryRepository nftEventHistoryRepository;





    // register
    public User registerUser(RegisterReqDTO registerReqDTO) {

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


    public LoginResponseDTO loginUser(RegisterReqDTO registerReqDTO, User user) {


        if (!user.getBlockchain().equals(registerReqDTO.getBlockchain())) {
            log.warn("블록체인 주소 다름");
            throw new RuntimeException("블록체인 주소 다름");
        }

        // jwt 토큰생성
        String accessToken = tokenProvider.createAccessToken(user.getWalletAddress());
        String refreshToken = tokenProvider.createRefreshToken();


        // 이미 저장된 엑세스토큰이 있는지 확인
        Optional<PersonalAccessToken> optionalPersonalAccessToken = personalAccessTokenRepository.findByTokenableId(user.getId());

        // 없으면 신규발급
        if (optionalPersonalAccessToken.isEmpty()) {
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
                .token(accessToken)
                .is_certified(user.getIsCertified())
                .build();

        return loginResponseDTO;
    }


    public void logoutUser(String token) {

        Optional<PersonalAccessToken> optionalPersonalAccessToken = personalAccessTokenRepository.findByToken(token);
        if(optionalPersonalAccessToken.isPresent()) {
            personalAccessTokenRepository.delete(optionalPersonalAccessToken.get());
        }
    }




    // 이미 로그인한 유저 요청 검사
    public User logedUserValidation(String wallet_address, String blockchain) {

        // walletAddress 소문자처리
        wallet_address.toLowerCase();

        // 받은 블록체인,지갑주소로 유저 조회
        Optional<User> optionalUser = userRepositroy.findByWalletAddressAndBlockchain(wallet_address, blockchain);

        if (optionalUser.isEmpty()) {
            log.warn("유저없음");
            throw new RuntimeException("유저없음");
        }

        User user = optionalUser.get();

        // 유저의 pk로 생성된 토큰 조회
        Optional<PersonalAccessToken> optionalPersonalAccessToken = personalAccessTokenRepository.findByTokenableId(user.getId());

        if (optionalPersonalAccessToken.isEmpty()) {
            log.warn("토큰없음");
            throw new RuntimeException("토큰없음");
        }

        PersonalAccessToken personalAccessToken = optionalPersonalAccessToken.get();

        // 유저의 토큰 체크 (jwt body로 지갑주소를 넣었음)
        if (!user.getWalletAddress().equals(personalAccessToken.getToken())) {
            log.warn("토큰 정보 오류");
            throw new RuntimeException("토큰 정보 오류");
        }


        return user;
    }
}

