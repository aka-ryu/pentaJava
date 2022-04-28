package com.example.penta.dto.entityDTO;

import com.example.penta.entity.User;
import lombok.*;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserCollectionDTO {

    private Long id;
    private User user;
    private String imgLogo;
    private String imgFeature;
    private String imgBanner;
    private String name;
    private String url;
    private String description;
    private String category;
    private String linkMysite;
    private String linkInsta;
    private Double royalties;
    private String blockchain;
    private String paymentAsset;
    private String displayTheme;
}
