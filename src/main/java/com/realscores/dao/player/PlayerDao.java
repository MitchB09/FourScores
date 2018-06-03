package com.realscores.dao.player;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.realscores.obj.Player;

@Transactional
@Repository
public class PlayerDao implements IPlayerDao {

	@PersistenceContext	
	private EntityManager entityManager;	
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Player> getAllPlayers() {
		String hql = "FROM Player as plr ORDER BY plr.id";
		return (List<Player>) entityManager.createQuery(hql).getResultList();
	}

	@Override
	public Player getPlayerById(int playerId) {
		return entityManager.find(Player.class, playerId);
	}

	@Override
	public void addPlayer(Player player) {
		entityManager.persist(player);
	}

	@Override
	public void updatePlayer(Player player) {
		Player foundPlayer = getPlayerById(player.getId());
		foundPlayer.setName(player.getName());
		entityManager.flush();
	}

	@Override
	public void deletePlayer(int playerId) {
		entityManager.remove(getPlayerById(playerId));
	}

	@Override
	public boolean PlayerExists(String name) {
		String hql = "FROM Player as plr WHERE plr.name = ?";
		int count = entityManager.createQuery(hql).setParameter(1, name)
		              .getResultList().size();
		return count > 0 ? true : false;
	}

}
