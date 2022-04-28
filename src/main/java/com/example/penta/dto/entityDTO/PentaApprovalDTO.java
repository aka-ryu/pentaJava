package com.example.penta.dto.entityDTO;

import lombok.*;

import java.util.Date;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PentaApprovalDTO {

    private Long id;
    private Long blocknumber;
    private String txHash;
    private String event;
    private String owner;
    private String operator;
    private String approved;
    private String ca;
    private String tokenid;
    private Date blocktime;
}
