package com.example.penta.repository;

import com.example.penta.entity.User;
import com.example.penta.entity.UserFavorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserFavoriteRepository extends JpaRepository<UserFavorite, Long> {

    List<UserFavorite> findByUser(User user);
}
