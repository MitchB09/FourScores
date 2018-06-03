package com.realscores.dao.hole;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.realscores.obj.Hole;

@Transactional
@Repository
public class HoleDao implements IHoleDao {

	@PersistenceContext	
	private EntityManager entityManager;	
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Hole> getHolesByCourseId(int courseId) {
		String hql = "FROM Hole as h WHERE h.course_id = ?";
		List<Hole> hole = entityManager.createQuery(hql).setParameter(1, courseId)
		              .getResultList();
		return hole;
	}

	@Override
	public Hole getHoleByHoleId(int holeId) {
		return entityManager.find(Hole.class, holeId);
	}

	@Override
	public void addHole(Hole hole) {
		entityManager.persist(hole);
	}

	@Override
	public void updateHole(Hole hole) {
		Hole foundHole = getHoleByHoleId(hole.getHoleId());
		foundHole.setHoleNumber(hole.getHoleNumber());
		foundHole.setPar(hole.getPar());
		foundHole.setYards(hole.getYards());
		entityManager.flush();
	}

	@Override
	public void deleteHole(int holeId) {
		entityManager.remove(getHoleByHoleId(holeId));
	}

	@Override
	public boolean HoleExists(int courseId, int number) {
		String hql = "FROM Hole as h WHERE h.course_id = ? AND h.number = ?";
		int count = entityManager.createQuery(hql).setParameter(1, courseId).setParameter(2, number)
		              .getResultList().size();
		return count > 0 ? true : false;
	}

}
