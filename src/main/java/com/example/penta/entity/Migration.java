package com.example.penta.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "migrations")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Migration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "migration", nullable = false)
    private String migration;

    @Column(name = "batch", nullable = false)
    private Integer batch;


}