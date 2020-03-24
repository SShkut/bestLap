package com.foxminded.f1_best_lap;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

public class LapConverter {
	
	private LapConverter() {
		throw new IllegalStateException("Utility class");
	}
	
	public static Lap convert(String text) {
		try {
			if (text.isEmpty()) {
				return new Lap(null, null, null);
			}
			
			String abbreviation = text.substring(0, 3);
			List<String> dateTimeInfo = Arrays.asList(text.substring(3).split("_"));
			DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate date = LocalDate.parse(dateTimeInfo.get(0), dateFormatter);
			LocalTime time = LocalTime.parse(dateTimeInfo.get(1));
	
			return new Lap(abbreviation, date, time);
		} catch (Exception e) {
			return new Lap(null, null, null); 
		}
	}
}
