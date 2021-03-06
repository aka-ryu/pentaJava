package com.example.penta.dto.entityDTO;

import lombok.*;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Long id;
    private String walletAddress;
    private String blockchain;
    private String uuidUser;
    private Double depositFund;
    private String rememberToken;
    private Integer isCertified;


}
