package com.example.penta.dto.entityDTO;

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
