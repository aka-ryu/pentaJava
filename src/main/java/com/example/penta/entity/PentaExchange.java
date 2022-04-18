package com.example.penta.entity;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "penta_exchange")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class PentaExchange {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "blocknumber", nullable = false)
    private Long blocknumber;

    @Column(name = "tx_hash")
    private String txHash;

    @Column(name = "event")
    private String event;

    @Column(name = "buyer")
    private String buyer;

    @Column(name = "seller")
    private String seller;

    @Column(name = "tokenid")
    private String tokenid;

    @Column(name = "value")
    private String value;

    @Column(name = "token_type")
    private String tokenType;

    @Column(name = "royalty")
    private String royalty;

    @Column(name = "royalty_recipient")
    private String royaltyRecipient;

    @Column(name = "fee")
    private String fee;

    @Column(name = "actual_amount")
    private String actualAmount;

    @Column(name = "price")
    private String price;

    @Column(name = "blocktime")
    private Instant blocktime;

    @Column(name = "is_check", nullable = false)
    private Integer isCheck;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;


}