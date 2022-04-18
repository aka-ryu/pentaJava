package com.example.penta.entity;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "user_favorites")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class UserFavorite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "contract_address")
    private String contractAddress;

    @Column(name = "token_id")
    private String tokenId;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;


}