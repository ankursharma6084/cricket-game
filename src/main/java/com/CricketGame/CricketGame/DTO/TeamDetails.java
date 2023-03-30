package com.CricketGame.CricketGame.DTO;

import com.CricketGame.CricketGame.model.Player;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeamDetails {
    private String id;
    private String name ;
    private List<Player> players ;
}
