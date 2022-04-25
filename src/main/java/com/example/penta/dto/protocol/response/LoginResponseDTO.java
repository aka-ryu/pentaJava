package com.example.penta.dto.protocol.response;

import lombok.*;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponseDTO {

    private Long token_id;
    private String token;
    private String token_full;
    private String nonce;
    private String wallet_address;
    private String signature;
    private int is_certified;
}
