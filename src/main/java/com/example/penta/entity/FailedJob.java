package com.example.penta.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "failed_jobs")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class FailedJob {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "uuid", nullable = false)
    private String uuid;

    @Lob
    @Column(name = "connection", nullable = false)
    private String connection;

    @Lob
    @Column(name = "queue", nullable = false)
    private String queue;

    @Lob
    @Column(name = "payload", nullable = false)
    private String payload;

    @Lob
    @Column(name = "exception", nullable = false)
    private String exception;

    @CreatedDate
    @Column(name = "failed_at", nullable = false)
    private LocalDateTime failedAt;

}