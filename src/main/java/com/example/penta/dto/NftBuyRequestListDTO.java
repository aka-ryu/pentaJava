package com.example.penta.dto;

import com.example.penta.entity.NftMarket;
import com.example.penta.entity.User;
import lombok.*;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NftBuyRequestListDTO {

    private Long id;
    private String uuidBuy;
    private User user;
    private NftMarket nftMarket;
    private String tokenId;
    private String contractAddress;
    private String txId;
    private Long buyCount;
    private Integer requestStatus;
    private String orderRecipt;
    private String orderSignature;
}
