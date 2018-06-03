package com.realscores.obj.builder;

import java.util.ArrayList;
import java.util.List;

import com.realscores.obj.Player;

public class PlayerBuilder {

	public static Player createPlayer(int playerId){
		Player player = new Player();
		player.setId(playerId);
		player.setName("Player_" + String.valueOf(playerId));
		return player;
	}
	
	public static Player createCustomPlayer(int playerId, String name){
		Player player = new Player();
		player.setId(playerId);
		player.setName(name);
		return player;
	}
	
	public static List<Player> createPlayerList(int numPlayers){
		List<Player> players = new ArrayList<Player>();
		for(int i = 1; i <= numPlayers; i++) {
			players.add(PlayerBuilder.createPlayer(i));
		}
		return players; 
	}
}
