package com.example.penta.entity;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "penta_fail_mint")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class PentaFailMint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "blocknumber", nullable = false)
    private Long blocknumber;

    @Column(name = "tx_hash")
    private String txHash;

    @Column(name = "status")
    private String status;

    @Column(name = "signer")
    private String signer;

    @Column(name = "contract_addr")
    private String contractAddr;

    @Column(name = "blocktime")
    private Instant blocktime;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;


}