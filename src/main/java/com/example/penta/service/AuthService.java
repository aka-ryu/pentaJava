package com.example.penta.service;

import com.example.penta.dto.UserDTO;
import com.example.penta.dto.protocol.request.LoginRequestDTO;
import com.example.penta.dto.protocol.response.LoginResponseDTO;
import com.example.penta.dto.protocol.response.ProfileInfoResponseDTO;
import com.example.penta.dto.protocol.response.ResponseDTO;
import com.example.penta.dto.protocol.response.TokenInfoResponseDTO;
import com.example.penta.entity.NftAsset;
import com.example.penta.entity.PersonalAccessToken;
import com.example.penta.entity.User;
import com.example.penta.entity.UserProfile;
import com.example.penta.repository.NftAssetRepository;
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

    @Autowired
    private NftAssetRepository nftAssetRepository;


    public User registerUser(LoginRequestDTO loginRequestDTO) {

        // 유효한 값이 들어왔는지 확인
        if (loginRequestDTO.getBlockchain().isEmpty() || loginRequestDTO.getWallet_address().isEmpty()
                || loginRequestDTO.getBlockchain().equals("") || loginRequestDTO.getWallet_address().equals("")) {

            log.warn("blockchain, wallet_address 값이 없음");
            throw new RuntimeException("blockchain, wallet_address 값 없음");
        }

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

        if (!user.getBlockchain().equals(loginRequestDTO.getBlockchain())) {
            log.warn("블록체인 주소 다름");
            throw new RuntimeException("블록체인 주소 다름");
        }

        // jwt 토큰생성
        String token = tokenProvider.create(user.getWalletAddress());
        System.out.println(token);
        System.out.println("----------------------");
        System.out.println("----------------------");
        System.out.println("----------------------");
        System.out.println("----------------------");
        System.out.println("----------------------");

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
                .token(token)
                .is_certified(user.getIsCertified())
                .build();

        return loginResponseDTO;
    }


    public void logoutUser(String token) {
        String getToken = tokenProvider.validateAndGetUserId(token);

        Optional<PersonalAccessToken> optionalPersonalAccessToken = personalAccessTokenRepository.findByToken(getToken);

        if (optionalPersonalAccessToken.isEmpty()) {
            log.warn("해당 토큰 없음");
            throw new RuntimeException("해당 토큰 없음");
        }

        PersonalAccessToken personalAccessToken = optionalPersonalAccessToken.get();
        personalAccessTokenRepository.delete(personalAccessToken);
    }

    public TokenInfoResponseDTO tokenInfo(LoginRequestDTO loginRequestDTO) {

        // 유효한 값이 들어왔는지 확인
        if (loginRequestDTO.getBlockchain().isEmpty() || loginRequestDTO.getWallet_address().isEmpty()
                || loginRequestDTO.getBlockchain().equals("") || loginRequestDTO.getWallet_address().equals("")) {

            log.warn("blockchain, wallet_address 값이 없음");
            throw new RuntimeException("blockchain, wallet_address 값 없음");
        }

        // wallet_address 소문자작업
        loginRequestDTO.setWallet_address(loginRequestDTO.getWallet_address().toLowerCase());

        // personalAccessToken 체크

        // 받은 블록체인,지갑주소로 유저 조회
        Optional<User> optionalUser = userRepositroy.findByWalletAddressAndBlockchain(loginRequestDTO.getWallet_address(), loginRequestDTO.getBlockchain());

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

        TokenInfoResponseDTO tokenInfoResponseDTO = TokenInfoResponseDTO.builder()
                .wallet_address(loginRequestDTO.getWallet_address())
                .personalAccessToken(personalAccessToken)
                .build();

        return tokenInfoResponseDTO;
    }

    public ProfileInfoResponseDTO profileInfo(LoginRequestDTO loginRequestDTO) {

        // 유효한 값이 들어왔는지 확인
        if (loginRequestDTO.getBlockchain().isEmpty() || loginRequestDTO.getWallet_address().isEmpty()
                || loginRequestDTO.getBlockchain().equals("") || loginRequestDTO.getWallet_address().equals("")) {

            log.warn("blockchain, wallet_address 값이 없음");
            throw new RuntimeException("blockchain, wallet_address 값 없음");
        }

        // wallet_address 소문자작업
        loginRequestDTO.setWallet_address(loginRequestDTO.getWallet_address().toLowerCase());

        // personalAccessToken 체크

        // 받은 블록체인,지갑주소로 유저 조회
        Optional<User> optionalUser = userRepositroy.findByWalletAddressAndBlockchain(loginRequestDTO.getWallet_address(), loginRequestDTO.getBlockchain());

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

        Optional<UserProfile> optionalUserProfile = userProfileRepository.findByUser(user);

        if(optionalUserProfile.isEmpty()) {
            log.warn("유저프로필 없음");
            throw new RuntimeException("유저프로필 없음");
        }

        ProfileInfoResponseDTO profileInfoResponseDTO = ProfileInfoResponseDTO.builder()
                .wallet_address(loginRequestDTO.getWallet_address())
                .userProfile(optionalUserProfile.get())
                .is_certified(user.getIsCertified())
                .build();

        return profileInfoResponseDTO;
    }

    public void userItemCount(LoginRequestDTO loginRequestDTO) {

        // 유효한 값이 들어왔는지 확인
        if (loginRequestDTO.getBlockchain().isEmpty() || loginRequestDTO.getWallet_address().isEmpty()
                || loginRequestDTO.getBlockchain().equals("") || loginRequestDTO.getWallet_address().equals("")) {

            log.warn("blockchain, wallet_address 값이 없음");
            throw new RuntimeException("blockchain, wallet_address 값 없음");
        }

        // walletAddress 소문자처리
        loginRequestDTO.setWallet_address(loginRequestDTO.getWallet_address().toLowerCase());

        // 받은 블록체인,지갑주소로 유저 조회
        Optional<User> optionalUser = userRepositroy.findByWalletAddressAndBlockchain(loginRequestDTO.getWallet_address(), loginRequestDTO.getBlockchain());

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

        Optional<UserProfile> optionalUserProfile = userProfileRepository.findByUser(user);

        if(optionalUserProfile.isEmpty()) {
            log.warn("유저프로필 없음");
            throw new RuntimeException("유저프로필 없음");
        }

        List<NftAsset> arrayIds = nftAssetRepository.findAllByOwnerAddress(user.getWalletAddress());
        List<Long> ownedIds = nftAssetRepository.findIdList(user.getWalletAddress());

    }
}

