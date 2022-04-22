//package com.example.penta.service;
//
//import com.example.penta.dto.RequestDTO;
//import com.example.penta.dto.UserDTO;
//import com.example.penta.entity.User;
//import com.example.penta.entity.UserProfile;
//import com.example.penta.repository.UserProfileRepository;
//import com.example.penta.repository.UserRepositroy;
//import lombok.extern.java.Log;
//import lombok.extern.log4j.Log4j2;
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.UUID;
//
//@Service
//@Log4j2
//public class UserService {
//
//    @Autowired
//    private UserRepositroy userRepositroy;
//
//    @Autowired
//    private UserProfileRepository userProfileRepository;
//
//    @Autowired
//    private ModelMapper modelMapper;
//
//
//    //유저 회원가입
//    public void registerUser(RequestDTO requestDTO){
//
////        // 필수값 유효성검사
////        if(requestDTO.getBlockchain() == null || requestDTO.getWallet_address() == null) {
////            log.info("Error validation");
////            throw new RuntimeException("Error validation");
////        }
//
//        // 이미 존재하는 회원인지 체크
//        User getUser = userRepositroy.findByWalletAddressAndBlockchain(
//                requestDTO.getWalletaddress(),
//                requestDTO.getBlockchain()
//        );
//
//
//        // 유저가 이미 있다면 login 메서드로 이동
//        if(getUser != null) {
//            UserDTO userDTO = modelMapper.map(getUser, UserDTO.class);
//            loginUser(userDTO);
//
//            //유저가 없다면 User, UserProfile 생성 후 login 메서드로 이동
//        } else {
//            String uuid = UUID.randomUUID().toString();
//
//            try {
//                User registerUser = User.builder()
//                        .walletAddress(requestDTO.getWalletaddress())
//                        .blockchain(requestDTO.getBlockchain())
//                        .uuidUser(uuid)
//                        .build();
//
//                userRepositroy.save(registerUser);
//
//                UserProfile userProfile = UserProfile.builder()
//                        .id(registerUser.getId())
//                        .userName("NoName")
//                        .build();
//
//                userProfileRepository.save(userProfile);
//
//                User user = userRepositroy.findByIdAndWalletAddressAndBlockchain(
//                        registerUser.getId(),
//                        registerUser.getWalletAddress(),
//                        registerUser.getBlockchain()
//                );
//
//                UserDTO userDTO = modelMapper.map(user, UserDTO.class);
//                loginUser(userDTO);
//
//            } catch (Exception e) {
//                log.info(e.getMessage());
//            }
//
//        }
//
//    }
//
//    public void loginUser(UserDTO userDTO) {
//
//        log.info("ㅎㅇ");
//    }
//}
