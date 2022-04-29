package com.example.penta.repository;

import com.example.penta.entity.NftAsset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NftAssetRepository extends JpaRepository<NftAsset, Long> {

    List<NftAsset> findByCreatorAddress(String creatorAddress);

    // assetInfo
    List<NftAsset> findAllByOwnerAddress(String ownerAddress);

    List<NftAsset> findByCreatorAddressAndOwnerAddress(String CreatorAddress, String OwnedAddress);

    // arrayIds
    @Query("SELECT n.id FROM NftAsset n WHERE n.ownerAddress = ?1")
    List<Long> arrayIds(String ownerAddress);

    // ownedInfo
    @Query("SELECT n FROM NftAsset n WHERE n.ownerAddress = ?1 GROUP BY n.tokenId")
    List<NftAsset> ownedInfo(String ownerAddress);

    // ownedIds
    @Query("SELECT n.id FROM NftAsset n WHERE n.ownerAddress = ?1 GROUP BY n.tokenId")
    List<Long> ownedIds(String ownerAddress);

    // assetInfo01
    @Query("SELECT n.id FROM NftAsset n WHERE n.id = ?1 AND n.ownerAddress = ?2 AND n.quantity > 0")
    Optional<Long> optionalAssetInfo01(Long id, String ownerAddress);

    // assetInfo02 endIds 널용
    @Query("SELECT n.id FROM NftAsset n WHERE n.id = ?1 AND n.ownerAddress = ?2 AND n.quantity = 0")
    Optional<Long> optionalAssetInfo02Null(Long id, String ownerAddress);

    // assetInfo02
    @Query("SELECT n.id FROM NftAsset n WHERE n.id = ?1 AND n.ownerAddress = ?2 AND n.quantity = 0 AND n.id NOT IN ?3")
    Optional<Long> optionalAssetInfo02NotNull(Long id, String ownerAddress, List<Long> endIds);
    // assetIds
    @Query("SELECT n FROM NftAsset n WHERE n.ownerAddress = ?1 AND n.id IN ?2")
    List<NftAsset> assetIds(String ownerAddress, List<Long> ids);

    // allAsset
    @Query("SELECT COUNT(DISTINCT n.tokenId) FROM NftAsset n WHERE n.creatorAddress = ?1 OR n.ownerAddress = ?2")
    Long allAssetCount(String creatorAddress, String ownerAddress);

    // freezeCount
    @Query("SELECT COUNT(DISTINCT n.tokenId) FROM NftAsset n WHERE n.ownerAddress = ?1 AND n.isMint = 0")
    Long freezeCount(String ownerAddress);


    // ownerCount
    @Query("SELECT COUNT(n) FROM NftAsset n WHERE n.ownerAddress = ?1 AND n.id IN ?2")
    int onwerCount(String ownerAddress, List<Long> assetIds);


    // assetInfo-all-assetInfo
    @Query("SELECT n FROM NftAsset n WHERE n.creatorAddress = ?1 AND n.ownerAddress = ?2")
    List<NftAsset> assetInfoAllAssetInfo(String creatorAddress, String ownerAddress);
}
