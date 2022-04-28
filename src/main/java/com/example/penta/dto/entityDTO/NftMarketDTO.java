package com.example.penta.dto.entityDTO;

import com.example.penta.entity.NftAsset;
import lombok.*;

import java.util.Date;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NftMarketDTO {

    private Long id;
    private String uuidMarket;
    private NftAsset nftAsset;
    private String contractAddress;
    private String tokenId;
    private Integer isDisplay;
    private Double price;
    private String tokenType;
    private String assetType;
    private Double priceReserve;
    private Double minimumBid;
    private Integer typeTrade;
    private String category;
    private Long supply;
    private Long quantity;
    private Integer isEnd;
    private Date startAt;
    private Date expirationDate;
    private String orderRecipt;
    private String orderSignature;
    private String sellerAddress;
    private String txId;


}
