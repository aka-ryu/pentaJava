package com.example.penta.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "wallet_address", nullable = false)
    private String walletAddress;

    @Column(name = "blockchain", nullable = false)
    private String blockchain;

    @Column(name = "uuid_user", nullable = false)
    private String uuidUser;

    @Column(name = "deposit_fund")
    private Double depositFund;

    @Column(name = "remember_token", length = 100)
    private String rememberToken;

    @Column(name = "is_certified", nullable = false)
    private Integer isCertified;

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

}