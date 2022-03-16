package com.ITI0302._happy.service;

import com.ITI0302._happy.exception.NotFoundException;
import com.ITI0302._happy.models.Animal;
import com.ITI0302._happy.repositories.AnimalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnimalService {

    private final AnimalRepository animalRepository;

    public List<Animal> findAll() {
        return animalRepository.findAll();
    }

    public Animal findById(String id) {
        return getById(id);
    }

    public Animal addAnimal(Animal animal) {
        return animalRepository.save(animal);
    }

    public Animal updateAnimal(Animal animal) {
        return animalRepository.save(animal);
    }

    public void delete(String id) {
        animalRepository.delete(getById(id));
    }

    // @ToDo need to make client no found exception - ok
    private Animal getById(String id) {
        return animalRepository.findById(id).orElseThrow(() -> new NotFoundException("No such animal found. :("));
    }

    public List<Animal> searchByName(String name) {
        return animalRepository.findByNameContainingIgnoreCase(name);
    }

    public void deleteAnimal(String id) {
        animalRepository.delete(getById(id));
    }

}
