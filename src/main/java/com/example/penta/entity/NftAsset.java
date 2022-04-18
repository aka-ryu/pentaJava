package com.example.penta.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "nft_assets")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class NftAsset {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "uuid_asset")
    private String uuidAsset;

    @Column(name = "creator_address")
    private String creatorAddress;

    @Column(name = "owner_address")
    private String ownerAddress;

    @Column(name = "contract_address")
    private String contractAddress;

    @Column(name = "token_id")
    private String tokenId;

    @Column(name = "token_num")
    private Long tokenNum;

    @Column(name = "blockchain")
    private String blockchain;

    @Column(name = "network_id")
    private String networkId;

    @Column(name = "name")
    private String name;

    @Column(name = "asset_blockchain")
    private Integer assetBlockchain;

    @Column(name = "type")
    private Integer type;

    @Column(name = "asset_type")
    private String assetType;

    @Column(name = "supply")
    private Long supply;

    @Column(name = "quantity")
    private Long quantity;

    @Column(name = "init_count")
    private Long initCount;

    @Column(name = "uri_metadata")
    private String uriMetadata;

    @Column(name = "royalty_fee", nullable = false)
    private Integer royaltyFee;

    @Column(name = "royalty_payout_address")
    private String royaltyPayoutAddress;

    @Column(name = "is_freeze", nullable = false)
    private Integer isFreeze;

    @Column(name = "is_mint", nullable = false)
    private Integer isMint;

    @Column(name = "type_put_market", nullable = false)
    private Integer typePutMarket;

    @Column(name = "is_market_display", nullable = false)
    private Integer isMarketDisplay;

    @Column(name = "category")
    private String category;

    @Column(name = "price")
    private Double price;

    @Column(name = "service_fee")
    private Double serviceFee;

    @Column(name = "start_at")
    private Date startAt;

    @Column(name = "expiration_date")
    private Date expirationDate;

    @Column(name = "sell_start_date")
    private Date sellStartDate;

    @Column(name = "is_royalty_register", nullable = false)
    private Integer isRoyaltyRegister;

    @Column(name = "is_fees_register", nullable = false)
    private Integer isFeesRegister;

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;


}