package com.foxminded.f1_best_lap;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DriverService {
	
	private final QualifyingDataParser qualifyingDataParser;
	private final DriverDataParser driverDataParser;
	
	public DriverService(QualifyingDataParser qualifyingDataParser, DriverDataParser driverDataParser) {
		this.qualifyingDataParser = qualifyingDataParser;
		this.driverDataParser = driverDataParser;
	}

	public List<Driver> getDrivers() {
		Map<String, LocalDateTime> qualifyingStartData;
		Map<String, LocalDateTime> qualifyingEndData;
		Map<String, String> driverData;
		
		qualifyingStartData = qualifyingDataParser.parse("start.log");
		qualifyingEndData = qualifyingDataParser.parse("end.log");
		driverData = driverDataParser.parse("abbreviations.txt");
				
		return driverData.entrySet()
				.stream()
				.map(abbr -> {
					String[] nameAndTeam = abbr.getValue().split("_");
					String name = nameAndTeam[0];
					String team = nameAndTeam[1];
					Duration duration = Duration.between(qualifyingStartData.get(abbr.getKey()), qualifyingEndData.get(abbr.getKey()));
					return new Driver(name, team, duration);
				})
				.collect(Collectors.toList());
	}
}
