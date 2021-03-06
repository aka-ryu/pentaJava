package com.example.penta.repository;

import com.example.penta.entity.User;
import com.example.penta.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepositroy extends JpaRepository<User, Long> {


    Optional<User> findByWalletAddressAndBlockchain(String walletAddress, String blockchain);

    User findByIdAndWalletAddressAndBlockchain(Long id, String walletAddress, String blockchain);



}
