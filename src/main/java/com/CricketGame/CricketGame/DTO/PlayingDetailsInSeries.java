package com.CricketGame.CricketGame.DTO;

import lombok.Data;

@Data
public class PlayingDetailsInSeries extends PlayingDetails{
    private String seriesName;
    private int numberOfMatches;
}
