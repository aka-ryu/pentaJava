package com.example.penta.dto.entityDTO;

import lombok.*;

import java.util.Date;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PentaExchangeDTO {

    private Long id;
    private Long blocknumber;
    private String txHash;
    private String event;
    private String buyer;
    private String seller;
    private String tokenid;
    private String value;
    private String tokenType;
    private String royalty;
    private String royaltyRecipient;
    private String fee;
    private String actualAmount;
    private String price;
    private Date blocktime;
    private Integer isCheck;
}
