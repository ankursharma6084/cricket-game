package com.CricketGame.CricketGame.DTO;

import com.CricketGame.CricketGame.constants.PlayingFormat;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayingDetails {

    @NotBlank(message = "firstTeamName is mandatory")
    private String firstTeamName;
    @NotBlank(message = "secondTeamName is mandatory")
    private String secondTeamName;
    @NotNull(message = "PlayingFormat is mandatory")
    private PlayingFormat playingFormat;
    private int numberOfOvers;
}
