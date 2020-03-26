package com.foxminded.f1_best_lap;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

class LapConverterTest {

	@Test
	void givenString_whenConvert_thenLapObject() {
		String text = "SVF2018-05-24_12:02:58.917";
		DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss.SSS");
		LocalDateTime expectedDateTime = LocalDateTime.parse("2018-05-24_12:02:58.917", pattern);
		Map<String, LocalDateTime> expected = new HashMap<>();
		expected.put("SVF", expectedDateTime);

		Map<String, LocalDateTime> actual = DriverConverter.convert(text);

		assertEquals(expected, actual);
	}

	@Test
	void givenEmptyString_whenConvert_thenNullObject() {
		String text= "";
		Map<String, LocalDateTime> expected = new HashMap<>();
		
		Map<String, LocalDateTime> actual = DriverConverter.convert(text);
		
		assertEquals(expected, actual);
	}
	
	@Test
	void givenIncorrectData_whenConvert_thenNullObject() {
		String text = "absfd";
		Map<String, LocalDateTime> expected = new HashMap<>();
		
		Map<String, LocalDateTime> actual = DriverConverter.convert(text);
		
		assertEquals(expected, actual);
	}
}
