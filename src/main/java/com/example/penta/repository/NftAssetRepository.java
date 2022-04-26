package com.example.penta.repository;

import com.example.penta.entity.NftAsset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NftAssetRepository extends JpaRepository<NftAsset, Long> {

    List<NftAsset> findAllByOwnerAddress(String ownerAddress);

    @Query("SELECT n.id FROM NftAsset n WHERE n.ownerAddress = ?1 GROUP BY n.tokenId")
    List<Long> findIdList(String ownerAddress);






}
