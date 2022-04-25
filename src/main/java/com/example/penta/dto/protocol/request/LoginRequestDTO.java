package com.example.penta.dto.protocol.request;

import lombok.*;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestDTO {
    private String wallet_address;
    private String blockchain;
    private String signature;
}
