package com.example.keycloack.repository;

import com.example.keycloack.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByIdTelegram(String id);
}
