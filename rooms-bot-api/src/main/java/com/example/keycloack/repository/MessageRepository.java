package com.example.keycloack.repository;

import com.example.keycloack.models.Messages;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends MongoRepository<Messages, String> {

}
