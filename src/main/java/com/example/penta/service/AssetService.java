package com.example.penta.service;

import com.example.penta.dto.protocol.request.asset.AssetInfoReqDTO;
import com.example.penta.entity.User;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class AssetService {

    @Autowired
    private AuthService authService;

    private void assetInfo(AssetInfoReqDTO assetInfoReqDTO) {

       User user = authService.logedUserValidation(assetInfoReqDTO.getWallet_address(), assetInfoReqDTO.getBlockchain());
    }
}
