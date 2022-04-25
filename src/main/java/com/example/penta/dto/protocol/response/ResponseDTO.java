package com.example.penta.dto.protocol.response;

import lombok.*;

import java.util.List;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDTO {

    private boolean success;
    private Object data;
    private String message;
    private int code;
}
