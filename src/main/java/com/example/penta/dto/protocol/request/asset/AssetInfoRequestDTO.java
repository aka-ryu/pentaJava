package com.example.penta.dto.protocol.request.asset;

import javax.validation.constraints.NotNull;

public class AssetInfoRequestDTO {

    @NotNull
    private String wallet_address;
    @NotNull
    private String blockchain;

    private String select;
    private String owned_status;
}
