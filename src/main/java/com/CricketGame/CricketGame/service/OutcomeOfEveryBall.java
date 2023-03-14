package com.CricketGame.CricketGame.service;

import com.CricketGame.CricketGame.constants.PlayerCategory;
import com.CricketGame.CricketGame.constants.ProbabilityFunction;

import java.util.List;

public class OutcomeOfEveryBall {
    public static int getResult(PlayerCategory playerCategory) {
        int runs;
        if (playerCategory == PlayerCategory.BOWLER) {
            runs = resultForPlayer(ProbabilityFunction.BOWLER_PROBABILITY_ON_BALL);
        } else {
            runs = resultForPlayer(ProbabilityFunction.BATSMAN_PROBABILITY_ON_BALL);
        }

        return runs;
    }

    private static int resultForPlayer(List<Integer> playerList) {
        int sumValue = 0;
        for (int temporaryScore : playerList)
            sumValue += temporaryScore;
        int randomValue = (int) (Math.random() * (sumValue + 1));

        int curValue = 0;
        for (int i = 0; i < playerList.size() - 1; i++) {
            curValue += playerList.get(i);
            if (curValue >= randomValue) return i;
        }
        return -1;
    }

};
