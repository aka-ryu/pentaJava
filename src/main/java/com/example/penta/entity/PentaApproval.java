package com.example.penta.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;

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
    private Date blocktime;

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;


}