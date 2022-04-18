package com.example.penta.entity;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "penta_mint")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class PentaMint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "tx_hash")
    private String txHash;

    @Column(name = "blocknumber", nullable = false)
    private Long blocknumber;

    @Column(name = "eventname")
    private String eventname;

    @Column(name = "from_address")
    private String fromAddress;

    @Column(name = "to_address")
    private String toAddress;

    @Column(name = "tokenid")
    private String tokenid;

    @Column(name = "token_amount")
    private String tokenAmount;

    @Column(name = "token_type")
    private String tokenType;

    @Column(name = "token_supply")
    private String tokenSupply;

    @Column(name = "blocktime")
    private Instant blocktime;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;


}