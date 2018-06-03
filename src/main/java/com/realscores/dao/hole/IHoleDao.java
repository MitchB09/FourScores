package com.realscores.dao.hole;

import java.util.List;

import com.realscores.obj.Hole;

public interface IHoleDao {
	List<Hole> getHolesByCourseId(int courseId);
	Hole getHoleByHoleId(int holeId);
    void addHole(Hole hole);
    void updateHole(Hole hole);
    void deleteHole(int holeId);
    boolean HoleExists(int courseId, int number);
}
