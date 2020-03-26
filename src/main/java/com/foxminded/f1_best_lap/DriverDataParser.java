package com.foxminded.f1_best_lap;

import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DriverDataParser {
	
	public Map<String, String> parse(String fileName) {
		ClassLoader classLoader = ClassLoader.getSystemClassLoader();
		Path filePath;
		
		try {
			filePath = Paths.get(classLoader.getResource(fileName).toURI());
		} catch (URISyntaxException e) {
			return new HashMap<>();
		}
		
		try (Stream<String> stream = Files.lines(filePath)) {
			return stream.map(line -> line.split("_"))
					.collect(Collectors.toMap(s -> s[0], s -> s[1] + "_" + s[2]));
		} catch (Exception e) {
			return new HashMap<>();
		}
	}
}
