package com.example.penta.dto.protocol.response;

import com.example.penta.entity.UserProfile;
import lombok.*;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProfileInfoResponseDTO {

    private String wallet_address;

    private UserProfile userProfile;

    private int is_certified;

}
