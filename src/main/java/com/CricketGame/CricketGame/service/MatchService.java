package com.CricketGame.CricketGame.service;
import java.util.List;
import com.CricketGame.CricketGame.DTO.MatchSummary;
import com.CricketGame.CricketGame.DTO.PlayedMatchDetails;
import com.CricketGame.CricketGame.constants.PlayingFormat;
import com.CricketGame.CricketGame.exception.InvalidDetailsException;
import com.CricketGame.CricketGame.model.Match;
import com.CricketGame.CricketGame.model.Team;

public interface MatchService {
    PlayedMatchDetails play(Team teamA, Team teamB, int numberOfOvers, PlayingFormat playingFormat)
                            throws InvalidDetailsException;

    Match getMatchDetailsById(String id) throws InvalidDetailsException;

    Match playInningsMatch(Team teamA, Team teamB, int numberOfOvers, PlayingFormat playingFormat) throws InvalidDetailsException;

    List<MatchSummary> getAllMatches() throws InvalidDetailsException;

    List<MatchSummary> getMatchesPlayedByATeam(String id) throws InvalidDetailsException;
}
