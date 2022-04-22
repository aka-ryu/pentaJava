package com.example.penta.dto;

import lombok.*;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestDTO {

    String walletaddress;
    String blockchain;
    String signature;

}
