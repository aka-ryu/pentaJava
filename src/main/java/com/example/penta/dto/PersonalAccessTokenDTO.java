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

    public PersonalAccessTokenDTO(PersonalAccessToken personalAccessToken) {
        this.id = personalAccessToken.getId();
        this.tokenalbeType = personalAccessToken.getTokenableType();
        this.tokenableId = personalAccessToken.getTokenableId();
        this.name = personalAccessToken.getName();
        this.token = personalAccessToken.getToken();
        this.nonce = personalAccessToken.getNonce();
        this.abilities = personalAccessToken.getAbilities();
        this.lastUsedAt = personalAccessToken.getLastUsedAt();
    }

    public static PersonalAccessToken toEntity (PersonalAccessTokenDTO personalAccessTokenDTO) {
        return PersonalAccessToken.builder()
                .id(personalAccessTokenDTO.getId())
                .tokenableType(personalAccessTokenDTO.getTokenalbeType())
                .tokenableId(personalAccessTokenDTO.getTokenableId())
                .name(personalAccessTokenDTO.getName())
                .token(personalAccessTokenDTO.getToken())
                .nonce(personalAccessTokenDTO.getNonce())
                .abilities(personalAccessTokenDTO.getAbilities())
                .lastUsedAt(personalAccessTokenDTO.getLastUsedAt())
                .build();
    }
}
