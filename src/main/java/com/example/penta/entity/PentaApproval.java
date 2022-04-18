package com.example.penta.entity;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "penta_approval")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class PentaApproval {
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

    @Column(name = "owner")
    private String owner;

    @Column(name = "operator")
    private String operator;

    @Column(name = "approved")
    private String approved;

    @Column(name = "ca")
    private String ca;

    @Column(name = "tokenid")
    private String tokenid;

    @Column(name = "blocktime")
    private Instant blocktime;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;


}