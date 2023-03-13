package com.CricketGame.CricketGame.service;

import com.CricketGame.CricketGame.DTO.PlayedMatchDetails;
import com.CricketGame.CricketGame.model.Match;

import java.util.Optional;

public interface MatchService {
    Match play(Match match);

    Match getMatchDetailsbyId(String id);
}
