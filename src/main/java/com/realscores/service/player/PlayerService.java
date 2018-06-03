package com.realscores.service.player;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.realscores.dao.player.IPlayerDao;
import com.realscores.obj.Player;

@Service
public class PlayerService implements IPlayerService {

	@Autowired
	IPlayerDao playerDao;
	
	@Override
	public List<Player> getAllPlayers() {
		return playerDao.getAllPlayers(); 
	}

	@Override
	public Player getPlayerById(int playerId) {
		return playerDao.getPlayerById(playerId);
	}

	@Override
	public boolean addPlayer(Player player) {
		if (playerDao.PlayerExists(player.getName())){
			return false;
		} else {
			playerDao.addPlayer(player);
		    return true;
		}
	}

	@Override
	public void updatePlayer(Player player) {
		playerDao.updatePlayer(player);
	}

	@Override
	public void deletePlayer(int playerId) {
		playerDao.deletePlayer(playerId);
	}

}
