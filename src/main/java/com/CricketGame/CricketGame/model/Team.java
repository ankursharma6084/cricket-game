package com.CricketGame.CricketGame.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
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

    public Team(String name){
        this.name = name ;
        players = new ArrayList<>() ;
    }

    // Remove these fields from team as they are derived fields
}
