package com.foxminded.f1_best_lap;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class LapReport {

	public static List<Lap> createReport(List<Lap> startData, List<Lap> endData) {
		List<Lap> report = new ArrayList<>();
		for (Lap start : startData) {
			for (Lap end : endData) {
				if (start.getPilotTeamAbbreviatoin().equals(end.getPilotTeamAbbreviatoin())
						&& start.getDate().equals(end.getDate())) {
					Duration duration = Duration.between(start.getTime(), end.getTime());
					LocalTime timePassed = LocalTime.ofSecondOfDay(duration.getSeconds()).plusNanos(duration.getNano());
					report.add(new Lap(start.getPilotTeamAbbreviatoin(), start.getDate(), timePassed));
				}
			}
		}
		return report;
	}
}
