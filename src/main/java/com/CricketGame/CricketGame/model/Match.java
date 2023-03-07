package com.CricketGame.CricketGame.model;

import com.CricketGame.CricketGame.constants.Coin;
import com.CricketGame.CricketGame.constants.PlayingFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Match")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Component
public class Match {
    @Id
    private String id;
    private Team teamA ;
    private Team teamB ;
    private Team tossWinner ;
    private Coin winningTossTeamChoice;
    private PlayingFormat playingFormat;
    private int numberOfOvers ;
    private ArrayList<ScoreCard> scoreCard ;

    public Match(Team A, Team B){
        UUID uuid = UUID.randomUUID() ;
        id = uuid.toString();
        this.teamA = A ;
        this.teamB = B;
        scoreCard = new ArrayList<>();
    }
}
