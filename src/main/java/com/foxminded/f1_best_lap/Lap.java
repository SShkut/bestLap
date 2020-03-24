package com.foxminded.f1_best_lap;

import java.time.LocalDate;
import java.time.LocalTime;

public class Lap {
	
	private final String pilotTeamAbbreviatoin;
	private final LocalDate date;
	private final LocalTime time;
	
	public Lap(String pilotTeamAbbreviatoin, LocalDate date, LocalTime time) {
		this.pilotTeamAbbreviatoin = pilotTeamAbbreviatoin;
		this.date = date;
		this.time = time;
	}

	public String getPilotTeamAbbreviatoin() {
		return pilotTeamAbbreviatoin;
	}

	public LocalDate getDate() {
		return date;
	}

	public LocalTime getTime() {
		return time;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((pilotTeamAbbreviatoin == null) ? 0 : pilotTeamAbbreviatoin.hashCode());
		result = prime * result + ((time == null) ? 0 : time.hashCode());
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
		Lap other = (Lap) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (pilotTeamAbbreviatoin == null) {
			if (other.pilotTeamAbbreviatoin != null)
				return false;
		} else if (!pilotTeamAbbreviatoin.equals(other.pilotTeamAbbreviatoin))
			return false;
		if (time == null) {
			if (other.time != null)
				return false;
		} else if (!time.equals(other.time))
			return false;
		return true;
	}
}
