package com.realscores.obj;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

import javax.persistence.*;

@Entity(name = "Hole")
@Table(name = "hole")
public class Hole implements Serializable {

	private static final long serialVersionUID = 7862178050906377236L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="hole_id")
    private int hole_id;

	@ManyToOne(targetEntity = Course.class, fetch=FetchType.LAZY)
	@JoinColumn(name="course_id", insertable=false, updatable=false)
	private Course course;
	
	@Column(name="par")
	private int par;
	
	@Column(name="yards")
	private int yards;
	
	@Column(name="number")
	private int holeNumber;

	public int getHoleId() {
		return hole_id;
	}

	public void setHoleId(int hole_id) {
		this.hole_id = hole_id;
	}

    @JsonIgnore
	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public int getPar() {
		return par;
	}

	public void setPar(int par) {
		this.par = par;
	}

	public int getYards() {
		return yards;
	}

	public void setYards(int yards) {
		this.yards = yards;
	}

	public int getHoleNumber() {
		return holeNumber;
	}

	public void setHoleNumber(int holeNumber) {
		this.holeNumber = holeNumber;
	}

}
