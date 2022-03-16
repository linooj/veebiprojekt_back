package com.ITI0302._happy.controller.theory.skateboards;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/skateboard")
public class SkateboardsApi {

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
    // Fred has a Skateboard shop in Telliskivi.
    // ---
    // Hi. I'm Fred the hipster. I studied law and music, but now I'm selling and making skateboards. Wild life!
    // Our business has grown and I need some help automating it.
    // Currently our inventory is managed by pen and paper. You need to make it better.
    // This is what I need:
    // - an overview of the skateboards we sell
    // - I want to know which ones are in stock and which ones are new (vs used)
    // - I want to order by the price or by the name alphabetically
    // and a
    // - page for each skateboard where I can see it's info
    // - button to add a new skateboard
    // - button to update existing skateboard
    // - button to delete skateboard

    //todo A first things first, please add necessary annotations to this class

    //todo B "an overview of the skateboards we sell"
    // create a method to query skateboards (plural)

    private List<Skateboard> skateboardsInDatabase = new ArrayList<>();

    /**
     * Examples:
     * http://localhost:8080/api/theory/skateboard
     * @param condition used
     * @param sorting -
     * @return list of skateboards that are used
     *
     * @param condition -
     * @param sorting priceAscending
     * @return list of skateboards from cheapest to most expensive
     */
    @GetMapping()
    public ResponseEntity<List<Skateboard>> allSkateboards(@RequestParam(name = "condition") Optional<String> condition,
                                                           @RequestParam(name = "sorting") Optional<String> sorting,
                                                           @RequestParam(name = "inStock") Optional<Boolean> inStock) {
        if (inStock.isPresent()) {
            if (inStock.get().equals(true)) {
                List<Skateboard> gotEm = skateboardsInDatabase.stream().filter(Skateboard::getInStock).collect(Collectors.toList());
                return new ResponseEntity<>(gotEm, HttpStatus.OK);
            }
        }
        if (condition.isPresent()) {
            List<Skateboard> filteredByCondition = skateboardsInDatabase.stream().filter(skateboard -> skateboard.getCondition().equals(condition.get())).collect(Collectors.toList());
            return new ResponseEntity<>(filteredByCondition, HttpStatus.OK);
        }
        if (sorting.isPresent()) {
            if (sorting.get().equals("priceAscending")) {
                List<Skateboard> sortByPrice = skateboardsInDatabase.stream().sorted(Comparator.comparingInt(Skateboard::getPrice)).collect(Collectors.toList());
                return new ResponseEntity<>(sortByPrice, HttpStatus.OK);
            } else if (sorting.get().equals("priceDescending")) {
                List<Skateboard> sortByPriceDescending = skateboardsInDatabase.stream().sorted(Comparator.comparingInt(Skateboard :: getPrice).reversed()).collect(Collectors.toList());
                return new ResponseEntity<>(sortByPriceDescending, HttpStatus.OK);
            } else if (sorting.get().equals("nameAscending")) {
                List<Skateboard> nameList = skateboardsInDatabase.stream().sorted(Comparator.comparing(Skateboard::getName)).collect(Collectors.toList());
                return new ResponseEntity<>(nameList, HttpStatus.OK);
            } else if (sorting.get().equals("nameDescending")) {
                List<Skateboard> nameList = skateboardsInDatabase.stream().sorted(Comparator.comparing(Skateboard::getName).reversed()).collect(Collectors.toList());
                return new ResponseEntity<>(nameList, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(skateboardsInDatabase, HttpStatus.OK);
    }

    //todo C "page for each skateboard where I can see it's info"
    // create a method to query a single skateboard
    // will throw not found, if out of stock

    @GetMapping("/{id}")
    public ResponseEntity<Skateboard> findSkateboard(@PathVariable Long id) {
        Optional<Skateboard> byId = skateboardsInDatabase.stream().filter(skateboard -> skateboard.getId().equals(id)).findFirst();
        return byId.map(skateboard -> new ResponseEntity<>(skateboard, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    //todo D "button to add a new skateboard"
    // create a method to save a new skateboard

    @PostMapping()
    public ResponseEntity<Skateboard> saveSkateboard(@RequestBody Skateboard skateboard) {
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //todo E "button to update existing skateboard"
    // create a method to update a skateboard

    @PutMapping()
    public ResponseEntity<Skateboard> updateSkateboard(@RequestBody Skateboard skateboard) {
        return new ResponseEntity<>(new Skateboard(), HttpStatus.OK);
    }

    //todo F "button to delete skateboard"
    // create a method to delete a skateboard

    @DeleteMapping("/{id}")
    public ResponseEntity<Skateboard> deleteSkateboard(@PathVariable String id) {
        return new ResponseEntity<>(HttpStatus.OK);

    }


    //todo G, H "I want to know which ones are in stock and which ones are new (vs used)"
    // G modify correct method to filter whether the skateboard is in stock or out of stock
    // H modify correct method to filter by condition (new, used, broken)
    // make sure existing functionality doesn't break

    //todo I-J "I want to order by the price or by the name alphabetically"
    // I modify correct method to provide sorting by price and name
    // J modify correct method to support sorting in ascending and descending order
    // in addition write some examples for how you will sort using your api (provide urls)

}
