package com.CricketGame.CricketGame.service;

import com.CricketGame.CricketGame.DTO.PlayedSeriesDetails;
import com.CricketGame.CricketGame.DTO.PlayingDetailsInSeries;
import com.CricketGame.CricketGame.model.Series;

public interface SeriesService {
    PlayedSeriesDetails playSeries(PlayingDetailsInSeries playingDetailsInSeries);

    Series getSeriesDetails(String id);
}
