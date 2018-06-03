package com.realscores.obj;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="player_round")
public class PlayerRound implements Serializable{

	private static final long serialVersionUID = -7642795917197186404L;

	@Id
	@Column(name="player_round_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int player_round_id;
	
	@Column(name="round_id")
	private int round_id;
	
	@ManyToOne
	@JoinColumn(name = "player_id")
	private Player player;

	@OneToMany
	@JoinColumn(name = "player_round_id")
	private List<HoleScore> scores;

	public int getRoundId() {
		return round_id;
	}
	
	public int getPlayerRoundId() {
		return player_round_id;
	}

	public void setPlayerRoundId(int player_round_id) {
		this.player_round_id = player_round_id;
	}

	public void setRoundId(int round_id) {
		this.round_id = round_id;
	}

	public Player getPlayer() {
		return player;
	}	

	public void setPlayer(Player player) {
		this.player = player;
	}

	public List<HoleScore> getScores() {
		return scores;
	}

	public void setScores(List<HoleScore> scores) {
		this.scores = scores;
	}
	
	public int getTotalStokes() {
		int totalStrokes = 0;
		for (HoleScore holeScore: scores){
			totalStrokes += holeScore.getStrokes();
		}
		return totalStrokes;
	}
}
