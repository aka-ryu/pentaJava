package com.example.penta.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_collections")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class UserCollection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "img_logo")
    private String imgLogo;

    @Column(name = "img_feature")
    private String imgFeature;

    @Column(name = "img_banner")
    private String imgBanner;

    @Column(name = "name")
    private String name;

    @Lob
    @Column(name = "url")
    private String url;

    @Lob
    @Column(name = "description")
    private String description;

    @Column(name = "category")
    private String category;

    @Column(name = "link_mysite")
    private String linkMysite;

    @Column(name = "link_insta")
    private String linkInsta;

    @Column(name = "royalties")
    private Double royalties;

    @Column(name = "blockchain")
    private String blockchain;

    @Column(name = "payment_asset")
    private String paymentAsset;

    @Column(name = "display_theme")
    private String displayTheme;

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;


}