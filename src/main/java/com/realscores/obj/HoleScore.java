package com.realscores.obj;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name="Hole_score")
@Table(name="hole_score")
public class HoleScore implements Serializable{

	private static final long serialVersionUID = -6778088324391438214L;

	@Id
	@ManyToOne(targetEntity = PlayerRound.class)
	@JoinColumn(name = "player_round_id")
	private int player_round_id;
	
	@Id
	@ManyToOne(targetEntity = Hole.class)
	@JoinColumn(name="hole_id")
	private Hole hole;
	
	@Column(name="strokes")
	private int strokes;
		
	@Column(name="gir")
	private Boolean GIR;
	
	@Column(name="fairway")
	@Enumerated(EnumType.ORDINAL)
	private FairwayHitEnum fairwayHit;
	
	@Column(name="putts")
	private Integer putts;

	public int getPlayerRoundId() {
		return player_round_id;
	}

	public void setPlayerRoundId(int player_round_id) {
		this.player_round_id = player_round_id;
	}

	public Hole getHole() {
		return hole;
	}

	public void setHole(Hole hole) {
		this.hole = hole;
	}

	public int getStrokes() {
		return strokes;
	}

	public void setStrokes(int strokes) {
		this.strokes = strokes;
	}
	
	public String getScore() {
		if (this.hole != null){
			int score = strokes - hole.getPar();
			if (score > 0){
				return "+" + String.valueOf(score);
			}
			return String.valueOf(score);
		} else {
			return null;
		}
	}

	public Boolean isGIR() {
		return GIR;
	}

	public void setGIR(Boolean GIR) {
		this.GIR = GIR;
	}

	public FairwayHitEnum getFairwayHit() {
		return fairwayHit;
	}

	public void setFairwayHit(FairwayHitEnum fairwayHit) {
		this.fairwayHit = fairwayHit;
	}

	public Integer getPutts() {
		return putts;
	}

	public void setPutts(Integer putts) {
		this.putts = putts;
	}
}
