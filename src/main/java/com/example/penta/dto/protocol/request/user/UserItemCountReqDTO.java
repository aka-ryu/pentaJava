package com.example.penta.dto.protocol.request.user;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserItemCountReqDTO {

    @NotBlank
    private String wallet_address;

    @NotBlank
    private String blockchain;
}
