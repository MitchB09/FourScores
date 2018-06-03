package com.realscores.obj;

import java.io.Serializable;
import java.util.ArrayList;
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
@Table(name="course")
public class Course implements Serializable { 

	private static final long serialVersionUID = -2366507745359730865L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="course_id")
    private int course_id;
	
	@Column(name="name")
    private String name;

	@OneToMany
	@JoinColumn(name = "course_id")
	private List<Hole> holes = new ArrayList<Hole>();

	public int getCourseId() {
		return course_id;
	}

	public void setCourseId(int course_id) {
		this.course_id = course_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Hole> getHoles() {
		return holes;
	}

	public void setHoles(List<Hole> holes) {
		this.holes = holes;
	}

}
