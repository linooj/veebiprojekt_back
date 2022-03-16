package com.ITI0302._happy.service;

import com.ITI0302._happy.models.Animal;
import com.ITI0302._happy.repositories.AnimalRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.mockito.Mockito.*;

class AnimalServiceTest {
    @Mock
    AnimalRepository animalRepository;
    @InjectMocks
    AnimalService animalService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testFindAll() {
        List<Animal> list = List.of(new Animal("id", "name", new GregorianCalendar(2021, Calendar.OCTOBER, 23, 19, 8).getTime(), "species", "breed", "chipNr", Animal.Gender.MALE, 1000, null));
        when(animalRepository.findAll()).thenReturn(list);

        List<Animal> result = animalService.findAll();
        Assertions.assertEquals(list, result);
    }

    @Test
    void testFindById() {
        Animal animal = new Animal("id", "name", new GregorianCalendar(2021, Calendar.OCTOBER, 23, 19, 8).getTime(), "species", "breed", "chipNr", Animal.Gender.MALE, 1000, null);
        when(animalRepository.findById(anyString())).thenReturn(Optional.of(animal));
        Animal result = animalService.findById("id");
        Assertions.assertEquals(animal, result);
    }

    @Test
    void testAddAnimal() {
        Animal animal = new Animal("id", "name", new GregorianCalendar(2021, Calendar.OCTOBER, 23, 19, 8).getTime(), "species", "breed", "chipNr", Animal.Gender.MALE, 1000, null);
        when(animalRepository.save(any(Animal.class))).thenReturn(animal);
        Animal result = animalService.addAnimal(animal);
        Assertions.assertEquals(animal, result);
    }

    @Test
    void testUpdateAnimal() {
        Animal animal = new Animal("id", "name", new GregorianCalendar(2021, Calendar.OCTOBER, 23, 19, 8).getTime(), "species", "breed", "chipNr", Animal.Gender.MALE, 1000, null);
        when(animalRepository.save(any(Animal.class))).thenReturn(animal);
        Animal result = animalService.updateAnimal(animal);
        Assertions.assertEquals(animal, result);

    }

    @Test
    void testSearchByName() {
        List<Animal> list = List.of(new Animal("id", "name", new GregorianCalendar(2021, Calendar.OCTOBER, 23, 19, 8).getTime(), "species", "breed", "chipNr", Animal.Gender.MALE, 1000, null));

        when(animalRepository.findByNameContainingIgnoreCase(anyString())).thenReturn(list);

        List<Animal> result = animalService.searchByName("name");
        Assertions.assertEquals(list, result);
    }

}
