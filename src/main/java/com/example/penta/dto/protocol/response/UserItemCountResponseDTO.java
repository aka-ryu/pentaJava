package com.example.penta.dto.protocol.response;

import lombok.*;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserItemCountResponseDTO {

    private int allAssetCount;
    private int createCount;
    private int ownerCount;
    private int favoriteCount;
    private int displayCount;
    private int myBidCount;
    private int freezCount;

}
