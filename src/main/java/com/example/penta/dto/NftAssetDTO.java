package com.example.penta.dto;

import com.example.penta.entity.NftAsset;
import lombok.*;

import java.time.Instant;
import java.util.Date;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NftAssetDTO {

    private Long id;
    private String uuidAsset;
    private String creatorAddress;
    private String ownerAddress;
    private String contractAddress;
    private String tokenId;
    private Long tokenNum;
    private String blockchain;
    private String networkId;
    private String name;
    private Integer assetBlockchain;
    private Integer type;
    private String assetType;
    private Long supply;
    private Long quantity;
    private Long initCount;
    private String uriMetadata;
    private Integer royaltyFee;
    private String royaltyPayoutAddress;
    private Integer isFreeze;
    private Integer isMint;
    private Integer typePutMarket;
    private Integer isMarketDisplay;
    private String category;
    private Double price;
    private Double serviceFee;
    private Date startAt;
    private Date expirationDate;
    private Date sellStartDate;
    private Integer isRoyaltyRegister;
    private Integer isFeesRegister;

    public NftAssetDTO(NftAsset nftAsset) {
        this.id = nftAsset.getId();
        this.uuidAsset = nftAsset.getUuidAsset();
        this.creatorAddress = nftAsset.getCreatorAddress();
        this.ownerAddress = nftAsset.getOwnerAddress();
        this.contractAddress = nftAsset.getContractAddress();
        this.tokenId = nftAsset.getTokenId();
        this.tokenNum = nftAsset.getTokenNum();
        this.blockchain = nftAsset.getBlockchain();
        this.networkId = nftAsset.getNetworkId();
        this.name = nftAsset.getName();
        this.assetBlockchain = nftAsset.getAssetBlockchain();
        this.type = nftAsset.getType();
        this.assetType = nftAsset.getAssetType();
        this.supply = nftAsset.getSupply();
        this.quantity = nftAsset.getQuantity();
        this.initCount = nftAsset.getInitCount();
        this.uriMetadata = nftAsset.getUriMetadata();
        this.royaltyFee = nftAsset.getRoyaltyFee();
        this.royaltyPayoutAddress = nftAsset.getRoyaltyPayoutAddress();
        this.isFreeze = nftAsset.getIsFreeze();
        this.isMint = nftAsset.getIsMint();
        this.typePutMarket = nftAsset.getTypePutMarket();
        this.isMarketDisplay = nftAsset.getIsMarketDisplay();
        this.category = nftAsset.getCategory();
        this.price = nftAsset.getPrice();
        this.serviceFee = nftAsset.getServiceFee();
        this.startAt = nftAsset.getStartAt();
        this.expirationDate = nftAsset.getExpirationDate();
        this.sellStartDate = nftAsset.getSellStartDate();
        this.isRoyaltyRegister = nftAsset.getIsRoyaltyRegister();
        this.isFeesRegister = nftAsset.getIsFeesRegister();
    }

    public static NftAsset toEntity(NftAssetDTO nftAssetDTO) {
        return NftAsset.builder()
                .id(nftAssetDTO.getId())
                .uuidAsset(nftAssetDTO.getUuidAsset())
                .creatorAddress(nftAssetDTO.getCreatorAddress())
                .ownerAddress(nftAssetDTO.getOwnerAddress())
                .contractAddress(nftAssetDTO.getContractAddress())
                .tokenId(nftAssetDTO.getTokenId())
                .tokenNum(nftAssetDTO.getTokenNum())
                .blockchain(nftAssetDTO.getBlockchain())
                .networkId(nftAssetDTO.getNetworkId())
                .name(nftAssetDTO.getName())
                .assetBlockchain(nftAssetDTO.getAssetBlockchain())
                .type(nftAssetDTO.getType())
                .assetType(nftAssetDTO.getAssetType())
                .supply(nftAssetDTO.getSupply())
                .quantity(nftAssetDTO.getQuantity())
                .initCount(nftAssetDTO.getInitCount())
                .uriMetadata(nftAssetDTO.getUriMetadata())
                .royaltyFee(nftAssetDTO.getRoyaltyFee())
                .royaltyPayoutAddress(nftAssetDTO.getRoyaltyPayoutAddress())
                .isFreeze(nftAssetDTO.getIsFreeze())
                .isMint(nftAssetDTO.getIsMint())
                .typePutMarket(nftAssetDTO.getTypePutMarket())
                .isMarketDisplay(nftAssetDTO.getIsMarketDisplay())
                .category(nftAssetDTO.getCategory())
                .price(nftAssetDTO.getPrice())
                .serviceFee(nftAssetDTO.getServiceFee())
                .startAt(nftAssetDTO.getStartAt())
                .expirationDate(nftAssetDTO.getExpirationDate())
                .sellStartDate(nftAssetDTO.getSellStartDate())
                .isRoyaltyRegister(nftAssetDTO.getIsRoyaltyRegister())
                .isFeesRegister(nftAssetDTO.getIsFeesRegister())
                .build();
    }
}
