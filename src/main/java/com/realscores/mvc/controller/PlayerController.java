package com.realscores.mvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.UriComponentsBuilder;

import com.realscores.obj.Player;
import com.realscores.service.player.IPlayerService;

@Controller
@RequestMapping("/players")
public class PlayerController {
  
	@Autowired
	IPlayerService playerService;
	
	@GetMapping()
	public ResponseEntity<List<Player>> getPlayers(){		
		List<Player> players = playerService.getAllPlayers();
		return new ResponseEntity<List<Player>>(players, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Player> getPlayerById(
			@PathVariable("id") int playerId){		
		Player player = playerService.getPlayerById(playerId);
		return new ResponseEntity<Player>(player, HttpStatus.OK);
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> createPlayer(@RequestBody Player player, UriComponentsBuilder builder){		
		boolean flag = playerService.addPlayer(player);
		if (flag == false) {
	      return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/players/{id}").buildAndExpand(player.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/{playerId}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> updatePlayer(
			@PathVariable("playerId") int playerId,
			@RequestBody Player player) {
		
		if (player.getId() != playerId){
			return new ResponseEntity<Object>("Cannot update player id", HttpStatus.UNPROCESSABLE_ENTITY);
		}
		playerService.updatePlayer(player);
		return new ResponseEntity<Object>(player, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletePlayer(@PathVariable("id") Integer id) {
		playerService.deletePlayer(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}	
}
