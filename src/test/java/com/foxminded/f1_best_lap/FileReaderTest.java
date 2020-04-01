package com.foxminded.f1_best_lap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FileReaderTest {
	
	FileReader reader;
	
	@BeforeEach
	void setUp() {
		reader = new FileReader();
	}
	
	@Test
	void givenExistentFile_whenReadFile_thenReturnStream() throws IOException {
		String newLine = System.lineSeparator();
		String expected = String.format("%s%n%s", "MES2018-05-24_12:05:58.778", "RGH2018-05-24_12:06:27.441");
		
		String actual = reader.readFile("test.txt").collect(Collectors.joining(newLine));
		
		assertEquals(expected, actual);
	}
	
	@Test 
	void givenNotExistentFileName_whenReadFile_thenThrowException() {
		
		assertThrows(FileNotFoundException.class, () -> reader.readFile("notExists.txt"));
	}
}
