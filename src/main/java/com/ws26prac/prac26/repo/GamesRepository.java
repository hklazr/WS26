package com.ws26prac.prac26.repo;

import java.time.LocalDateTime;
import java.util.List;
import org.bson.Document;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import static com.ws26prac.prac26.Constants.*;
import com.ws26prac.prac26.model.Game;


@Repository
public class GamesRepository {

    @Autowired
    MongoTemplate mongoTemplate;

    // QUERY = db.game.find().skip(0).limit(25);

    public List<Game> getAllGames(Integer limit, Integer offset) {

        Query query = Query.query(new Criteria())
                        .skip(offset)
                        .limit(limit);

        List<Document> docs = mongoTemplate.find(query, Document.class, COLLECTION_GAME);
        // LocalDateTime now = LocalDateTime.now();
        List<Game> game = docs.stream()
                            .map(x -> Game.fromMongoDocument(x))
                            .toList();
        
        return game;



    }
    
}
