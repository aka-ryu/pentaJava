package com.example.penta.dto;

import com.example.penta.entity.NftAsset;
import com.example.penta.entity.NftMarket;
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

    public NftMarketDTO (NftMarket nftMarket) {
        this.id = nftMarket.getId();
        this.uuidMarket = nftMarket.getUuidMarket();
        this.nftAsset = nftMarket.getNftAsset();
        this.contractAddress = nftMarket.getContractAddress();
        this.tokenId = nftMarket.getTokenId();
        this.isDisplay = nftMarket.getIsDisplay();
        this.price = nftMarket.getPrice();
        this.tokenType = nftMarket.getTokenType();
        this.assetType = nftMarket.getAssetType();
        this.priceReserve = nftMarket.getPriceReserve();
        this.minimumBid = nftMarket.getMinimumBid();
        this.typeTrade = nftMarket.getTypeTrade();
        this.category = nftMarket.getCategory();
        this.supply = nftMarket.getSupply();
        this.quantity = nftMarket.getQuantity();
        this.isEnd = nftMarket.getIsEnd();
        this.startAt = nftMarket.getStartAt();
        this.expirationDate = nftMarket.getExpirationDate();
        this.orderRecipt = nftMarket.getOrderRecipt();
        this.orderSignature = nftMarket.getOrderSignature();
        this.sellerAddress = nftMarket.getSellerAddress();
        this.txId = nftMarket.getTxId();
    }

    public static NftMarket toEntity(NftMarketDTO nftMarketDTO) {
        return NftMarket.builder()
                .id(nftMarketDTO.getId())
                .uuidMarket(nftMarketDTO.getUuidMarket())
                .nftAsset(nftMarketDTO.getNftAsset())
                .contractAddress(nftMarketDTO.getContractAddress())
                .tokenId(nftMarketDTO.getTokenId())
                .isDisplay(nftMarketDTO.getIsDisplay())
                .price(nftMarketDTO.getPrice())
                .tokenType(nftMarketDTO.getTokenType())
                .assetType(nftMarketDTO.getAssetType())
                .priceReserve(nftMarketDTO.getPriceReserve())
                .minimumBid(nftMarketDTO.getMinimumBid())
                .typeTrade(nftMarketDTO.getTypeTrade())
                .category(nftMarketDTO.getCategory())
                .supply(nftMarketDTO.getSupply())
                .quantity(nftMarketDTO.getQuantity())
                .isEnd(nftMarketDTO.getIsEnd())
                .startAt(nftMarketDTO.getStartAt())
                .expirationDate(nftMarketDTO.getExpirationDate())
                .orderRecipt(nftMarketDTO.getOrderRecipt())
                .orderSignature(nftMarketDTO.getOrderSignature())
                .sellerAddress(nftMarketDTO.getSellerAddress())
                .txId(nftMarketDTO.getTxId())
                .build();
    }
}
