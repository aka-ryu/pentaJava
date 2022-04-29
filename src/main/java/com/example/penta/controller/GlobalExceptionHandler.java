package com.example.penta.controller;

import com.example.penta.dto.protocol.response.ResponseDTO;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RestController
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<?> handleExeption(Exception e) {

        ResponseDTO responseDTO = ResponseDTO.builder()
                .success(false)
                .message("에러, 예외발생")
                .data(e.getMessage())
                .code(400)
                .build();

        return ResponseEntity.ok().body(responseDTO);
    }

    @ExceptionHandler(value = ExpiredJwtException.class)
    public void jwthandle(ExpiredJwtException e) {
        System.out.println("리프레시 토큰내놔~!");
    }
}
