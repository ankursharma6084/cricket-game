package com.CricketGame.CricketGame.converter;

import com.CricketGame.CricketGame.DTO.PlayerInput;
import com.CricketGame.CricketGame.exception.InvalidDetailsException;
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
              Player player = playerService.getPlayerById(id) ;

              // write logic for if we change teamId of a player then what to do
              if(! playerInput.getTeamId().equals(player.getTeamId())) {
                     throw new InvalidDetailsException("Team of a player cannot be changed") ;
              }
              player.setPlayerCategory(playerInput.getPlayerCategory());
              player.setName(playerInput.getName());
              return playerService.updatePlayer(player , id) ;
       }
}
