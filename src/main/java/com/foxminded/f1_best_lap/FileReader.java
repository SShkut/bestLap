package com.foxminded.f1_best_lap;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class FileReader {
	
	public Stream<String> readFile(String fileName) throws IOException {

		Path filePath = getAbsolutePath(fileName);
		return Files.lines(filePath);
	}
	
	private Path getAbsolutePath(String fileName) throws IOException {
		ClassLoader classLoader = ClassLoader.getSystemClassLoader();
		try {
			return Paths.get(classLoader.getResource(fileName).toURI());
		} catch (URISyntaxException e) {
			return Paths.get("");
		} catch (NullPointerException e) {
			throw new FileNotFoundException();
		}
	}
}
