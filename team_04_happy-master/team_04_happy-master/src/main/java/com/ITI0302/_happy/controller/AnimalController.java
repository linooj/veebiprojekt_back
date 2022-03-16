package com.ITI0302._happy.controller;

import com.ITI0302._happy.models.Animal;
import com.ITI0302._happy.remake.models.User;
import com.ITI0302._happy.service.AnimalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RequestMapping("/api/animals")
@RestController
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class AnimalController {

    private final AnimalService animalService;

    @GetMapping()
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Animal>> allAnimals() {
        List<Animal> animals = animalService.findAll();
        return new ResponseEntity<>(animals, HttpStatus.OK);
    }

//  ToDo WTF on see meetod :)))))
    @GetMapping("/search")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Animal>> searchedAnimals(@RequestParam(name = "name") String name) {
        List<Animal> animals = animalService.searchByName(name);
        return new ResponseEntity<>(animals, HttpStatus.OK);
    }

    //  ToDo välja mõelda, mis selle url võiks olla

    @GetMapping("/userId")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<List<Animal>> animalsFilteredByUser(@RequestParam(name = "userId") String userId) {
        List<Animal> usersAnimals = new ArrayList<>();
        for (Animal animal: animalService.findAll()) {
            User foundUser = animal.getUser();
            if (foundUser != null && foundUser.getId().equals(userId)) {
                usersAnimals.add(animal);
            }
        }
        return new ResponseEntity<>(usersAnimals, HttpStatus.OK);
    }


//  ToDo muuta pärast -> "animalID"
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Animal> findAnimal(@PathVariable String id) {
        Animal searchedAnimal = animalService.findById(id);
        return new ResponseEntity<>(searchedAnimal, HttpStatus.OK);
    }

    @PostMapping()
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Animal> saveAnimal(@RequestBody Animal animal) {
        this.animalService.addAnimal(animal);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping()
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Animal> updateAnimal(@RequestBody Animal animal) {
        Animal changedAnimal = this.animalService.updateAnimal(animal);
        return new ResponseEntity<>(changedAnimal, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<User> deleteAnimal(@PathVariable String id) {
        animalService.deleteAnimal(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
