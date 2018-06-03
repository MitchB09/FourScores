package com.realscores.dao.holescore;

import java.util.List;

import com.realscores.obj.HoleScore;

public interface IHoleScoreDao {
	HoleScore getHoleScoreById(int roundScoreId);
	List<HoleScore> getHoleScoreByPlayerRoundId(int playerRoundId);
	List<HoleScore> getHoleScoresByRoundId(int roundId);
    void addHoleScore(HoleScore holeScore);
    void updateHoleScore(HoleScore holeScore);
    void deleteHoleScore(int roundScoreId);
    boolean HoleScoreExists(int playerRoundId, int holeId);
}
