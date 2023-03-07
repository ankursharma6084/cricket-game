package com.CricketGame.CricketGame.service;

import com.CricketGame.CricketGame.constants.Coin;
import com.CricketGame.CricketGame.constants.PlayingFormat;
import com.CricketGame.CricketGame.model.BowlerPerformanceInMatch;
import com.CricketGame.CricketGame.model.Match;
import com.CricketGame.CricketGame.model.Over;
import com.CricketGame.CricketGame.model.PlayerPerformanceInMatch;
import com.CricketGame.CricketGame.model.ScoreCard;
import com.CricketGame.CricketGame.model.Team;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

@Service
public class PlayMatch {

    public Match play(Match match, TeamService teamService){
        Team battingTeam;
        Team bowlingTeam;
        Team tossWinner;
        Coin winningTossTeamChoice;
        ArrayList<ScoreCard> scoreCard = new ArrayList<>() ;
        //System.out.println(match.getTeamA()+ " " +match.getTeamB());

//        // Selecting Match Format
//        System.out.println(" Select Match Format to Play ");
//        System.out.println("(Press 1 for T05 , 2 for T10, 3 for T20, 4 for ODI and 5 for Custom Format)");
//        Scanner sc = new Scanner(System.in);
//        int choiceOfFormat = sc.nextInt() ;
//        int numberOfOvers = getNumberofOversFromchoiceOfFormat(choiceOfFormat) ;

        int numberOfOvers = match.getNumberOfOvers();
        // setting Playing Format
        setPlayingFormat(numberOfOvers, match);

        // Toss in progress
        System.out.println(" Toss in Progress ");
        tossWinner = playToss(match.getTeamA(), match.getTeamB());
        System.out.println(tossWinner);
        match.setTossWinner(tossWinner);

        // Toss Winner team decides randomly whether to Bat or Bowl
        System.out.println("Team "+ tossWinner.getName()+ " won the toss and is deciding whether to Bat or Bowl ");
        winningTossTeamChoice = coinToss() ;
        match.setWinningTossTeamChoice(winningTossTeamChoice);
        if(winningTossTeamChoice == Coin.Head){
            System.out.println("Team "+ tossWinner.getName()+ " decided to Bat ");
            battingTeam = tossWinner ;
            bowlingTeam = getBowlingTeam(match.getTeamA(), match.getTeamB(), tossWinner) ;
        }

        else {
            System.out.println("Team "+ tossWinner.getName()+ " decided to Bowl ");
            bowlingTeam = tossWinner ;
            battingTeam = getBattingTeam(match.getTeamA(), match.getTeamB(), tossWinner) ;
        }

        // First Innings
        FirstInnings firstInnings = new FirstInnings(teamService, battingTeam , bowlingTeam, numberOfOvers);
        ScoreCard firstInningsScoreCard = firstInnings.playFirstInnings();
        scoreCard.add(firstInningsScoreCard) ;
        // Second Innings
        SecondInnings secondInnings = new SecondInnings(teamService, bowlingTeam, battingTeam, numberOfOvers);
        ScoreCard secondInningsScoreCard = secondInnings.playSecondInnings(firstInningsScoreCard.getTotalScore()+1);
        scoreCard.add(secondInningsScoreCard) ;
        match.setScoreCard(scoreCard);

        // Getting Match outcome and Printing ScoreCard
        showOutcomeofMatch(firstInningsScoreCard, secondInningsScoreCard);
        printScorecard(firstInningsScoreCard, secondInningsScoreCard);

        setMatchesInTeamDetails(match, battingTeam);
        setMatchesInTeamDetails(match, bowlingTeam);

        // saving Match in Database
        return match;
    }

    private void setMatchesInTeamDetails(Match match, Team battingTeam) {
        ArrayList<String> teamAMatches = battingTeam.getMatches();
        teamAMatches.add(match.getId());
        battingTeam.setMatches(teamAMatches);
    }

    private void setPlayingFormat(int numberOfOvers, Match match) {
            if(numberOfOvers == 5){
                match.setPlayingFormat(PlayingFormat.T05);
            }  else if (numberOfOvers == 10) {
                match.setPlayingFormat(PlayingFormat.T10);
            }  else if (numberOfOvers == 20) {
                match.setPlayingFormat(PlayingFormat.T20);
            }  else if (numberOfOvers == 50) {
                match.setPlayingFormat(PlayingFormat.ODI);
            }   else{
                match.setPlayingFormat(PlayingFormat.CUSTOM);
            }
    }

    private void printScorecard(ScoreCard firstInningsScoreCard, ScoreCard secondInningsScoreCard) {
        System.out.println(" ");
        System.out.println("First Innings Scorecard");
        printInningWiseScoreCard(firstInningsScoreCard);
        System.out.println(" ");
        System.out.println("Second Innings Scorecard");
        printInningWiseScoreCard(secondInningsScoreCard);

    }

