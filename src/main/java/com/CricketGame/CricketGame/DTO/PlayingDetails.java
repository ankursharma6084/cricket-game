package com.CricketGame.CricketGame.DTO;

import com.CricketGame.CricketGame.constants.PlayingFormat;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayingDetails {

    @NotBlank(message = "firstTeamName is mandatory")
    @NotNull(message = "firstTeamName cannot be null")
    private String firstTeamName;
    @NotBlank(message = "secondTeamName is mandatory")
    @NotNull(message = "secondTeamName cannot be null")
    private String secondTeamName;

    @NotBlank(message = "PlayingFormat is mandatory")
    private PlayingFormat playingFormat;
    @Min(1)
    @Max(50)
    @NotBlank(message = "numberOfOvers is mandatory")
    private int numberOfOvers;
}
