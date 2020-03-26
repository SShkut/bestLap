package com.foxminded.f1_best_lap;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DriverService {

	public List<Driver> getDrivers() {
		Map<String, LocalDateTime> qualifyingStartData;
		Map<String, LocalDateTime> qualifyingEndData;
		Map<String, String> driverData;
		
		qualifyingStartData = QualifyingDataParser.parse("start.log");
		qualifyingEndData = QualifyingDataParser.parse("end.log");
		driverData = DriverDataParser.parse("abbreviations.txt");
				
		return driverData.entrySet()
				.stream()
				.map(abbr -> {
					String[] nameAndTeam = abbr.getValue().split("_");
					String name = nameAndTeam[0];
					String team = nameAndTeam[1];
					Duration duration = Duration.between(qualifyingStartData.get(abbr.getKey()), qualifyingEndData.get(abbr.getKey()));
					LocalTime qualifyingTime = LocalTime.ofSecondOfDay(duration.getSeconds()).plusNanos(duration.getNano());
					return new Driver(name, team, qualifyingTime);
				})
				.collect(Collectors.toList());
	}
}
