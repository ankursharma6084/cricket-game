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

    private ArrayList<Player> players ;
    public Team(String id,String name) {
        this.id = id ;
        this.name = name;
    }
    public Team(String name, ArrayList<Player> players) {
        this.name = name;
        this.players = players;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

}
