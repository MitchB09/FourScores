package com.realscores.service.roundscore;

import java.util.List;

import com.realscores.obj.HoleScore;

public interface IHoleScoreService {
	HoleScore getHoleScoreById(int holeScoreId);
	List<HoleScore> getHoleScoreByPlayerRoundId(int playerRoundId);
	List<HoleScore> getHoleScoresByRoundId(int roundId);
    boolean addHoleScore(HoleScore holeScore);
    void updateHoleScore(HoleScore holeScore);
    void deleteHoleScore(int holeScoreId);
}
