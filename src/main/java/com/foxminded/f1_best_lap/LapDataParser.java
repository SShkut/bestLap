package com.foxminded.f1_best_lap;

import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LapDataParser {
	
	private LapDataParser() {
		throw new IllegalStateException("Utility class");
	}

	public static List<Lap> parse(String fileName) {
		ClassLoader classLoader = ClassLoader.getSystemClassLoader();
		Path filePath;
		try {
			filePath = Paths.get(classLoader.getResource(fileName).toURI());
		} catch (URISyntaxException e1) {
			return new ArrayList<>();
		}
		try (Stream<String> stream = Files.lines(filePath)) {
			return stream.map(LapConverter::convert).collect(Collectors.toList());
		} catch (Exception e) {
			return new ArrayList<>();
		}
	}
}
