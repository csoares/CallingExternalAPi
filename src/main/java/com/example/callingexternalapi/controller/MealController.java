package com.example.callingexternalapi.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class MealController {

    String url = "https://www.themealdb.com/api/json/v1/1/";


    @GetMapping(path = "meal/{name}")
    public ResponseEntity<Meal> getMeal(@PathVariable String name) {
        String finalUri = url + "search.php?s=" + name;
        System.out.println("finalUri = " + finalUri);
        RestTemplate restTemplate = new RestTemplate();
        // need to check null here
        List<Meal> meals = restTemplate.getForEntity(finalUri, ListMeal.class).getBody().meals;
        Meal result;
        if (meals != null)
            result = meals.get(0);
        else
            result = null;
        return ResponseEntity.ok(result);
    }


    @GetMapping(path = "meals/{name}")

    public ResponseEntity<ListMeal> getMeals(@PathVariable String name) {
        String finalUri = url + "filter.php?i=" + name;
        System.out.println("finalUri = " + finalUri);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<ListMeal> result = restTemplate.getForEntity(finalUri, ListMeal.class);
        return ResponseEntity.ok(result.getBody());
    }

    // move this class to a separate file! Only here for demonstration purposes.
    public static class ListMeal {
        public List<Meal> meals;

        @Override
        public String toString() {
            return "ListMeal{" +
                    "meals=" + meals +
                    '}';
        }
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Meal {
        public String idMeal;
        public String strMeal;
        public String strCategory;
        public String strArea;
        public String strInstructions;
        public String strIngredient1;
        public String strIngredient2;
        public String strIngredient3;
        // and so on...

        @Override
        public String toString() {
            return "Meal{" +
                    "idMeal='" + idMeal + '\'' +
                    ", strMeal='" + strMeal + '\'' +
                    ", strCategory='" + strCategory + '\'' +
                    ", strArea='" + strArea + '\'' +
                    ", strInstructions='" + strInstructions + '\'' +
                    ", strIngredient1='" + strIngredient1 + '\'' +
                    ", strIngredient2='" + strIngredient2 + '\'' +
                    ", strIngredient3='" + strIngredient3 + '\'' +
                    '}';
        }
    }

    public static class MealList {
        public List<Meal> meals;

        @Override
        public String toString() {
            return "MealList{" +
                    "meals=" + meals +
                    '}';
        }
    }

}