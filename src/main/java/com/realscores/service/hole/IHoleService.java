package com.realscores.service.hole;

import java.util.List;

import com.realscores.obj.Hole;

public interface IHoleService {
	List<Hole> getHolesByCourseId(int courseId);
	Hole getHoleByHoleId(int holeId);
    boolean addHole(Hole hole);
    void updateHole(Hole hole);
    void deleteHole(int holeId);
}
