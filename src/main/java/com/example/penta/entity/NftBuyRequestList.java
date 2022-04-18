package com.example.penta.entity;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "nft_buy_request_lists")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class NftBuyRequestList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "uuid_buy", nullable = false)
    private String uuidBuy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nft_market_id")
    private NftMarket nftMarket;

    @Column(name = "token_id")
    private String tokenId;

    @Column(name = "contract_address")
    private String contractAddress;

    @Column(name = "tx_id")
    private String txId;

    @Column(name = "buy_count")
    private Long buyCount;

    @Column(name = "request_status", nullable = false)
    private Integer requestStatus;

    @Lob
    @Column(name = "order_recipt")
    private String orderRecipt;

    @Column(name = "order_signature")
    private String orderSignature;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;


}