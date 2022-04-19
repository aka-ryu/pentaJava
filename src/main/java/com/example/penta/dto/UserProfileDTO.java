package com.example.penta.dto;

import com.example.penta.entity.User;
import com.example.penta.entity.UserProfile;
import lombok.*;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileDTO {

    private Long id;
    private User user;
    private String userName;
    private String userBio;
    private String userEmail;
    private String imageBanner;
    private String imageProfile;
    private String instagramId;
    private String instagramLink;
    private String googleId;
    private String googleToken;
    private String kakaoklipId;



}
