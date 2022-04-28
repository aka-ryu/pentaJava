package com.example.penta.controller;

import com.example.penta.dto.protocol.request.auth.RegisterReqDTO;
import com.example.penta.dto.protocol.response.LoginResponseDTO;
import com.example.penta.dto.protocol.response.ResponseDTO;
import com.example.penta.entity.User;
import com.example.penta.service.AuthService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Log4j2
@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> userRegister(@Valid RegisterReqDTO registerReqDTO) {

        log.info("레지스터 진입");
        try {
            User user = authService.registerUser(registerReqDTO);
            LoginResponseDTO loginResponseDTO = authService.loginUser(registerReqDTO, user);


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


    @PostMapping("/logout")
    public void userLogout(@AuthenticationPrincipal String token) {
        authService.logoutUser(token);
    }
}
