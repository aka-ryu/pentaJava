package com.example.penta.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.Instant;
import java.time.LocalDateTime;

@Entity
@Table(name = "password_resets")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class PasswordReset {
    @Id
    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "token", nullable = false)
    private String token;

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;


}