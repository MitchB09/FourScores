package com.realscores.dao.round;

import java.util.List;

import com.realscores.obj.Round;

public interface IRoundDao {
	Round getRoundById(int roundId);
	List<Round> getAllRounds();
	List<Round> getRoundsByCourseId(int playerRoundId);
    void addRound(Round round);
    void updateRound(Round round);
    void deleteRound(int roundId);
    boolean roundExists(int roundId);
}
