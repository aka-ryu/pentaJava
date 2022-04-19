package com.example.penta.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDateTime;

@Entity
@Table(name = "nft_asset_metadata")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class NftAssetMetadatum {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nft_asset_id")
    private NftAsset nftAsset;

    @Lob
    @Column(name = "image_url")
    private String imageUrl;

    @Lob
    @Column(name = "name")
    private String name;

    @Lob
    @Column(name = "external_link")
    private String externalLink;

    @Lob
    @Column(name = "description")
    private String description;

    @Lob
    @Column(name = "image_preview_url")
    private String imagePreviewUrl;

    @Lob
    @Column(name = "image_thumbnail_url")
    private String imageThumbnailUrl;

    @Lob
    @Column(name = "image_original_url")
    private String imageOriginalUrl;

    @Lob
    @Column(name = "animation_url")
    private String animationUrl;

    @Lob
    @Column(name = "animation_original_url")
    private String animationOriginalUrl;

    @Lob
    @Column(name = "extra_metadata")
    private String extraMetadata;

    @Lob
    @Column(name = "more_info_url")
    private String moreInfoUrl;

    @Column(name = "is_freeze", nullable = false)
    private Integer isFreeze;

    @Column(name = "nft_type")
    private Integer nftType;

    @Column(name = "symbol")
    private String symbol;

    @Lob
    @Column(name = "original_asset")
    private String originalAsset;

    @Lob
    @Column(name = "proof_origin")
    private String proofOrigin;

    @Lob
    @Column(name = "ownership")
    private String ownership;

    @Lob
    @Column(name = "issuer_nft")
    private String issuerNft;

    @Lob
    @Column(name = "guarantee_issue")
    private String guaranteeIssue;

    @Column(name = "serial_number")
    private String serialNumber;

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;


}