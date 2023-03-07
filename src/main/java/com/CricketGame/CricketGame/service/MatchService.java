package com.CricketGame.CricketGame.service;

import com.CricketGame.CricketGame.DTO.PlayedMatchDetails;
import com.CricketGame.CricketGame.model.Match;

import java.util.Optional;

public interface MatchService {
    PlayedMatchDetails play(Match match);

    Match getMatchDetailsbyId(String id);

//    String saveMatch(Match match);
}
