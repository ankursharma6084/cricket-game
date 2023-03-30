package com.CricketGame.CricketGame.DTO;

import com.CricketGame.CricketGame.constants.PlayerCategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerInput {
    @NotBlank(message = "Player name is mandatory")
    private String name;
    @NotNull(message = "PlayerCategory name is mandatory")
    private PlayerCategory playerCategory;
    @NotBlank(message = "TeamId name is mandatory to associate player with a team")
    private String teamId;
}
