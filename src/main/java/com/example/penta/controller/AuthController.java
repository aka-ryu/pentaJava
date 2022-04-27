package com.example.penta.controller;

import com.example.penta.dto.protocol.request.LoginRequestDTO;
import com.example.penta.dto.protocol.response.LoginResponseDTO;
import com.example.penta.dto.protocol.response.ProfileInfoResponseDTO;
import com.example.penta.dto.protocol.response.ResponseDTO;
import com.example.penta.dto.protocol.response.TokenInfoResponseDTO;
import com.example.penta.entity.User;
import com.example.penta.service.AuthService;
import com.mysql.cj.x.protobuf.Mysqlx;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Log4j2
@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> userRegister(LoginRequestDTO loginRequestDTO) {

        log.info("레지스터 진입");
        try {
            User user = authService.registerUser(loginRequestDTO);
            LoginResponseDTO loginResponseDTO = authService.loginUser(loginRequestDTO, user);


            ResponseDTO responseDTO = ResponseDTO.builder()
                    .success(true)
                    .code(200)
                    .data(loginResponseDTO)
                    .build();

            return ResponseEntity.ok().body(responseDTO);

        } catch (Exception e) {
            log.warn(e.getMessage());
            String errMsg = e.getMessage();
            ResponseDTO responseDTO = ResponseDTO.builder()
                    .success(false)
                    .code(401)
                    .message(errMsg)
                    .build();

            return ResponseEntity.ok().body(responseDTO);
        }
    }

//    @PostMapping
//    public void userLogout(@RequestHeader("Authorization") String token) {
//
//    }

    @PostMapping("/tokeninfo")
    public ResponseEntity<?> tokenInfo(LoginRequestDTO loginRequestDTO, @AuthenticationPrincipal String userId) {

        log.info("토큰인포 진입");
        try {
            TokenInfoResponseDTO tokenInfoResponseDTO = authService.tokenInfo(loginRequestDTO);
            ResponseDTO responseDTO = ResponseDTO.builder()
                    .success(true)
                    .data(tokenInfoResponseDTO)
                    .message("User profile lookup successfully.")
                    .code(200)
                    .build();

            return ResponseEntity.ok().body(responseDTO);
        } catch (Exception e) {
            log.warn(e.getMessage());
            String errMsg = e.getMessage();

            ResponseDTO responseDTO = ResponseDTO.builder()
                    .success(false)
                    .code(401)
                    .message(errMsg)
                    .build();

            return ResponseEntity.ok().body(responseDTO);
        }
    }

    @PostMapping("/profileinfo")
    public ResponseEntity<?> profileInfo(@AuthenticationPrincipal String userId, LoginRequestDTO loginRequestDTO) {

        try {
            ProfileInfoResponseDTO profileInfoResponseDTO = authService.profileInfo(loginRequestDTO);
            ResponseDTO responseDTO = ResponseDTO.builder()
                    .success(true)
                    .data(profileInfoResponseDTO)
                    .message("User profile lookup successfully.")
                    .code(200)
                    .build();

            return ResponseEntity.ok().body(responseDTO);
        } catch (Exception e) {
            log.warn(e.getMessage());
            String errMsg = e.getMessage();

            ResponseDTO responseDTO = ResponseDTO.builder()
                    .success(false)
                    .code(401)
                    .message(errMsg)
                    .build();

            return ResponseEntity.ok().body(responseDTO);
        }
    }

}
