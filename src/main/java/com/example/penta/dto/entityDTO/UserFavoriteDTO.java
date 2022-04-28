package com.example.penta.dto.entityDTO;

import com.example.penta.entity.User;
import lombok.*;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserFavoriteDTO {

    private Long id;
    private User user;
    private String contractAddress;
    private String tokenId;
}
