package com.realscores.mvc.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriComponentsBuilder;

import com.realscores.obj.Round;
import com.realscores.obj.RoundFilter;
import com.realscores.service.round.IRoundService;

@Controller
@RequestMapping()
public class RoundController {
	
	@Autowired
	IRoundService roundService;
	
	@GetMapping("/rounds")
	public ResponseEntity<List<Round>> getRounds(){	
		List<Round> rounds = roundService.getAllRounds();
		return new ResponseEntity<List<Round>>(rounds, HttpStatus.OK);
	}
	
	@GetMapping("/rounds/{id}")
	public ResponseEntity<Round> getRoundById(
			@PathVariable("id") int roundId){		
		Round round = roundService.getRoundById(roundId);
		return new ResponseEntity<Round>(round, HttpStatus.OK);
	}
	
	@GetMapping("/rounds/search")
	public ResponseEntity<List<Round>> searchRounds(
			@RequestParam(required=false) Integer courseId,
			@RequestParam(required=false) @DateTimeFormat(pattern="yyyy-MM-dd") Date startDate,
			@RequestParam(required=false) @DateTimeFormat(pattern="yyyy-MM-dd") Date endDate){
		List<Round> rounds = roundService.searchRounds(new RoundFilter(courseId, startDate, endDate));
		return new ResponseEntity<List<Round>>(rounds, HttpStatus.OK);
	}
	
	@PostMapping(name="/rounds", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> createRound(@RequestBody Round round, UriComponentsBuilder builder){		
		boolean flag = roundService.addRound(round);
		if (flag == false) {
	      return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/rounds/{id}").buildAndExpand(round.getRoundId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	
	@PutMapping("/rounds")
	public ResponseEntity<Round> updateRound(@RequestBody Round round) {
		roundService.updateRound(round);
		return new ResponseEntity<Round>(round, HttpStatus.OK);
	}

	@DeleteMapping("/rounds/{id}")
	public ResponseEntity<Void> deleteRound(@PathVariable("id") Integer id) {
		roundService.deleteRound(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}	
	
}
