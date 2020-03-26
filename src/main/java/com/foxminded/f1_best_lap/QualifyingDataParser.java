package com.foxminded.f1_best_lap;

import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class QualifyingDataParser {
	
	private QualifyingDataParser() {
		throw new IllegalStateException("Utility class");
	}

	public static Map<String, LocalDateTime> parse(String fileName) {
		ClassLoader classLoader = ClassLoader.getSystemClassLoader();
		Path filePath;
		
		try {
			filePath = Paths.get(classLoader.getResource(fileName).toURI());
		} catch (URISyntaxException e) {
			return new HashMap<>();
		}
		
		try (Stream<String> stream = Files.lines(filePath)) {
			DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss.SSS");
			return stream.map(line -> line.split("_"))
					.collect(Collectors.toMap(s -> s[0].substring(0, 3), s -> LocalDateTime.parse(s[0].substring(3) + "_" + s[1], pattern)));
					
		} catch (Exception e) {
			return new HashMap<>();
		}
	}
}
