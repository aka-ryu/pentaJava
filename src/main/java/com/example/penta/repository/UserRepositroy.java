package com.example.penta.repository;

import com.example.penta.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositroy extends JpaRepository<User, Long> {

    User findByWalletAddressAndBlockchain(String walletAddress, String blockchain);

    User findByIdAndWalletAddressAndBlockchain(Long id, String walletAddress, String blockchain);
}
