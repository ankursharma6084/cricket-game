package com.CricketGame.CricketGame.service;

import com.CricketGame.CricketGame.constants.PlayerCategory;
import com.CricketGame.CricketGame.model.BowlerPerformanceInMatch;
import com.CricketGame.CricketGame.model.Over;
import com.CricketGame.CricketGame.model.Player;
import com.CricketGame.CricketGame.model.PlayerPerformanceInMatch;
import com.CricketGame.CricketGame.model.ScoreCard;
import com.CricketGame.CricketGame.model.Team;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.exit;

public abstract class Innings {
    protected TeamService teamService;
    protected String battingTeam ;
    protected String bowlingTeam ;
    protected int numberOfOvers;
    protected int numberOfPlayers;
    // We dont have to save them
    protected PlayerPerformanceInMatch striker ;
    protected PlayerPerformanceInMatch nonStriker;
    protected BowlerPerformanceInMatch currentBowler;
    protected ScoreCard scoreCard ;
    protected ArrayList<Player> batsmenYetToBat;

    public Innings(TeamService teamService, String battingTeam, String bowlingTeam, int numberOfOvers){
        this.teamService = teamService;
        this.battingTeam = battingTeam;
        this.bowlingTeam = bowlingTeam;
        this.numberOfOvers = numberOfOvers;
        scoreCard = new ScoreCard(battingTeam , bowlingTeam);
        batsmenYetToBat = getAllPlayers();
        numberOfPlayers = batsmenYetToBat.size() ;
        addAllBowlersFromBowlingTeam();
        currentBowler = null;
    }

    private void addAllBowlersFromBowlingTeam() {
        ArrayList<BowlerPerformanceInMatch> bowlers = scoreCard.getBowlerPerformance();
        for(Player player: teamService.getAllPlayers(bowlingTeam)){
             if(player.getPlayerCategory() != PlayerCategory.BATSMAN)
                 bowlers.add(new BowlerPerformanceInMatch(player.getName())) ;
        }

        if(bowlers.size() == 0){
            System.out.println("Your Team dosenot have any bowlers! Please add some bowlers");
            exit(-1);
        }
    }

    protected void addBoundariesifAny(int runs) {
        if(runs == 0) { }

        else if(runs%4 == 0){
            striker.setFours(striker.getFours()+1);
            scoreCard.setFoursInMatch(scoreCard.getFoursInMatch()+1);
        }
        else if(runs%6 == 0){
            striker.setSixes(striker.getSixes()+1);
            scoreCard.setSixesInMatch(scoreCard.getSixesInMatch()+1);
        }
    }

    protected BowlerPerformanceInMatch getNextBowler(BowlerPerformanceInMatch currentBowler){
        ArrayList<BowlerPerformanceInMatch> bowlers = scoreCard.getBowlerPerformance();
        for(BowlerPerformanceInMatch bowler: bowlers){
            if(bowler != currentBowler && bowler.getOversBowled() < Math.ceil((double) numberOfOvers /bowlers.size()))
                return bowler ;
        }
        return currentBowler ;
    }

    protected double calculateEconomyOfBowler(){
        double economy = currentBowler.getRunsScoredAgainst()/(double)(currentBowler.getBallsBowled()/ Over.getSize()) ;
        return economy ;
    }

    protected PlayerPerformanceInMatch getNewPlayerInField() {
        PlayerPerformanceInMatch player = batsmanComingtoTheField(batsmenYetToBat.get(0).getName());
        batsmenYetToBat.remove(0);
        return player ;
    }

    protected PlayerPerformanceInMatch batsmanComingtoTheField(String name) {
        PlayerPerformanceInMatch player = new PlayerPerformanceInMatch(name);
        return player;
    }

    private ArrayList<Player> getAllPlayers() {
        List<Player> players = teamService.getAllPlayers(battingTeam);
        batsmenYetToBat = new ArrayList<>(players);
        return batsmenYetToBat;
    }

    protected void swap() {
        PlayerPerformanceInMatch temp = striker ;
        striker = nonStriker ;
        nonStriker = temp;
    }

    protected int getOutcomeofBallThrown() {
        int runs = (int)(Math.random()*10 - 1)%8 ;
        if(runs == 7) runs = -1 ;
        return runs ;
    }

}

// Add_ons
// add bowler , economy , runs scored against , bowler throws ball new method