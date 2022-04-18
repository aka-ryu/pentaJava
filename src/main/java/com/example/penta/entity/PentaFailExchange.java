package com.example.penta.entity;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "penta_fail_exchange")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class PentaFailExchange {
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

    @Column(name = "cancel_payment")
    private String cancelPayment;

    @Column(name = "blocktime")
    private Instant blocktime;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;


}