    private void printInningWiseScoreCard(ScoreCard firstInningsScoreCard) {
        System.out.println("Batting Team " + firstInningsScoreCard.getBattingTeam() + " and" +
                "Bowling Team " + firstInningsScoreCard.getBowlingTeam() );
        System.out.println("Total Score - " + firstInningsScoreCard.getTotalScore() +
                " Total Wickets - " + firstInningsScoreCard.getTotalWickets());
        System.out.println("Total Fours - " + firstInningsScoreCard.getFoursInMatch() +
                " Total Sixes - " + firstInningsScoreCard.getSixesInMatch());

        ArrayList<PlayerPerformanceInMatch> playerPerformanceinMatch = firstInningsScoreCard.getPlayerPerformance();
        for(PlayerPerformanceInMatch curPlayerPerformance : playerPerformanceinMatch)
        {
            System.out.println(curPlayerPerformance.getPlayerName() +
                    " scored " + curPlayerPerformance.getRunsScored() + " runs "
                    + curPlayerPerformance.getFours() + " fours and " +
                    curPlayerPerformance.getSixes() + " sixes");
        }

        System.out.println("");
        System.out.println("OverWise Runs");
        ArrayList<Over> overs = firstInningsScoreCard.getOvers() ;
        for(Over curOver: overs){
            System.out.println("Over ");
            ArrayList<Integer> balls = curOver.getBalls() ;
            for(int ball: balls)
            {
                if(ball == -1) System.out.print("W ");
                else System.out.print(ball + " ");
            }
            System.out.println("");
        }

        System.out.println("Bowler Performance ");
        for(BowlerPerformanceInMatch bowler: firstInningsScoreCard.getBowlerPerformance()){
            System.out.println(bowler.getName() + " bowled " +
                    bowler.getOversBowled() + " overs " +
                    bowler.getBallsBowled()%Over.getSize() + " balls got " +
                    bowler.getRunsScoredAgainst() + " runs scored against and took " +
                    bowler.getWicketsTaken() + " wickets");
        }
    }


    private int getNumberofOversFromchoiceOfFormat(int choiceOfFormat, PlayingFormat playingFormat) {
        if(choiceOfFormat == 1) {
            playingFormat = PlayingFormat.T05;
            return 5;
        }
        else if (choiceOfFormat == 2) {
            playingFormat = PlayingFormat.T10;
            return 10;
        }
        else if (choiceOfFormat == 3) {
            playingFormat = PlayingFormat.T20;
            return 20;
        }
        else if (choiceOfFormat == 4) {
            playingFormat = PlayingFormat.ODI;
            return 50;
        }
        else {
            playingFormat = PlayingFormat.CUSTOM;
            System.out.println("Enter the number of Overs you want to play");
            Scanner sc = new Scanner(System.in);
            int numberOfOvers = sc.nextInt() ;
            return numberOfOvers ;
        }
    }

    private Team playToss(Team teamA, Team teamB) {
        Team tossWinner;
        Coin tossOutcome = coinToss();
        if(tossOutcome == Coin.Head){
            tossWinner = teamA ;
        }
        else tossWinner = teamB ;
        return tossWinner;
    }

    private void showOutcomeofMatch(ScoreCard firstInningsScoreCard, ScoreCard secondInningsScoreCard) {
        System.out.println("");
        System.out.println("Team " + firstInningsScoreCard.getBattingTeam() + " scored " +
                firstInningsScoreCard.getTotalScore() + " runs");
        System.out.println("Team " + firstInningsScoreCard.getBowlingTeam() + " scored " +
                secondInningsScoreCard.getTotalScore() + " runs");

        String winningTeamName = getWinningTeam(firstInningsScoreCard, secondInningsScoreCard) ;

        if(winningTeamName != null)
            System.out.println("So " + winningTeamName + " won the match by "
                    + Math.abs(firstInningsScoreCard.getTotalScore() - secondInningsScoreCard.getTotalScore())
                    + " runs" );

        else System.out.println("Oops match Drawn");
    }

    public String getWinningTeam(ScoreCard firstInningsScoreCard, ScoreCard secondInningsScoreCard) {
        if(firstInningsScoreCard.getTotalScore() > secondInningsScoreCard.getTotalScore())
            return firstInningsScoreCard.getBattingTeam() ;
        else if (firstInningsScoreCard.getTotalScore() < secondInningsScoreCard.getTotalScore()) {
            return secondInningsScoreCard.getBattingTeam() ;
        }
        else return null ;
    }

    private Team getBattingTeam(Team teamA, Team teamB, Team tossWinner) {
        return getBowlingTeam(teamA, teamB , tossWinner) ;
    }

    private Team getBowlingTeam(Team teamA, Team teamB, Team tossWinner) {
        if(teamA == tossWinner) return teamB;
        return teamA ;
    }

    private Coin coinToss(){
        Random r = new Random();
        int i = r.nextInt(2);
        if (i == 0) {
            return Coin.Head;
        } else {
            return Coin.Tail;
        }
    }
}
