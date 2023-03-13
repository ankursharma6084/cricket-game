package com.CricketGame.CricketGame.repository;

import com.CricketGame.CricketGame.model.Series;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeriesRepository extends MongoRepository<Series, String> {
}
