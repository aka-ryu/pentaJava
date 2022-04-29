package com.example.penta.service;

import com.example.penta.dto.protocol.request.asset.AssetInfoReqDTO;
import com.example.penta.entity.NftAsset;
import com.example.penta.entity.User;
import com.example.penta.repository.NftAssetRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class AssetTests {

    @Autowired
    private AuthService authService;

    @Autowired
    private NftAssetRepository nftAssetRepository;

    @Test
    public void assetinfoTests() {

        AssetInfoReqDTO assetInfoReqDTO = AssetInfoReqDTO.builder()
                .wallet_address("0xe33f5e0c73b19c13f873ac9ccf1e17f4735cd2fe")
                .blockchain("ethereum")
                .select("all")
                .build();

        String walletAddress = assetInfoReqDTO.getWallet_address();
        String blockChain = assetInfoReqDTO.getBlockchain();
        String select = assetInfoReqDTO.getSelect();

        User user = authService.logedUserValidation(walletAddress, blockChain);

        List<?> assetInfo = new ArrayList<>();

        if(select.equals("all")) {
            assetInfo = nftAssetRepository.findByCreatorAddressAndOwnerAddress(walletAddress, walletAddress);
        } else if (select.equals("created")) {
            assetInfo = nftAssetRepository.findByCreatorAddress(walletAddress);
        } else if (select.equals("owned")) {
            assetInfo = nftAssetRepository.findAllByOwnerAddress(walletAddress);
        }

        if(assetInfo.isEmpty()) {

        }
    }
}
