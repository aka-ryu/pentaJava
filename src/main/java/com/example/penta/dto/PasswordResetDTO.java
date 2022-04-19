package com.example.penta.dto;

import com.example.penta.entity.PasswordReset;
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
