package com.example.penta.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "nft_event_histories")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class NftEventHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "contract_address")
    private String contractAddress;

    @Column(name = "token_id")
    private String tokenId;

    @Column(name = "event")
    private String event;

    @Column(name = "price")
    private Double price;

    @Column(name = "from_address")
    private String fromAddress;

    @Column(name = "to_address")
    private String toAddress;

    @Column(name = "nft_market_id", nullable = false)
    private Long nftMarketId;

    @Lob
    @Column(name = "order_recipt")
    private String orderRecipt;

    @Column(name = "order_signature")
    private String orderSignature;

    @Column(name = "quantity")
    private Long quantity;

    @Column(name = "tx_id")
    private String txId;

    @CreatedDate
    @Column(name = "created_at")
    private Instant createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private Instant updatedAt;


}