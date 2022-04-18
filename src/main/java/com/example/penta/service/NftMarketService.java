package com.example.penta.service;

import com.example.penta.repository.NftMarketRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class NftMarketService {

    @Autowired
    private NftMarketRepository nftMarketRepository;

    
}
