package com.realscores.obj.builder;

import java.util.ArrayList;
import java.util.List;

import com.realscores.obj.Hole;

public class HoleBuilder {
	
	public static Hole createHole(int holeId){
		Hole hole = new Hole();
		hole.setHoleId(holeId);
		hole.setHoleNumber(holeId);
		hole.setPar(holeId % 3 + 3);
		hole.setYards((holeId % 3 + 1) * 200);
		return hole;
	}
	
	public static Hole createCustomHole(int holeId, int holeNumber, int par, int yards){
		Hole hole = new Hole();
		hole.setHoleId(holeId);
		hole.setHoleNumber(holeNumber);
		hole.setPar(par);
		hole.setYards(yards);
		return hole;
	}
	
	public static List<Hole> createHoleList(int numHoles){
		List<Hole> holes = new ArrayList<Hole>();
		for(int i = 1; i <= numHoles; i++) {
			holes.add(HoleBuilder.createHole(i));
		}
		return holes; 
	}
}
