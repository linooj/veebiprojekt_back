package com.ITI0302._happy.controller;

import com.ITI0302._happy.models.Animal;
import com.ITI0302._happy.remake.models.User;
import com.ITI0302._happy.service.AnimalService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.mockito.Mockito.*;

class AnimalControllerTest1 {
    @Mock
    AnimalService animalService;
    @InjectMocks
    AnimalController animalController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testAllAnimals() {
        List<Animal> list = List.of(new Animal("id", "name", new GregorianCalendar(2021, Calendar.OCTOBER, 23, 19, 8).getTime(), "species", "breed", "chipNr", Animal.Gender.MALE, 1000, null));
        when(animalService.findAll()).thenReturn(list);

        ResponseEntity<List<Animal>> result = animalController.allAnimals();
        ResponseEntity<List<Animal>> expected = new ResponseEntity<>(list, HttpStatus.OK);

        Assertions.assertEquals(expected, result);
    }

    @Test
    void testSearchedAnimals() {
        List<Animal> list = List.of(new Animal("id", "name", new GregorianCalendar(2021, Calendar.OCTOBER, 23, 19, 8).getTime(), "species", "breed", "chipNr", Animal.Gender.MALE, 1000, null));

        when(animalService.searchByName(anyString())).thenReturn(list);

        ResponseEntity<List<Animal>> result = animalController.searchedAnimals("name");
        ResponseEntity<List<Animal>> expected = new ResponseEntity<>(list, HttpStatus.OK);

        Assertions.assertEquals(expected, result);
    }

    @Test
    void testFindAnimal() {
        Animal animal = new Animal("id", "name", new GregorianCalendar(2021, Calendar.OCTOBER, 23, 19, 8).getTime(), "species", "breed", "chipNr", Animal.Gender.MALE, 1000, null);
        when(animalService.findById(anyString())).thenReturn(animal);

        ResponseEntity<Animal> result = animalController.findAnimal("id");
        ResponseEntity<Animal> expected = new ResponseEntity<>(animal, HttpStatus.OK);

        Assertions.assertEquals(expected, result);
    }

    @Test
    void testSaveAnimal() {
        Animal animal = new Animal("id", "name", new GregorianCalendar(2021, Calendar.OCTOBER, 23, 19, 8).getTime(), "species", "breed", "chipNr", Animal.Gender.MALE, 1000, null);

        when(animalService.addAnimal(any())).thenReturn(animal);

        ResponseEntity<Animal> result = animalController.saveAnimal(animal);
        ResponseEntity<Animal> expected = new ResponseEntity<>(HttpStatus.CREATED);

        Assertions.assertEquals(expected, result);
    }

    @Test
    void testUpdateAnimal() {
        Animal animal = new Animal("id", "name", new GregorianCalendar(2021, Calendar.OCTOBER, 23, 19, 8).getTime(), "species", "breed", "chipNr", Animal.Gender.MALE, 1000, null);

        when(animalService.updateAnimal(any())).thenReturn(animal);

        ResponseEntity<Animal> result = animalController.updateAnimal(animal);
        ResponseEntity<Animal> expected = new ResponseEntity<>(animal, HttpStatus.OK);

        Assertions.assertEquals(expected, result);
    }

    @Test
    void testDeleteAnimal() {
        ResponseEntity<User> result = animalController.deleteAnimal("id");
        ResponseEntity<Animal> expected = new ResponseEntity<>(HttpStatus.OK);

        Assertions.assertEquals(expected, result);
    }
}
