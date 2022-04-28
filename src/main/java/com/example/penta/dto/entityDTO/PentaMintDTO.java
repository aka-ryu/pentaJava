package com.example.penta.dto.entityDTO;

import lombok.*;

import java.util.Date;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PentaMintDTO {

    private Long id;
    private String txHash;
    private Long blocknumber;
    private String eventname;
    private String fromAddress;
    private String toAddress;
    private String tokenid;
    private String tokenAmount;
    private String tokenType;
    private String tokenSupply;
    private Date blocktime;
}
