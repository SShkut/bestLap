package com.foxminded.f1_best_lap;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

class LapConverterTest {

	@Test
	void givenString_whenConvert_thenLapObject() {
		String text = "SVF2018-05-24_12:02:58.917";
		LocalDate expectedDate = LocalDate.parse("2018-05-24", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		LocalTime expectedTime = LocalTime.parse("12:02:58.917");
		Lap expected = new Lap("SVF", expectedDate, expectedTime);

		Lap actual = LapConverter.convert(text);

		assertEquals(expected, actual);
	}

	@Test
	void givenEmptyString_whenConvert_thenNullObject() {
		String text= "";
		Lap expected = new Lap(null, null, null);
		
		Lap actual = LapConverter.convert(text);
		
		assertEquals(expected, actual);
	}
	
	@Test
	void givenIncorrectData_whenConvert_thenNullObject() {
		String text = "absfd";
		Lap expected = new Lap(null, null, null);
		
		Lap actual = LapConverter.convert(text);
		
		assertEquals(expected, actual);
	}
}
