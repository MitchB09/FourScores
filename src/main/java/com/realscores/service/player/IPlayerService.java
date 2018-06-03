package com.realscores.service.player;

import java.util.List;

import com.realscores.obj.Player;

public interface IPlayerService {
	List<Player> getAllPlayers();
	Player getPlayerById(int playerId);
    boolean addPlayer(Player player);
    void updatePlayer(Player player);
    void deletePlayer(int playerId);
}
