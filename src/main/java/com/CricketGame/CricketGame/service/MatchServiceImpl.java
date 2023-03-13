package com.CricketGame.CricketGame.service;

import com.CricketGame.CricketGame.DTO.PlayedMatchDetails;
import com.CricketGame.CricketGame.DTO.PlayingDetails;
import com.CricketGame.CricketGame.model.Match;
import com.CricketGame.CricketGame.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MatchServiceImpl implements MatchService{
    @Autowired
    PlayMatch playMatch;
    @Autowired
    MatchRepository matchRepository;
    @Autowired
    TeamService teamService;

    // write in converter
    @Override
    public Match play(Match match) {
            match = playMatch.play(match, teamService);
            matchRepository.save(match);
            return match;
    }
    @Override
    public Match getMatchDetailsbyId(String id) {
           return matchRepository.findById(id).orElseThrow(()-> new RuntimeException());
    }

}
