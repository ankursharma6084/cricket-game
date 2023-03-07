package com.CricketGame.CricketGame.controller;

import com.CricketGame.CricketGame.DTO.PlayedMatchDetails;
import com.CricketGame.CricketGame.DTO.PlayingDetails;
import com.CricketGame.CricketGame.converter.TeamNameToMatchConverter;
import com.CricketGame.CricketGame.model.Match;
import com.CricketGame.CricketGame.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class MatchController {
       @Autowired
       TeamNameToMatchConverter teamNameToMatchConverter;
       @Autowired
       MatchService matchService;
       @GetMapping("/playMatch")
       public PlayedMatchDetails play(@RequestBody PlayingDetails playingDetails){
               return teamNameToMatchConverter.play(playingDetails);
       }

       @GetMapping("/match/{id}")
       public Match getMatchDetailsbyId(@PathVariable String id){
              return matchService.getMatchDetailsbyId(id);
       }
}
