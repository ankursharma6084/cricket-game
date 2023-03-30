package com.CricketGame.CricketGame.converter;

import com.CricketGame.CricketGame.DTO.PlayerInput;
import com.CricketGame.CricketGame.model.Player;
import com.CricketGame.CricketGame.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PlayerInputToPlayerConverter {
       @Autowired
       PlayerService playerService;
       public Player createPlayer(PlayerInput playerInput){
              Player player = new Player(playerInput.getName() , playerInput.getPlayerCategory() , playerInput.getTeamId());
              return playerService.createPlayer(player);
       }

       public Player updatePlayer(PlayerInput playerInput , String id){
             return playerService.updatePlayer(playerInput , id) ;
       }
}
