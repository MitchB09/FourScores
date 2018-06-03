package com.realscores.service.round;

import java.util.List;

import com.realscores.obj.Round;

public interface IRoundService {
	List<Round> getAllRounds();
	Round getRoundById(int roundId);
	List<Round> getRoundsByCourseId(int courseId);
    boolean addRound(Round round);
    void updateRound(Round round);
    void deleteRound(int roundId);
}
