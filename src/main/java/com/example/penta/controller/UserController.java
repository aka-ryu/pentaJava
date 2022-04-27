package com.example.penta.controller;

import com.example.penta.dto.protocol.request.LoginRequestDTO;
import com.example.penta.dto.protocol.response.ResponseDTO;
import com.example.penta.dto.protocol.response.TokenInfoResponseDTO;
import com.example.penta.dto.protocol.response.UserItemCountResponseDTO;
import com.example.penta.service.UserItemCountService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Log4j2
public class UserController {

    @Autowired
    private UserItemCountService userItemCountService;

    @RequestMapping("/useritem_count")
    public ResponseEntity<?> userItemCount(LoginRequestDTO loginRequestDTO, @AuthenticationPrincipal String userId){

        
        log.info("유저아이템 컨트롤러 접속");
        try {
            UserItemCountResponseDTO userItemCountResponseDTO = userItemCountService.userItemCount(loginRequestDTO);
            ResponseDTO responseDTO = ResponseDTO.builder()
                    .success(true)
                    .data(userItemCountResponseDTO)
                    .message("user item count successfully.")
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
