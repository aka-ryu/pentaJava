package com.example.penta.dto.protocol.request.auth;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterReqDTO {

    @NotBlank
    private String wallet_address;
    @NotBlank
    private String blockchain;
    private String signature;
}
