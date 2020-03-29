package com.foxminded.f1_best_lap;

import java.time.LocalTime;
import java.util.Arrays;

public class Formatter {

	public String format(Racer racer, int index) {
		
		if (racer == null || index <= 0) {
			return "";
		}
		
		char[] offset = new char[20];
		Arrays.fill(offset, ' ');

		LocalTime timeSpent = LocalTime.ofSecondOfDay(racer.getBestLapTime().getSeconds()).plusNanos(racer.getBestLapTime().getNano());
		if (index == 15) {
			return String.format("%-25s%-30s%-15s%n", String.valueOf(index) + ". " + racer.getName(), "|" + racer.getTeam(), "|" + timeSpent)
					+ String.format("%s%n", "------------------------------------------------------------------------");			
		}
		return String.format("%-25s%-30s%-15s%n", String.valueOf(index) + ". " + racer.getName(), "|" + racer.getTeam(), "|" + timeSpent);
	}
}

