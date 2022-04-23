package com.example.penta.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.parameters.P;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "personal_access_tokens")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@EntityListeners(AuditingEntityListener.class)
public class PersonalAccessToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "tokenable_type", nullable = false)
    private String tokenableType;

    @Column(name = "tokenable_id", nullable = false)
    private Long tokenableId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "token", nullable = false, length = 64)
    private String token;

    @Column(name = "nonce", length = 6)
    private String nonce;

    @Lob
    @Column(name = "abilities")
    private String abilities;

    @Column(name = "last_used_at")
    private Date lastUsedAt;

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public static PersonalAccessToken lastUsedAt(PersonalAccessToken personalAccessToken) {
        PersonalAccessToken updateToken = PersonalAccessToken.builder()
                .id(personalAccessToken.getId())
                .tokenableType(personalAccessToken.getTokenableType())
                .tokenableId(personalAccessToken.getTokenableId())
                .name(personalAccessToken.getName())
                .token(personalAccessToken.getToken())
                .lastUsedAt(new Date())
                .createdAt(personalAccessToken.getCreatedAt())
                .build();

        return updateToken;
    }
}