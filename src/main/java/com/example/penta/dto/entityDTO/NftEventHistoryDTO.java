package com.example.penta.dto.entityDTO;

import lombok.*;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NftEventHistoryDTO {

    private Long id;
    private String contractAddress;
    private String tokenId;
    private String event;
    private Double price;
    private String fromAddress;
    private String toAddress;
    private Long nftMarketId;
    private String orderRecipt;
    private String orderSignature;
    private Long quantity;
    private String txId;
}
