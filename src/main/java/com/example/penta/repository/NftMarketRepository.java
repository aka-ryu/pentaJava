package com.example.penta.repository;

import com.example.penta.entity.NftMarket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NftMarketRepository extends JpaRepository<NftMarket, Long> {
}
