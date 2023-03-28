package com.CricketGame.CricketGame.controller;

import com.CricketGame.CricketGame.DTO.PlayedSeriesDetails;
import com.CricketGame.CricketGame.DTO.PlayingDetails;
import com.CricketGame.CricketGame.DTO.PlayingDetailsInSeries;
import com.CricketGame.CricketGame.model.Series;
import com.CricketGame.CricketGame.service.SeriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SeriesController {
    @Autowired
    private SeriesService seriesService;
    @GetMapping("/playSeries")
    public PlayedSeriesDetails playSeries(@RequestBody PlayingDetailsInSeries playingDetailsInSeries){
        return seriesService.playSeries(playingDetailsInSeries);
    }

    @GetMapping("/getSeriesDetails/{id}")
    public Series getSeriesDetails(@PathVariable String id){
        return seriesService.getSeriesDetails(id);
    }
}