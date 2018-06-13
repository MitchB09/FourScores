package com.realscores.dao.round;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.realscores.obj.Round;
import com.realscores.obj.RoundFilter;

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
		List<Round> rounds = entityManager.createQuery(hql).setParameter(0, courseId)
		              .getResultList();
		return rounds;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Round> searchRounds(RoundFilter roundFilter){
		String hql = "FROM Round as rd WHERE 1=1";
		Map<Integer, Object> params = new HashMap<>();
		int paramCount = 0;
		if(roundFilter != null){
			if(roundFilter.getCourseId() != null){
				hql += " AND rd.course_id = ?" ;
				params.put(paramCount, roundFilter.getCourseId());
				paramCount++;
			}
			if(roundFilter.getStartDate() != null){
				hql += " AND rd.date >= ?" ;
				params.put(paramCount, roundFilter.getStartDate());
				paramCount++;
			}
			if(roundFilter.getEndDate() != null){
				hql += " AND rd.date <= ?" ;
				params.put(paramCount, roundFilter.getEndDate());
				paramCount++;
			}
		}
		
		Query query = entityManager.createQuery(hql);
		for (int i = 0; i < paramCount; i++){
			query.setParameter(i, params.get(i));
		}
		return query.getResultList();
	}


	@Override
	public void addRound(Round round) {
		entityManager.persist(round);
	}

	@Override
	public void updateRound(Round round) {
		Round foundRound = getRoundById(round.getRoundId());
		foundRound.setCourseId(round.getCourseId());
		foundRound.setDate(round.getDate());
		entityManager.flush();
	}

	@Override
	public void deleteRound(int roundId) {
		entityManager.remove(getRoundById(roundId));
	}

	@Override
	public boolean roundExists(int roundId) {
		String hql = "FROM Round as rd WHERE rd.round_id = ?";
		int count = entityManager.createQuery(hql).setParameter(0, roundId)
		              .getResultList().size();
		return count > 0 ? true : false;
	}

}
