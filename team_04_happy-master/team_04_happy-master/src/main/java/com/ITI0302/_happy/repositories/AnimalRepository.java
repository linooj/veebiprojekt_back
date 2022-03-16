package com.ITI0302._happy.repositories;

import com.ITI0302._happy.models.Animal;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimalRepository extends MongoRepository<Animal, String> {
    List<Animal> findByNameContainingIgnoreCase(String name);
}
