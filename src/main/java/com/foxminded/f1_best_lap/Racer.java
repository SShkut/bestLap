package com.foxminded.f1_best_lap;

import java.time.Duration;

public class Racer {

	private final String abbreviation;
	private final String name;
	private final String team;
	private final Duration bestLapTime;
	
	public Racer(String abbreviation, String name, String team, Duration bestLapTime) {
		this.abbreviation = abbreviation;
		this.name = name;
		this.team = team;
		this.bestLapTime = bestLapTime;
	}

	public String getAbbreviation() {
		return abbreviation;
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
		result = prime * result + ((abbreviation == null) ? 0 : abbreviation.hashCode());
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
		Racer other = (Racer) obj;
		if (abbreviation == null) {
			if (other.abbreviation != null)
				return false;
		} else if (!abbreviation.equals(other.abbreviation))
			return false;
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
