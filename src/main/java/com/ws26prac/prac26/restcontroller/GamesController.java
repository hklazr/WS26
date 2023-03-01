package com.ws26prac.prac26.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import com.ws26prac.prac26.model.Game;
import com.ws26prac.prac26.repo.GamesRepository;

import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;

@RestController
@RequestMapping ("/games")
public class GamesController {
    
    @Autowired
    GamesRepository gameRepo;

    // @GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
    // public ResponseEntity<String> findGamesLimitOffset(
    //             @RequestParam(defaultValue="25") Integer limit,
    //             @RequestParam(defaultValue="0") Integer offset) {

    //     JsonObject payload = gamesService.findGamesLimitOffset(limit, offset);
        
    //     return ResponseEntity
    //             .status(HttpStatus.OK)
    //             .contentType(MediaType.APPLICATION_JSON)
    //             .body(payload.toString());
    // }
// }

    @GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getAllGames(
                                            @RequestParam(defaultValue = "25") Integer limit, 
                                            @RequestParam(defaultValue = "0") Integer offset) {

        List<Game> games = gameRepo.getAllGames(limit, offset);
        JsonArrayBuilder jar = Json.createArrayBuilder();
		for (Game g: games)
			jar.add(g.toJson());
            
            return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Json.createObjectBuilder()
                .add("games", jar)
                .build().toString());
        }
}
