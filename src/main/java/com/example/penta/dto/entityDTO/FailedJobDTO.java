package com.example.penta.dto.entityDTO;

import lombok.*;

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
