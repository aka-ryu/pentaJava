package com.example.penta.dto;

import com.example.penta.entity.NftAsset;
import com.example.penta.entity.NftAssetMetadatum;
import lombok.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NftAssetMetadatumDTO {

    @Autowired
    private ModelMapper modelMapper;

    private Long id;
    private NftAsset nftAsset;
    private String imageUrl;
    private String name;
    private String externalLink;
    private String description;
    private String imagePreviewUrl;
    private String imageThumbnailUrl;
    private String imageOriginalUrl;
    private String animationUrl;
    private String animationOriginalUrl;
    private String extraMetadata;
    private String moreInfoUrl;
    private Integer isFreeze;
    private Integer nftType;
    private String symbol;
    private String originalAsset;
    private String proofOrigin;
    private String ownership;
    private String issuerNft;
    private String guaranteeIssue;
    private String serialNumber;







}
