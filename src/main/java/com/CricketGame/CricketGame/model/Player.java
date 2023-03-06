package com.CricketGame.CricketGame.model;

import com.CricketGame.CricketGame.constants.PlayerCategory;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Player")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Player {
    @Id
    private String id;
    private String name;
    private PlayerCategory playerCategory;
    private String teamId;
    private int numberOf50sScored ;
    private int numberof100sScored ;
    private int numberOfWicketsTaken ;

    public Player(String name, PlayerCategory playerCategory, String teamId){
           UUID uuid = UUID.randomUUID() ;
           id = uuid.toString();
           this.name = name ;
           this.playerCategory = playerCategory;
           this.teamId = teamId;
           numberOf50sScored = 0;
           numberof100sScored = 0;
           numberOfWicketsTaken = 0;
    }
}
