package com.CricketGame.CricketGame.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Team")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Team {
    @Id
    private String id;
    private String name ;
    private ArrayList<String> players ;

    // Remove these fields from team as they are derived fields
    private ArrayList<String> matches;
    private ArrayList<String> series;
    public Team(String id,String name) {
        this.id = id ;
        this.name = name;
        matches = new ArrayList<>();
    }
    public Team(String id,String name, ArrayList<String> players) {
        this.id = id;
        this.name = name;
        this.players = players;
        matches = new ArrayList<>();
    }

}
