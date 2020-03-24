package com.foxminded.f1_best_lap;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LapDataParser {
	
	private LapDataParser() {
		throw new IllegalStateException("Utility class");
	}

	public static List<Lap> parse(Path fileName) {
		try (Stream<String> stream = Files.lines(fileName)) {
			return stream.map(LapConverter::convert).collect(Collectors.toList());
		} catch (Exception e) {
			return new ArrayList<>();
		}
	}
}
