package com.example.keycloack.repository;

import com.example.keycloack.models.Messages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Messages, Long> {

}
