package com.CricketGame.CricketGame.DTO;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PlayingDetailsInSeries extends PlayingDetails{
    @NotBlank(message = "seriesName is mandatory")
    @NotNull(message = "seriesName cannot be null")
    private String seriesName;
    @Min(1)
    @Max(10)
    @NotBlank(message = "numberOfMatches is mandatory")
    private int numberOfMatches;
}
