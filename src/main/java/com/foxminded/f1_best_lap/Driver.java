package com.foxminded.f1_best_lap;

import java.time.Duration;

public class Driver {

	private final String name;
	private final String team;
	private final Duration bestLapTime;
	
	public Driver(String name, String team, Duration bestLapTime) {
		this.name = name;
		this.team = team;
		this.bestLapTime = bestLapTime;
	}

	public String getName() {
		return name;
	}

	public String getTeam() {
		return team;
	}

	public Duration getBestLapTime() {
		return bestLapTime;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bestLapTime == null) ? 0 : bestLapTime.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((team == null) ? 0 : team.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Driver other = (Driver) obj;
		if (bestLapTime == null) {
			if (other.bestLapTime != null)
				return false;
		} else if (!bestLapTime.equals(other.bestLapTime))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (team == null) {
			if (other.team != null)
				return false;
		} else if (!team.equals(other.team))
			return false;
		return true;
	}	
}
