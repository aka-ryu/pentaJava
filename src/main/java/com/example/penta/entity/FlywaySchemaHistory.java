package com.example.penta.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.Instant;

@Entity
@Table(name = "flyway_schema_history")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class FlywaySchemaHistory {
    @Id
    @Column(name = "installed_rank", nullable = false)
    private Integer id;

    @Column(name = "version", length = 50)
    private String version;

    @Column(name = "description", nullable = false, length = 200)
    private String description;

    @Column(name = "type", nullable = false, length = 20)
    private String type;

    @Column(name = "script", nullable = false, length = 1000)
    private String script;

    @Column(name = "checksum")
    private Integer checksum;

    @Column(name = "installed_by", nullable = false, length = 100)
    private String installedBy;

    @Column(name = "installed_on", nullable = false)
    private Instant installedOn;

    @Column(name = "execution_time", nullable = false)
    private Integer executionTime;

    @Column(name = "success", nullable = false)
    private Boolean success = false;


}