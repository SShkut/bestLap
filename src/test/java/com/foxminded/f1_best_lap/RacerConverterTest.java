package com.foxminded.f1_best_lap;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RacerConverterTest {
	
	private static final DateTimeFormatter PATTERN = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss.SSS");
	
	private RacerConverter racerConverter;
	private Map<String, LocalDateTime> startData;
	private Map<String, LocalDateTime> endData;

	@BeforeEach
	void setUp() throws Exception {
		racerConverter = new RacerConverter();
		startData = new HashMap<>();
		endData = new HashMap<>();
	}

	@Test
	void givenRacerDataStringAndTimes_whenConvert_thenReturnRacer() {
		String text = "DRR_Daniel Ricciardo_RED BULL RACING TAG HEUER";
		LocalDateTime startTime = LocalDateTime.parse("2018-05-24_12:14:12.054", PATTERN);
		LocalDateTime endTime = LocalDateTime.parse("2018-05-24_12:15:24.067", PATTERN);
		startData.put("DRR", startTime);
		endData.put("DRR", endTime);
		Duration duration = Duration.between(startTime, endTime);		
		Racer expected = new Racer("DRR", "Daniel Ricciardo", "RED BULL RACING TAG HEUER", duration);
		
		Racer actual = racerConverter.convert(text, startData, endData);
		
		assertEquals(expected, actual);
	}

	@Test
	void givenRacerEmptyString_whenConvert_thenReturnNull() {
		String text = "";		
		LocalDateTime startTime = LocalDateTime.parse("2018-05-24_12:14:12.054", PATTERN);
		LocalDateTime endTime = LocalDateTime.parse("2018-05-24_12:15:24.067", PATTERN);
		startData.put("DRR", startTime);
		endData.put("DRR", endTime);		
		Racer expected = null;
		
		Racer actual = racerConverter.convert(text, startData, endData);
		
		assertEquals(expected, actual);
	}
	
	@Test
	void givenEmptyTime_whenConvert_thenReturnNull() {
		String text = "DRR_Daniel Ricciardo_RED BULL RACING TAG HEUER";		
		Racer expected = null;
		
		Racer actual = racerConverter.convert(text, startData, endData);
		
		assertEquals(expected, actual);
	}
}
