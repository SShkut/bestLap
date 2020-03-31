package com.foxminded.f1_best_lap;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class FileReader {
	
	public Stream<String> readFile(String fileName) {
		Path filePath = getAbsolutePath(fileName);
		try {
			return Files.lines(filePath);
		} catch (IOException e) {
			return Stream.empty();
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
