package com.foxminded.f1_best_lap;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Map;

public class RacerConverter {

	public Racer convert(String text, Map<String, LocalDateTime> startData, Map<String, LocalDateTime> endData) {
		if (text.equals("") || startData.isEmpty() || endData.isEmpty()) {
			return null;
		}
		
		String[] racerData = text.split("_");
		String abbreviation = racerData[0];
		String name = racerData[1];
		String team = racerData[2];
		Duration duration = Duration.between(startData.get(abbreviation), endData.get(abbreviation));
		return new Racer(abbreviation, name, team, duration);		
	}
}
