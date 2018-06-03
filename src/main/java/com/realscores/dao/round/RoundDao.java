package com.realscores.dao.round;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.realscores.obj.Round;

@Transactional
@Repository
public class RoundDao implements IRoundDao {

	@PersistenceContext	
	private EntityManager entityManager;
	
	@Override
	public Round getRoundById(int roundId) {
		return entityManager.find(Round.class, roundId);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Round> getAllRounds() {
		String hql = "FROM Round as rd ORDER BY rd.round_id";
		return (List<Round>) entityManager.createQuery(hql).getResultList();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Round> getRoundsByCourseId(int courseId) {
		String hql = "FROM Round as rd WHERE rd.course_id = ?";
		List<Round> rounds = entityManager.createQuery(hql).setParameter(1, courseId)
		              .getResultList();
		return rounds;
	}

	@Override
	public void addRound(Round round) {
		entityManager.persist(round);
	}

	@Override
	public void updateRound(Round round) {
		Round foundRound = getRoundById(round.getRoundId());
		foundRound.setCourseId(round.getCourseId());
		foundRound.setStartTime(round.getStartTime());
		entityManager.flush();
	}

	@Override
	public void deleteRound(int roundId) {
		entityManager.remove(getRoundById(roundId));
	}

	@Override
	public boolean roundExists(int roundId) {
		String hql = "FROM Round as rd WHERE rd.round_id = ?";
		int count = entityManager.createQuery(hql).setParameter(1, roundId)
		              .getResultList().size();
		return count > 0 ? true : false;
	}

}
