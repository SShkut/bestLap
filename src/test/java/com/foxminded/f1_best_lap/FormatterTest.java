package com.foxminded.f1_best_lap;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FormatterTest {
	
	private static final String NEWLINE = System.lineSeparator();
	private static final DateTimeFormatter PATTERN = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss.SSS");
	
	private Formatter formatter;
	private Racer racer;
	private LocalTime timeSpent;

	@BeforeEach
	void setUp() throws Exception {
		formatter = new Formatter();
		LocalDateTime startTime = LocalDateTime.parse("2018-05-24_12:14:12.054", PATTERN);
		LocalDateTime endTime = LocalDateTime.parse("2018-05-24_12:15:24.067", PATTERN);	
		Duration duration = Duration.between(startTime, endTime);
		timeSpent = LocalTime.ofSecondOfDay(duration.getSeconds()).plusNanos(duration.getNano());
		racer = new Racer("DRR", "Daniel Ricciardo", "RED BULL RACING TAG HEUER", duration);
	}

	@Test
	void givenDriverAndIndex_whenFormat_thenReturnFormattedString() {
		int index = 1;

		String expected = String.format("%-25s%-30s%-15s%n", String.valueOf(index) + ". " + racer.getName(), "|" + racer.getTeam(), "|" + timeSpent);
		String actual = formatter.format(racer, index);
		
		assertEquals(expected, actual);
	}

	@Test
	void givenLastQualifiedDriver_whenFormat_thenReturnFormattedStringWithDelimiter() {
		int index = 15;
		
		String expected = String.format("%-25s%-30s%-15s%n", String.valueOf(index) + ". " + racer.getName(), "|" + racer.getTeam(), "|" + timeSpent)
				+ String.format("%s%n", "------------------------------------------------------------------------");
		String actual = formatter.format(racer, index);
		
		assertEquals(expected, actual);
	}
	
	@Test
	void givenNullDriver_whenFormat_thenReturnEmptyString() {
		int index = 1;
		
		String expected = "";		
		String actual = formatter.format(null, index);
		
		assertEquals(expected, actual);
	}
	
	@Test
	void givenLessThanZeroIndex_whenFormat_thenEmptyString() {
		int index = -1;
		
		String expected = "";
		String actual = formatter.format(racer, index);
		
		assertEquals(expected, actual);
	}
}
