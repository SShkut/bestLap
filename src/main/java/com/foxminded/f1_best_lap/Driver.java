package com.foxminded.f1_best_lap;

import java.time.LocalTime;

public class Driver {

	private final String name;
	private final String team;
	private final LocalTime qualifyingTime;
	
	public Driver(String name, String team, LocalTime qualifyingTime) {
		this.name = name;
		this.team = team;
		this.qualifyingTime = qualifyingTime;
	}

	public String getName() {
		return name;
	}

	public String getTeam() {
		return team;
	}

	public LocalTime getQualifyingTime() {
		return qualifyingTime;
	}	
}
