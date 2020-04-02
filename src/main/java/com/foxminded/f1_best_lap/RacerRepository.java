package com.foxminded.f1_best_lap;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RacerRepository {
	
	FileReader fileReader;
	
	public RacerRepository(FileReader fileReader) {
		this.fileReader = fileReader;
	}

	public List<Racer> getRacers() {
		
		RacerConverter racerConverter = new RacerConverter();
		Map<String, LocalDateTime> qualifyingStartData = getDataFrom("start.txt");
		Map<String, LocalDateTime> qualifyingEndData = getDataFrom("end.txt");
		
		if (qualifyingEndData.isEmpty() || qualifyingStartData.isEmpty()) {
			return new ArrayList<>();
		}

		try(Stream<String> stream = fileReader.readFile("abbreviations.txt")) {
			return stream.map(line -> racerConverter.convert(line, qualifyingStartData, qualifyingEndData))
					.collect(Collectors.toList());
		} catch(Exception e) {
			return new ArrayList<>();
		}
	}
	
	private Map<String, LocalDateTime> getDataFrom(String fileName) {
		try (Stream<String> stream = fileReader.readFile(fileName)) {
			DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss.SSS");
			return stream.map(line -> line.split("_"))
					.collect(Collectors.toMap(s -> s[0].substring(0, 3), s -> LocalDateTime.parse(s[0].substring(3) + "_" + s[1], pattern)));
					
		} catch (Exception e) {
			return new HashMap<>();
		}
	}
}
