package com.realscores.service.roundscore;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.realscores.dao.holescore.IHoleScoreDao;
import com.realscores.obj.HoleScore;

@Service
public class HoleScoreService implements IHoleScoreService {

	@Autowired
	private IHoleScoreDao holeScoreDao; 
	
	@Override
	public HoleScore getHoleScoreById(int holeScoreId) {
		return holeScoreDao.getHoleScoreById(holeScoreId);
	}

	@Override
	public List<HoleScore> getHoleScoreByPlayerRoundId(int playerRoundId) {
		return holeScoreDao.getHoleScoreByPlayerRoundId(playerRoundId);
	}

	@Override
	public List<HoleScore> getHoleScoresByRoundId(int roundId) {
		return holeScoreDao.getHoleScoresByRoundId(roundId);
	}

	@Override
	public boolean addHoleScore(HoleScore holeScore) {
		if (holeScoreDao.HoleScoreExists(holeScore.getPlayerRound().getPlayerRoundId(), holeScore.getHole().getHoleId())){
			return false;
		} else {
			holeScoreDao.addHoleScore(holeScore);
			return true;
		}
	}

	@Override
	public void updateHoleScore(HoleScore holeScore) {
		holeScoreDao.updateHoleScore(holeScore);
	}

	@Override
	public void deleteHoleScore(int holeScoreId) {
		holeScoreDao.deleteHoleScore(holeScoreId);
	}

}
