package com.example.penta.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "nft_markets")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class NftMarket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "uuid_market")
    private String uuidMarket;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nft_asset_id")
    private NftAsset nftAsset;

    @Column(name = "contract_address", nullable = false)
    private String contractAddress;

    @Column(name = "token_id", nullable = false)
    private String tokenId;

    @Column(name = "is_display")
    private Integer isDisplay;

    @Column(name = "price")
    private Double price;

    @Column(name = "token_type")
    private String tokenType;

    @Column(name = "asset_type")
    private String assetType;

    @Column(name = "price_reserve")
    private Double priceReserve;

    @Column(name = "minimum_bid")
    private Double minimumBid;

    @Column(name = "type_trade")
    private Integer typeTrade;

    @Column(name = "category")
    private String category;

    @Column(name = "supply")
    private Long supply;

    @Column(name = "quantity")
    private Long quantity;

    @Column(name = "is_end")
    private Integer isEnd;

    @Column(name = "start_at")
    private Date startAt;

    @Column(name = "expiration_date")
    private Date expirationDate;

    @Lob
    @Column(name = "order_recipt")
    private String orderRecipt;

    @Column(name = "order_signature")
    private String orderSignature;

    @Column(name = "seller_address")
    private String sellerAddress;

    @Column(name = "tx_id")
    private String txId;

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;



}