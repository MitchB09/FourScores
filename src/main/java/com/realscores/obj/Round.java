package com.realscores.obj;

import java.io.Serializable;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="round")
public class Round implements Serializable {

	private static final long serialVersionUID = 7745335780792154475L;

	@Id
	@Column(name="round_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int round_id;
	
	@Column(name="course_id")
	private int courseId;
	
	@Column(name="date")
  	private Date startTime;
	
	@OneToMany
	@JoinColumn(name = "round_id")
	private List<PlayerRound> playerRounds = new ArrayList<PlayerRound>();
  	
	public int getRoundId() {
	  	return round_id;
  	}
  
  	public void setId(int round_id) {
		this.round_id = round_id;
	}
	
  	public int getCourseId() {
		return courseId;
	}
	
  	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	
  	public Date getStartTime() {
		return startTime;
	}
	
  	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public List<PlayerRound> getPlayerRounds() {
		return playerRounds;
	}

	public void setPlayerRounds(List<PlayerRound> playerRounds) {
		this.playerRounds = playerRounds;
	}
	
}

