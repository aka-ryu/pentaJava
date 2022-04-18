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

    public PasswordResetDTO (PasswordReset passwordReset) {
        this.email = passwordReset.getEmail();
        this.token = passwordReset.getToken();
    }

    public static PasswordReset toEntity(PasswordResetDTO passwordResetDTO) {
        return PasswordReset.builder()
                .email(passwordResetDTO.getEmail())
                .token(passwordResetDTO.getToken())
                .build();
    }
}
