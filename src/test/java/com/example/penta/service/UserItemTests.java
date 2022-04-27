package com.example.penta.service;

import com.example.penta.dto.protocol.request.LoginRequestDTO;
import com.example.penta.dto.protocol.response.UserItemCountResponseDTO;
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

        LoginRequestDTO loginRequestDTO = LoginRequestDTO.builder()
                .wallet_address("0xe33f5e0c73b19c13f873ac9ccf1e17f4735cd2fe")
                .blockchain("ethereum")
                .build();

        // 유효한 값이 들어왔는지 확인
        if (loginRequestDTO.getBlockchain().isEmpty() || loginRequestDTO.getWallet_address().isEmpty()
                || loginRequestDTO.getBlockchain().equals("") || loginRequestDTO.getWallet_address().equals("")) {

            System.out.println("blockchain, wallet_address 값이 없음");
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

        List<NftAsset> assetInfo = nftAssetRepository.findAllByOwnerAddress(user.getWalletAddress());
        List<Long> arrayIds = nftAssetRepository.arrayIds(user.getWalletAddress());
        //여기까지 통과

        List<NftAsset> ownedInfo = nftAssetRepository.ownedInfo(user.getWalletAddress());
        List<Long> ownedIds = nftAssetRepository.ownedIds(user.getWalletAddress());

        //여기까지 통과


        List<NftMarket> marketInfo = nftMarketRepository.marketInfo(ownedInfo);


        System.out.println(marketInfo.size());
        System.out.println(marketInfo);
        System.out.println("--------------------");
        System.out.println("--------------------");
        System.out.println("--------------------");
        System.out.println("--------------------");
        System.out.println("--------------------");




        if (marketInfo.isEmpty()) {
            log.warn("market Info is can't not be null");

            throw new RuntimeException("market Info is can't not be null");
        } else {

        }
        List<Long> marketAsset = nftMarketRepository.marketAsset(ownedInfo);

        List<NftMarket> endInfo = nftMarketRepository.endInfo(marketAsset);
        List<Long> endIds = nftMarketRepository.endIds(marketAsset);


        int i = 0;
        List<Long> assetIds = new ArrayList<>();

        if(!ownedInfo.isEmpty()) {

            for(NftAsset owned : ownedInfo) {

                if (!marketInfo.isEmpty()) {

                    for(NftMarket market : marketInfo) {

                        if(owned.getQuantity() > 0) {

                            Optional<Long> optionalLong = nftAssetRepository.optionalAssetInfo01(owned.getId(), user.getWalletAddress());

                            if(!optionalLong.isEmpty()) {
                                Long assetInfo01 = optionalLong.get();
                                assetIds.add(assetInfo01);
                            }

                        } else if (owned.getQuantity().equals(0)) {

                            Optional<Long> optionalLong = nftAssetRepository.optionalAssetInfo02(owned.getId(), user.getWalletAddress(), endIds);

                            if(!optionalLong.isEmpty()) {
                                Long assetInfo02 = optionalLong.get();
                                assetIds.add(assetInfo02);
                            }

                        }
                        i++;
                    }

                    System.out.println("assetids");
                    System.out.println("assetids");
                    System.out.println("assetids");
                    System.out.println("assetids");
                    System.out.println(assetIds);

                    // 애초에 여길 안들어옴
                } else if (owned.getQuantity() > 0) {

                    assetIds.set(i, owned.getId());
                    List<Long> ids = new ArrayList<>();
                    while(assetIds.remove(null));

                    for(Long j : assetIds) {
                        ids.add(j);
                    }

                    

                    if(!ids.isEmpty()) {
                        assetIds.addAll(ids);
                    } else {
                        assetIds.clear();
                    }

                }
                i++;

            }

        }
        System.out.println(assetIds);

        int ownerCount;

        if(assetIds.isEmpty()) {
            List<NftAsset> owner = null;
            ownerCount = 0;

        } else {
            List<NftAsset> owner = nftAssetRepository.assetIds(user.getWalletAddress(), assetIds);
            ownerCount = owner.size();
        }

        System.out.println(ownerCount);

        Long allAssetCount = nftAssetRepository.allAssetCount(user.getWalletAddress(), user.getWalletAddress());

        Long createCount = nftAssetRepository.findByCreatorAddress(user.getWalletAddress()).stream().count();

        Long favoriteCount = userFavoriteRepository.findByUser(user).stream().count();

        Long freezeCount = nftAssetRepository.freezeCount(user.getWalletAddress());

        Long displayCount = nftMarketRepository.displayCount(arrayIds);

        List<Long> auctionIds = nftMarketRepository.auctionIds();

        Long myBidCount = nftEventHistoryRepository.myBidCount(user.getWalletAddress(), auctionIds);




    }

}
