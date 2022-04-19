package com.example.penta.dto;

import com.example.penta.entity.FailedJob;
import lombok.*;

import java.util.Date;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FailedJobDTO {

    private Long id;
    private String uuid;
    private String connection;
    private String queue;
    private String payload;
    private String exception;


}
