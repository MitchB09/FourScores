package com.realscores.obj;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="player_round")
public class PlayerRound implements Serializable{

	private static final long serialVersionUID = -7642795917197186404L;

	@Id
	@Column(name="player_round_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer player_round_id;

	@JsonIgnore
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="round_id", insertable=false, updatable=false)
	private Round round;
	
	@ManyToOne
	@JoinColumn(name = "player_id")
	private Player player;

	@NotNull
    @OneToMany(cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
	@JoinColumn(name = "player_round_id", nullable = false)
	private List<HoleScore> scores;

	public Round getRound() {
		return round;
	}
	
	public Integer getPlayerRoundId() {
		return player_round_id;
	}

	public void setPlayerRoundId(Integer player_round_id) {
		this.player_round_id = player_round_id;
	}

	public void setRound(Round round) {
		this.round = round;
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
	
	public int getTotalStrokes() {
		int totalStrokes = 0;
		for (HoleScore holeScore: scores){
			totalStrokes += holeScore.getStrokes();
		}
		return totalStrokes;
	}
}
