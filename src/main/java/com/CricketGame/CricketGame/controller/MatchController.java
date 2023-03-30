package com.CricketGame.CricketGame.controller;

import com.CricketGame.CricketGame.DTO.MatchSummary;
import com.CricketGame.CricketGame.DTO.PlayedMatchDetails;
import com.CricketGame.CricketGame.DTO.PlayingDetails;
import com.CricketGame.CricketGame.converter.PlayingDetailsToMatchConverter;
import com.CricketGame.CricketGame.exception.InvalidDetailsException;
import com.CricketGame.CricketGame.model.Match;
import com.CricketGame.CricketGame.service.MatchService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/match")
public class MatchController {
       @Autowired
       private PlayingDetailsToMatchConverter playingDetailsToMatchConverter;
       @Autowired
       private MatchService matchService;
       @GetMapping("/play")
       public PlayedMatchDetails play(@RequestBody @Valid PlayingDetails playingDetails){
               return playingDetailsToMatchConverter.play(playingDetails);
       }

       @GetMapping("/{id}")
       public Match getMatchDetailsById(@PathVariable String id){
              return matchService.getMatchDetailsById(id);
       }

       @GetMapping("/all")
       public List<MatchSummary> getAllMatches(){
              return matchService.getAllMatches();
       }

       @GetMapping("/byTeam/{id}")
       public List<MatchSummary> getMatchesPlayedByATeam(@PathVariable String id) {
              return matchService.getMatchesPlayedByATeam(id);
       }

}
