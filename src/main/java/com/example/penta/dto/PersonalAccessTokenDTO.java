package com.example.penta.dto;

import com.example.penta.entity.PersonalAccessToken;
import lombok.*;

import java.util.Date;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonalAccessTokenDTO {

    private Long id;
    private String tokenalbeType;
    private Long tokenableId;
    private String name;
    private String token;
    private String nonce;
    private String abilities;
    private Date lastUsedAt;


}
