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

    public FailedJobDTO(FailedJob failedJob) {
        this.id = failedJob.getId();
        this.uuid = failedJob.getUuid();
        this.connection = failedJob.getConnection();
        this.queue = failedJob.getQueue();
        this.payload = failedJob.getPayload();
        this.exception = failedJob.getException();
    }

 public static FailedJob toEntity(FailedJobDTO failedJobDTO) {
        return FailedJob.builder()
                .id(failedJobDTO.getId())
                .uuid(failedJobDTO.getUuid())
                .connection(failedJobDTO.getConnection())
                .queue(failedJobDTO.getQueue())
                .payload(failedJobDTO.getPayload())
                .exception(failedJobDTO.getException())
                .build();
 }

}
