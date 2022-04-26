package com.example.penta.dto.protocol.response;

import com.example.penta.entity.PersonalAccessToken;
import lombok.*;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TokenInfoResponseDTO {

    private String wallet_address;
    private PersonalAccessToken personalAccessToken;

}
