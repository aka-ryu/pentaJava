package com.example.penta.repository;

import com.example.penta.entity.NftEventHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NftEventHistoryRepository extends JpaRepository<NftEventHistory, Long> {

    // myBidCount
    @Query("SELECT COUNT(n) FROM NftEventHistory n WHERE n.event = 'bid' AND n.fromAddress = ?1 AND n.nftMarketId IN ?2")
    Long myBidCount(String fromAddress, List<Long> auctionIds);
}

