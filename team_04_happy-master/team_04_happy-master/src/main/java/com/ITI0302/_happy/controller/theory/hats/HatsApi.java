package com.ITI0302._happy.controller.theory.hats;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/hats")
public class HatsApi {

    //todo Welcome to the theory!
    // To start put these classes into my.project.controller.theory so you can check these using swagger or browser
    // Each team member has to do only 1 assignment and commit/push it to your repository.
    // (So 2 people - 2 assignments, 3 people - 3 assignments, 4 people - 4 assignments).
    // Make sure to commit under your user otherwise the points won't count. Each team member has to score at least 50%.
    // Don't add unnecessary code (no need for services or database).
    // We are doing mock-api design. I am grading urls and structure of the methods.
    // It should still work, i.e I can access this api from swagger or browser.
    // A good source for learning about proper API design is https://docs.microsoft.com/en-us/azure/architecture/best-practices/api-design

    //todo The Story
    // Mad Hatter. Another Telliskivi butique. Hat know-how link (:  https://www.youtube.com/watch?v=6lYuL_kz9Ak
    // ---
    // Hey, I am Max Hatter. I'm from the states (US). I played basketball in my youth and I fell in love with hats.
    // I came to Estonia few years ago and started a business selling hats. I have a busy shop in Telliskivi region.
    // However during winter-time our sales are slow, so I am thinking of expanding our online presence.
    // I think we need to do something on the web. Like a shop or gallery or both. Connect it to tik-tok, instagram, facebook.
    // Do the online thing. Can you help?
    // I guess I need like a landing page where you can see many hats.
    // And each hat has some info, so once you click on it, it displays it.
    // And then there are buttons for saving and updating when I have new hats or some info was wrong.
    // Oh, and some way to remove hats.
    // For landing page it is important that the hats can be filtered by style and colour.
    // Also I'd like to order them by size and price.

    //todo A first things first, please add necessary annotations to this class

    //todo B "I guess I need like a landing page where you can see many hats"
    // create a method to query hats (plural)

    List<Hat> hatsInDatabase = new ArrayList<>();

    @GetMapping()
    public ResponseEntity<List<Hat>> viewHats(@RequestBody Optional<String> filterOption,
                                              @RequestBody Optional<String> styleOrColour) {
        if (filterOption.isEmpty()) {
            return new ResponseEntity<>(hatsInDatabase, HttpStatus.OK);
        } else if (filterOption.get().equals("style") && styleOrColour.isPresent()) {
            List<Hat> hatsFilteredByStyle = hatsInDatabase.stream()
                    .filter(hat -> hat.getStyle().equals(styleOrColour.get()))
                    .collect(Collectors.toList());
            return new ResponseEntity<>(hatsFilteredByStyle, HttpStatus.OK);
        } else if (filterOption.get().equals("colour") && styleOrColour.isPresent()) {
            List<Hat> hatsFilteredByColour = hatsInDatabase.stream()
                    .filter(hat -> hat.getStyle().equals(styleOrColour.get()))
                    .collect(Collectors.toList());
            return new ResponseEntity<>(hatsFilteredByColour, HttpStatus.OK);
        } else if (filterOption.get().equals("priceAscending")) {
            List<Hat> hatsFilteredByPriceAscending = hatsInDatabase.stream()
                                                    .sorted(Comparator.comparing(Hat::getPrice))
                                                    .collect(Collectors.toList());
            return new ResponseEntity<>(hatsFilteredByPriceAscending, HttpStatus.OK);
        } else if (filterOption.get().equals("priceDescending")) {
            List<Hat> hatsFilteredByPriceDescending = hatsInDatabase.stream()
                                                    .sorted(Comparator.comparing(Hat::getPrice).reversed())
                                                    .collect(Collectors.toList());

            return new ResponseEntity<>(hatsFilteredByPriceDescending, HttpStatus.OK);
        } else if (filterOption.get().equals("sizeAscending")) {
            List<Hat> hatsFilteredBySizeAscending = hatsInDatabase.stream()
                                                    .sorted(Comparator.comparing(Hat::getSize))
                                                    .collect(Collectors.toList());
            return new ResponseEntity<>(hatsFilteredBySizeAscending, HttpStatus.OK);
        } else if (filterOption.get().equals("sizeDescending")) {
            List<Hat> hatsFilteredBySizeDescending = hatsInDatabase.stream()
                                                    .sorted(Comparator.comparing(Hat::getSize).reversed())
                                                    .collect(Collectors.toList());
            return new ResponseEntity<>(hatsFilteredBySizeDescending, HttpStatus.OK);
        }
        return null;
    }

    /**
     * Examples for using sorting/filtering algorithms
     *
     * 1. http://localhost:8080/api/theory/hats and as RequestBody I will add "colour" and "blue"
     * Return -> List of hats filtered by colour "blue"
     *
     * 2. http://localhost:8080/api/theory/hats and as RequestBody I will add "priceDescending" and ""
     * Return -> List of hats sorted by price (descending)
     */

    //todo C "And each hat has some info, so once you click on it, it displays it"
    // create a method to query a single hat

    @GetMapping("/{id}")
    public ResponseEntity<Hat> hatDetailView(@PathVariable long id) {
        return new ResponseEntity<>(hatsInDatabase.get((int) id), HttpStatus.OK);
    }

    //todo D "And then there are buttons for saving [..] when I have new hats [..]"
    // create a method to save a new hat

    @PostMapping()
    public ResponseEntity<Hat> saveHat(@RequestBody Hat hatToAdd) {
        hatsInDatabase.add(hatToAdd);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //todo E "And then there are buttons for [..] updating when [..] some info was wrong"
    // create a method to update a hat

    @PutMapping()
    public ResponseEntity<Hat> updateHat(@RequestBody Hat hatToUpdate) {
//      Some magical updating happens here -> hatToUpdate.update()
        return new ResponseEntity<>(hatToUpdate, HttpStatus.OK);
    }

    //todo F "Oh, and some way to remove hats."
    // create a method to delete a hat

    @DeleteMapping("/{id}")
    public ResponseEntity<Hat> deleteHat(@PathVariable long id) {
        hatsInDatabase.remove(hatsInDatabase.get((int) id));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //todo G, H "For landing page it is important that the hats can be filtered by style and colour."
    // G modify correct method to filter by hat style (59fifty, 9twenty, cap, etc)
    // G modify correct method to filter by hat colour (red, blue, etc)
    // make sure existing functionality doesn't break

    //todo I-J "Also I'd like to order them [the hats] by size and price."
    // I modify correct method to provide sorting by size and price
    // J modify correct method to support sorting in ascending and descending order
    // in addition write some examples for how you will sort using your api (provide urls)
}
