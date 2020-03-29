package com.foxminded.f1_best_lap;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RacerRepository {

	public List<Racer> getRacers() {
		
		RacerConverter racerConverter = new RacerConverter();
		Map<String, LocalDateTime> qualifyingStartData = getDataFrom(getAbsolutePath("start.txt"));
		Map<String, LocalDateTime> qualifyingEndData = getDataFrom(getAbsolutePath("end.txt")); 
				
		try (Stream<String> stream = Files.lines(getAbsolutePath("abbreviations.txt"))) {
		return stream
				.map(line -> racerConverter.convert(line, qualifyingStartData, qualifyingEndData))
				.collect(Collectors.toList());
		} catch (IOException e) {
			return new ArrayList<>();
		}
	}
	
	private Map<String, LocalDateTime> getDataFrom(Path filePath) {
		try (Stream<String> stream = Files.lines(filePath)) {
			DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss.SSS");
			return stream.map(line -> line.split("_"))
					.collect(Collectors.toMap(s -> s[0].substring(0, 3), s -> LocalDateTime.parse(s[0].substring(3) + "_" + s[1], pattern)));
					
		} catch (Exception e) {
			return new HashMap<>();
		}
	}
	
	private Path getAbsolutePath(String fileName) {
		ClassLoader classLoader = ClassLoader.getSystemClassLoader();
	
		try {
			return Paths.get(classLoader.getResource(fileName).toURI());
		} catch (URISyntaxException e) {
			return Paths.get("");
		}
	}
}
