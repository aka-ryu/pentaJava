package com.example.penta.dto.protocol.request.asset;

import lombok.*;

import javax.validation.constraints.NotNull;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AssetInfoReqDTO {

    @NotNull
    private String wallet_address;
    @NotNull
    private String blockchain;
    @NotNull
    private String select;
    private String owned_status;
}
