package com.realscores.dao.holescore;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.realscores.obj.HoleScore;

@Transactional
@Repository
public class HoleScoreDao implements IHoleScoreDao {

	@PersistenceContext	
	private EntityManager entityManager;

	@Override
	public HoleScore getHoleScoreById(int holeScoreId) {
		return entityManager.find(HoleScore.class, holeScoreId);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<HoleScore> getHoleScoreByPlayerRoundId(int playerRoundId) {
		String hql = "FROM Hole_score AS hs WHERE hs.player_round_id = ?";
		List<HoleScore> holeScores = entityManager.createQuery(hql).setParameter(0, playerRoundId)
		              .getResultList();
		return holeScores;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<HoleScore> getHoleScoresByRoundId(int playerRoundId) {
		String hql = "FROM Hole_scores as hs WHERE hs.player_round_id = ?";
		List<HoleScore> holeScores = entityManager.createQuery(hql).setParameter(0, playerRoundId)
						.getResultList();
		return holeScores;
	}
	
	@Override
	public void addHoleScore(HoleScore holeScore) {
		entityManager.persist(holeScore);
	}

	@Override
	public void updateHoleScore(HoleScore holeScore) {
		HoleScore foundHoleScore = getHoleScoreById(holeScore.getPlayerRound().getPlayerRoundId());
		foundHoleScore.setHole(holeScore.getHole());
		foundHoleScore.setStrokes(holeScore.getStrokes());
		foundHoleScore.setGIR(holeScore.isGIR());
		foundHoleScore.setFairwayHit(holeScore.getFairwayHit());
		foundHoleScore.setPutts(holeScore.getPutts());
		entityManager.flush();
	}

	@Override
	public void deleteHoleScore(int holeScoreId) {
		entityManager.remove(getHoleScoreById(holeScoreId));
	}

	@Override
	public boolean HoleScoreExists(int playerRoundId, int holeId) {
		String hql = "FROM Round_score as rdsc WHERE rdsc.player_round_id = ? AND rdsc.hole_id = ?";
		int count = entityManager.createQuery(hql).setParameter(1, playerRoundId).setParameter(2, holeId)
		              .getResultList().size();
		return count > 0 ? true : false;
	}

}
