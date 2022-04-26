package com.example.penta.repository;

import com.example.penta.entity.NftAsset;
import com.example.penta.entity.NftMarket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NftMarketRepository extends JpaRepository<NftMarket, Long> {

    @Query("SELECT n.nftAsset FROM NftMarket n WHERE n.isEnd = 0 AND n.nftAsset.id IN ?1")
    List<Long> marketAsset(List<Long> owendIds);
}
