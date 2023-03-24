package com.CricketGame.CricketGame.service;

import com.CricketGame.CricketGame.model.Over;
import com.CricketGame.CricketGame.model.ScoreCard;
import com.CricketGame.CricketGame.model.Team;


public class FirstInnings extends Innings {
    public FirstInnings(TeamService teamService, String battingTeam, String bowlingTeam, int numberofOvers) {
        super(teamService, battingTeam, bowlingTeam, numberofOvers);
    }

    public ScoreCard playFirstInnings() {

        System.out.println(battingTeam + " is batting and " + bowlingTeam + " is bowling");

        striker = getNewPlayerInField();
        nonStriker = getNewPlayerInField();

        for(int curOver = 1; curOver<= numberOfOvers && scoreCard.getTotalWickets() < numberOfPlayers - 1; curOver++){
            Over currentOver = new Over(curOver) ;
            currentBowler = getNextBowler(currentBowler) ;
            System.out.println("Over - " + curOver);
            playCurrentOver(currentOver);
            scoreCard.getOvers().add(currentOver) ;
            currentBowler.setOversBowled(currentBowler.getBallsBowled()/currentOver.getSize());
            System.out.println("");
        }

        if(scoreCard.getTotalWickets() < numberOfPlayers - 1)
            scoreCard.getPlayerPerformance().add(striker);

        scoreCard.getPlayerPerformance().add(nonStriker);

        while(batsmenYetToBat.size() > 0){
            scoreCard.getPlayerPerformance().add(getNewPlayerInField());
        }

        return scoreCard;
    }

    private void playCurrentOver(Over currentOver) {
        for(int curBall = 1; curBall<=currentOver.getSize()
                            && scoreCard.getTotalWickets() < numberOfPlayers - 1; curBall++){
            currentBowler.setBallsBowled(currentBowler.getBallsBowled()+1);
            int runs = getOutcomeofBallThrown(striker.getPlayerCategory());
            if(runs == -1) System.out.print("W ");
            else System.out.print(runs + " ");
            if(runs == -1){
                currentBowler.setWicketsTaken(currentBowler.getWicketsTaken()+1);
                scoreCard.setTotalWickets(scoreCard.getTotalWickets()+1) ;
                scoreCard.getPlayerPerformance().add(striker);

                if(scoreCard.getTotalWickets() >= numberOfPlayers - 1) {
                    currentOver.getBalls().add(runs) ;
                    return;
                }
                striker = getNewPlayerInField();
            }

            else {
                currentBowler.setRunsScoredAgainst(currentBowler.getRunsScoredAgainst()+runs);
                // economy of bowler calculation
                currentBowler.setEconomy(calculateEconomyOfBowler());
                scoreCard.setTotalScore(scoreCard.getTotalScore() + runs);
                striker.setRunsScored(striker.getRunsScored()+runs);
                if(runs%2 == 1){
                    swap() ;
                }
                addBoundariesifAny(runs);
            }
            currentOver.getBalls().add(runs) ;
        }

        swap() ;
    }
}
