package com.example.penta.dto;

import lombok.*;

import java.util.Date;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PentaFailMintDTO {

    private Long id;
    private Long blocknumber;
    private String txHash;
    private String status;
    private String signer;
    private String contractAddr;
    private Date blocktime;
}
