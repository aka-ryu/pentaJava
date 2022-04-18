package com.example.penta.dto;

import com.example.penta.entity.User;
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

    public UserDTO(User user) {
        this.id = user.getId();
        this.walletAddress = user.getWalletAddress();
        this.blockchain = user.getBlockchain();
        this.uuidUser = user.getUuidUser();
        this.depositFund = user.getDepositFund();
        this.rememberToken = user.getRememberToken();
        this.isCertified = user.getIsCertified();
    }

    public static User toEntity(UserDTO userDTO) {
        return User.builder()
                .id(userDTO.getId())
                .walletAddress(userDTO.getWalletAddress())
                .blockchain(userDTO.getBlockchain())
                .uuidUser(userDTO.getUuidUser())
                .depositFund(userDTO.getDepositFund())
                .rememberToken(userDTO.getRememberToken())
                .isCertified(userDTO.getIsCertified())
                .build();
    }
}
