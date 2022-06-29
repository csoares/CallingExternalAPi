package com.example.callingexternalapi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;


@RestController
@RequestMapping("/api/v1")
public class HelloController {
    String uri = "http://www.omdbapi.com/";
    String apikey = "PUT_HERE_YOUR_API_KEY";


    @GetMapping(path = "film/{title}")
    public ResponseEntity<Film> getFilm(@PathVariable String title) {
        String finalUri = uri + "?t=" + title + "&apikey=" + apikey;
        System.out.println("finalUri = " + finalUri);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Film> result = restTemplate.getForEntity(finalUri, Film.class);
        return ResponseEntity.ok(result.getBody());
    }

    @GetMapping(path = "films/{search}")
    public ResponseEntity<FilmList> getFilms(@PathVariable String search) {
        String finalUri = uri + "?s=" + search + "&apikey=" + apikey;
        System.out.println("finalUri = " + finalUri);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<FilmList> result = restTemplate.getForEntity(finalUri, FilmList.class);
        return ResponseEntity.ok(result.getBody());
    }

    // Move these classes to a separate file! Only here for demonstration purposes.
    public static class Film {
        public String Title;
        public String Year;
        public String Poster;
        public String Type;


        @Override
        public String toString() {
            return "Film{" +
                    "Title='" + Title + '\'' +
                    ", Year='" + Year + '\'' +
                    '}';
        }
    }


    public static class FilmList {
        public Film[] Search;

        @Override
        public String toString() {
            return "FilmList{" +
                    "Search=" + Arrays.toString(Search) +
                    '}';
        }
    }
}
