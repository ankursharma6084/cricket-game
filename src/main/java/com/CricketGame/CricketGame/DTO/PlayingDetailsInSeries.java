package com.CricketGame.CricketGame.DTO;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class PlayingDetailsInSeries extends PlayingDetails{
    @NotBlank(message = "seriesName is mandatory")
    private String seriesName;
    @Min(1)
    @Max(10)
    @NotNull(message = "numberOfMatches is mandatory")
    private int numberOfMatches;
}
