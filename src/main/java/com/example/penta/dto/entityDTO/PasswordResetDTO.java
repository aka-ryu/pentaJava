package com.example.penta.dto.entityDTO;

import lombok.*;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PasswordResetDTO {

    private String email;
    private String token;


}
