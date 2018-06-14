package com.realscores.obj;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name="hole_score")
public class HoleScore implements Serializable{

	private static final long serialVersionUID = -6778088324391438214L;

    @Id
    @Column(name="hole_score_id")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer hole_score_id;

    @ManyToOne(targetEntity = PlayerRound.class, fetch=FetchType.LAZY)
    @JoinColumn(name="player_round_id", insertable=false, updatable=false)
    private PlayerRound player_round;

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

	public Integer getHoleScoreId(){
	    return hole_score_id;
    }

    public void setHoleScoreId(Integer hole_score_id){
	    this.hole_score_id = hole_score_id;
    }

    @JsonIgnore
    public PlayerRound getPlayerRound() {
        return player_round;
    }

    public void setPlayerRound(PlayerRound player_round) {
        this.player_round = player_round;
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
		if (this.getHole() != null){
			int score = strokes - this.getHole().getPar();
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
