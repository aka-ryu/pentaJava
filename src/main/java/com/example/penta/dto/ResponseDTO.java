package com.example.penta.dto;

import lombok.*;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDTO {

    String token_id;
    String token;
    String token_full;
    String nonce;
    String wallet_address;
    String signature;
    String is_certified;
}
