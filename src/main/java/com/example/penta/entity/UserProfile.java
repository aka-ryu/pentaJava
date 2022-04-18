package com.example.penta.entity;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "user_profiles")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "user_name")
    private String userName;

    @Lob
    @Column(name = "user_bio")
    private String userBio;

    @Column(name = "user_email")
    private String userEmail;

    @Column(name = "image_banner")
    private String imageBanner;

    @Column(name = "image_profile")
    private String imageProfile;

    @Column(name = "instagram_id")
    private String instagramId;

    @Column(name = "instagram_link")
    private String instagramLink;

    @Column(name = "google_id")
    private String googleId;

    @Column(name = "google_token")
    private String googleToken;

    @Column(name = "kakaoklip_id")
    private String kakaoklipId;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;

}