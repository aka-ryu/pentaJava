package com.example.penta.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;

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
    private Date blocktime;

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;


}