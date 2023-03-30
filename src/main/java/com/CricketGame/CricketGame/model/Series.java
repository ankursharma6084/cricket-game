package com.CricketGame.CricketGame.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "series")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Series {
       @Id
       private String id;
       private String name;
       private String firstTeamId;
       private String secondTeamId;
       private String seriesWinnerId;
       private List<Match> matches;

       public Series(String name,String firstTeamId, String secondTeamId){
              UUID uuid = UUID.randomUUID() ;
              id = uuid.toString();
              this.name = name;
              this.firstTeamId = firstTeamId ;
              this.secondTeamId = secondTeamId;
              matches = new ArrayList<>();
       }
}
