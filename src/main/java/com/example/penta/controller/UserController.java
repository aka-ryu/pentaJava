package com.example.penta.controller;

import com.example.penta.dto.protocol.request.auth.RegisterReqDTO;
import com.example.penta.dto.protocol.request.user.UserItemCountReqDTO;
import com.example.penta.dto.protocol.response.ProfileInfoResponseDTO;
import com.example.penta.dto.protocol.response.ResponseDTO;
import com.example.penta.dto.protocol.response.TokenInfoResponseDTO;
import com.example.penta.dto.protocol.response.UserItemCountResponseDTO;
import com.example.penta.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@Log4j2
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/useritem_count")
    public ResponseEntity<?> userItemCount(@Valid UserItemCountReqDTO userItemCountReqDTO, @AuthenticationPrincipal String token){

            UserItemCountResponseDTO userItemCountResponseDTO = userService.userItemCount(userItemCountReqDTO);
            ResponseDTO responseDTO = ResponseDTO.builder()
                    .success(true)
                    .data(userItemCountResponseDTO)
                    .message("user item count successfully.")
                    .code(200)
                    .build();

            return ResponseEntity.ok().body(responseDTO);


    }

    @PostMapping("/tokeninfo")
    public ResponseEntity<?> tokenInfo(RegisterReqDTO registerReqDTO, @AuthenticationPrincipal String userId) {

            TokenInfoResponseDTO tokenInfoResponseDTO = userService.tokenInfo(registerReqDTO);
            ResponseDTO responseDTO = ResponseDTO.builder()
                    .success(true)
                    .data(tokenInfoResponseDTO)
                    .message("User profile lookup successfully.")
                    .code(200)
                    .build();

            return ResponseEntity.ok().body(responseDTO);

    }

    @PostMapping("/profileinfo")
    public ResponseEntity<?> profileInfo(@AuthenticationPrincipal String userId, RegisterReqDTO registerReqDTO) {

            ProfileInfoResponseDTO profileInfoResponseDTO = userService.profileInfo(registerReqDTO);
            ResponseDTO responseDTO = ResponseDTO.builder()
                    .success(true)
                    .data(profileInfoResponseDTO)
                    .message("User profile lookup successfully.")
                    .code(200)
                    .build();

            return ResponseEntity.ok().body(responseDTO);

    }
}
