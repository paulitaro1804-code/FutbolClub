package com.innovation.futbolclub.repository;

import com.innovation.futbolclub.model.Club;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ClubRepository extends MongoRepository<Club, String> { }
