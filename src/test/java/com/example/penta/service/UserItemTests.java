package com.example.penta.service;

import com.example.penta.dto.protocol.request.auth.RegisterReqDTO;
import com.example.penta.entity.*;
import com.example.penta.repository.*;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@Log4j2
public class UserItemTests {
    @Autowired
    private UserRepositroy userRepositroy;
    @Autowired
    private PersonalAccessTokenRepository personalAccessTokenRepository;

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    private NftAssetRepository nftAssetRepository;

    @Autowired
    private NftMarketRepository nftMarketRepository;

    @Autowired
    private NftEventHistoryRepository nftEventHistoryRepository;

    @Autowired
    private UserFavoriteRepository userFavoriteRepository;


    @Test
    @Transactional
    public void userItemCount() {

        RegisterReqDTO registerReqDTO = RegisterReqDTO.builder()
                .wallet_address("0xe33f5e0c73b19c13f873ac9ccf1e17f4735cd2fe")
                .blockchain("ethereum")
                .build();

        // 유효한 값이 들어왔는지 확인
        if (registerReqDTO.getBlockchain().isEmpty() || registerReqDTO.getWallet_address().isEmpty()
                || registerReqDTO.getBlockchain().equals("") || registerReqDTO.getWallet_address().equals("")) {

            System.out.println("blockchain, wallet_address 값이 없음");
            throw new RuntimeException("blockchain, wallet_address 값 없음");
        }

        // walletAddress 소문자처리
        registerReqDTO.setWallet_address(registerReqDTO.getWallet_address().toLowerCase());

        // 받은 블록체인,지갑주소로 유저 조회
        Optional<User> optionalUser = userRepositroy.findByWalletAddressAndBlockchain(registerReqDTO.getWallet_address(), registerReqDTO.getBlockchain());

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

        if (optionalUserProfile.isEmpty()) {
            log.warn("유저프로필 없음");
            throw new RuntimeException("유저프로필 없음");
        }

        List<NftAsset> assetInfo = nftAssetRepository.findAllByOwnerAddress(user.getWalletAddress());
        List<Long> arrayIds = nftAssetRepository.arrayIds(user.getWalletAddress());

        List<NftAsset> ownedInfo = nftAssetRepository.ownedInfo(user.getWalletAddress());
        List<Long> ownedIds = nftAssetRepository.ownedIds(user.getWalletAddress());

        List<NftMarket> marketInfo = nftMarketRepository.marketInfo(ownedInfo);
        List<Long> marketAsset = nftMarketRepository.marketAsset(ownedInfo);

        List<NftMarket> endInfo = nftMarketRepository.endInfo(marketAsset);
        List<Long> endIds = nftMarketRepository.endIds(marketAsset);

        // 확실히 문제없음. 생 쿼리와 동일한 결과를 도출함

        int i = 0;
        List<Long> assetIds = new ArrayList<>();

        if (!ownedInfo.isEmpty()) ;
        {

            // ownedInfo 3개
            for (NftAsset owned : ownedInfo) {


                if (!marketInfo.isEmpty()) {

                    for (NftMarket market : marketInfo) {

                        if (!(market == null)) {

                            System.out.println(owned.getQuantity());
                            if (owned.getQuantity() > 0) {
                                Optional<Long> optionalLong = nftAssetRepository.optionalAssetInfo01(owned.getId(), user.getWalletAddress());
                                if (!optionalLong.isEmpty()) {
                                    assetIds.add(optionalLong.get());
                                }


                            } else if (owned.getQuantity() == 0) {


                                if (endIds.isEmpty()) {
                                    Optional<Long> optionalLong = nftAssetRepository.optionalAssetInfo02Null(owned.getId(), user.getWalletAddress());
                                    if (!optionalLong.isEmpty()) {
                                        assetIds.add(optionalLong.get());
                                    }
                                } else {
                                    Optional<Long> optionalLong = nftAssetRepository.optionalAssetInfo02NotNull(owned.getId(), user.getWalletAddress(), endIds);
                                    if (!optionalLong.isEmpty()) {
                                        assetIds.add(optionalLong.get());
                                    }
                                }
                            }

                        } else {
                            System.out.println("마켓없음");
                            throw new RuntimeException("마켓없음");
                        }
                        i++;
                    }

                }

                if (owned.getQuantity() > 0) {
                    assetIds.add(i, owned.getId());

                }
                i++;

            }

        }
        int ownerCount;

        if (assetIds.isEmpty()) {
            ownerCount = 0;
        } else {
            ownerCount = nftAssetRepository.onwerCount(user.getWalletAddress(), assetIds);
        }



    }
}


