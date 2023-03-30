package com.CricketGame.CricketGame.model;

import com.CricketGame.CricketGame.constants.PlayerCategory;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "player")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Player {
    @Id
    private String id;
    private String name;
    private PlayerCategory playerCategory;
    private String teamId;
    private int runsScored;
    private int numberOfFoursScored;
    private int numberOfSixesScored;
    private int numberOfWicketsTaken ;

    public Player(String name, PlayerCategory playerCategory, String teamId){
           this.name = name ;
           this.playerCategory = playerCategory;
           this.teamId = teamId;
           numberOfFoursScored = 0;
           numberOfSixesScored = 0;
           numberOfWicketsTaken = 0;
           runsScored = 0 ;
    }
}
