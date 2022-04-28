package com.example.penta.dto.entityDTO;

import lombok.*;

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


}
