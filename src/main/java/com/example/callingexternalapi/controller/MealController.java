package com.example.callingexternalapi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("api/v1")
public class MealController {

    String uri = "http://www.omdbapi.com/";


    @GetMapping(path="meal/{name}")
    public ResponseEntity<Meal> getMeal(@PathVariable String name) {
//        String finalUri = uri + "?t=" + name ;
        String finalUri="https://www.themealdb.com/api/json/v1/1/search.php?s=pasta";
        System.out.println("finalUri = " + finalUri);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Meal> result = restTemplate.getForEntity(finalUri, Meal.class);
        return ResponseEntity.ok(result.getBody());
    }


    @GetMapping(path="meals/{name}")
    public ResponseEntity<MealList> getMeals(@PathVariable String name) {
//        String finalUri = uri + "?t=" + name ;
        String finalUri= "https://www.themealdb.com/api/json/v1/1/filter.php?i=chicken_breast";
        System.out.println("finalUri = " + finalUri);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<MealList> result = restTemplate.getForEntity(finalUri, MealList.class);
        return ResponseEntity.ok(result.getBody());
    }

    public static class Meal {
    }

    public static class MealList {
    }

}