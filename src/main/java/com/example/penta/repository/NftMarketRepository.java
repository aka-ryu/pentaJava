package com.example.penta.repository;

import com.example.penta.entity.NftAsset;
import com.example.penta.entity.NftMarket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NftMarketRepository extends JpaRepository<NftMarket, Long> {

    //marketInfo
    @Query("SELECT n FROM NftMarket n WHERE n.isEnd = 0 AND n.nftAsset IN ?1")
    List<NftMarket> marketInfo(List<NftAsset> ownedInfo);




















    //marketAsset
    @Query("SELECT n.nftAsset.id FROM NftMarket n WHERE n.isEnd = 0 AND n.nftAsset IN ?1")
    List<Long> marketAsset(List<NftAsset> ownedInfo);












    //endInfo
    @Query("SELECT n FROM NftMarket n WHERE n.isEnd = 1 AND n.nftAsset.id NOT IN ?1")
    List<NftMarket> endInfo(List<Long> marketAsset);

    //endIds
    @Query("SELECT n.nftAsset.id FROM NftMarket n WHERE n.isEnd = 1 AND n.nftAsset.id NOT IN ?1")
    List<Long> endIds(List<Long> marketAsset);

    //displayCount
    @Query("SELECT COUNT(n) FROM NftMarket n WHERE n.isDisplay = 1 AND n.isEnd = 0 AND n.nftAsset.id IN ?1")
    Long displayCount(List<Long> arrayIds);

    //auctionIds
    @Query("SELECT n.id FROM NftMarket n WHERE n.isDisplay = 1 AND n.isEnd = 0 AND n.typeTrade = 1")
    List<Long> auctionIds();
}
