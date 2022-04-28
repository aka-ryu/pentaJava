package com.example.penta.service;

import com.example.penta.dto.protocol.request.auth.RegisterReqDTO;
import com.example.penta.dto.protocol.request.user.UserItemCountReqDTO;
import com.example.penta.dto.protocol.response.ProfileInfoResponseDTO;
import com.example.penta.dto.protocol.response.TokenInfoResponseDTO;
import com.example.penta.dto.protocol.response.UserItemCountResponseDTO;
import com.example.penta.entity.*;
import com.example.penta.repository.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Log4j2
@Service
public class UserService {

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

    @Autowired
    private AuthService authService;

    public UserItemCountResponseDTO userItemCount(UserItemCountReqDTO userItemCountReqDTO) {

        User user = authService.logedUserValidation(userItemCountReqDTO.getWallet_address(), userItemCountReqDTO.getBlockchain());

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

        Long allAssetCount = nftAssetRepository.allAssetCount(user.getWalletAddress(), user.getWalletAddress());

        Long createCount = nftAssetRepository.findByCreatorAddress(user.getWalletAddress()).stream().count();

        Long favoriteCount = userFavoriteRepository.findByUser(user).stream().count();

        Long freezeCount = nftAssetRepository.freezeCount(user.getWalletAddress());

        Long displayCount = nftMarketRepository.displayCount(arrayIds);

        List<Long> auctionIds = nftMarketRepository.auctionIds();

        Long myBidCount = nftEventHistoryRepository.myBidCount(user.getWalletAddress(), auctionIds);

        UserItemCountResponseDTO userItemCountResponseDTO = UserItemCountResponseDTO.builder()
                .allAssetCount(allAssetCount.intValue())
                .createCount(createCount.intValue())
                .ownerCount(ownerCount)
                .favoriteCount(favoriteCount.intValue())
                .displayCount(displayCount.intValue())
                .myBidCount(myBidCount.intValue())
                .freezCount(freezeCount.intValue())
                .build();

        return userItemCountResponseDTO;

    }

    public TokenInfoResponseDTO tokenInfo(RegisterReqDTO registerReqDTO) {

        User user = authService.logedUserValidation(registerReqDTO.getWallet_address(), registerReqDTO.getBlockchain());

        Optional<PersonalAccessToken> optionalPersonalAccessToken = personalAccessTokenRepository.findByTokenableId(user.getId());

        // 이미 logedUserValidation 에서 토큰조회 했기 때문에 무조건 값이 존재함 Optioanl Null 체크 필요 x
        TokenInfoResponseDTO tokenInfoResponseDTO = TokenInfoResponseDTO.builder()
                .wallet_address(registerReqDTO.getWallet_address())
                .personalAccessToken(optionalPersonalAccessToken.get())
                .build();

        return tokenInfoResponseDTO;
    }

    public ProfileInfoResponseDTO profileInfo(RegisterReqDTO registerReqDTO) {

        User user = authService.logedUserValidation(registerReqDTO.getWallet_address(), registerReqDTO.getBlockchain());

        Optional<UserProfile> optionalUserProfile = userProfileRepository.findByUser(user);

        if(optionalUserProfile.isEmpty()) {
            log.warn("유저프로필 없음");
            throw new RuntimeException("유저프로필 없음");
        }

        ProfileInfoResponseDTO profileInfoResponseDTO = ProfileInfoResponseDTO.builder()
                .wallet_address(registerReqDTO.getWallet_address())
                .userProfile(optionalUserProfile.get())
                .is_certified(user.getIsCertified())
                .build();

        return profileInfoResponseDTO;
    }
}
