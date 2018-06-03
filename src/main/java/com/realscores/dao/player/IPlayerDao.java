package com.realscores.dao.player;

import java.util.List;

import com.realscores.obj.Player;

public interface IPlayerDao {
	List<Player> getAllPlayers();
	Player getPlayerById(int playerId);
    void addPlayer(Player player);
    void updatePlayer(Player player);
    void deletePlayer(int playerId);
    boolean PlayerExists(String name);
}
