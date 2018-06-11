package com.realscores.service.hole;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.realscores.dao.hole.IHoleDao;
import com.realscores.obj.Hole;

@Service
public class HoleService implements IHoleService {

	@Autowired
	private IHoleDao holeDao;

	@Override
	public List<Hole> getHolesByCourseId(int courseId) {
		return holeDao.getHolesByCourseId(courseId);
	}

	@Override
	public Hole getHoleByHoleId(int holeId) {
		return holeDao.getHoleByHoleId(holeId);
	}

	@Override
	public boolean addHole(Hole hole) {
		if (holeDao.HoleExists(hole.getCourseId(), hole.getHoleId())){
			return false;
		} else {
			holeDao.addHole(hole);
		    return true;
		}
	}

	@Override
	public void updateHole(Hole hole) {
		holeDao.updateHole(hole);
	}

	@Override
	public void deleteHole(int holeId) {
		holeDao.deleteHole(holeId);
	}

}
