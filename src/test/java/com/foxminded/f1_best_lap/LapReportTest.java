package com.foxminded.f1_best_lap;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LapReportTest {
	
	private List<Lap> startData;
	private List<Lap> endData;
	private List<Lap> expected;
	private List<Lap> actual;
	
	@BeforeEach
	void setUp() {
		startData = new ArrayList<>();
		endData = new ArrayList<>();
		expected = new ArrayList<>();
	}

	@Test
	void givenCorrectData_whenCreateReport_thenReturnReport() {
		LocalTime timeStart = LocalTime.parse("12:02:58.917");
		LocalTime timeEnd = LocalTime.parse("12:04:03.332");
		LocalDate date = LocalDate.parse("2018-05-24");		
		startData.add(new Lap("SVF", date, timeStart));
		endData.add(new Lap("SVF", date, timeEnd));
		
		Duration duration = Duration.between(timeStart, timeEnd);
		LocalTime timeSpend = LocalTime.ofSecondOfDay(duration.getSeconds()).plusNanos(duration.getNano());		
		expected.add(new Lap("SVF", date, timeSpend));
		
		actual = LapReport.createReport(startData, endData);
		
		assertEquals(expected, actual);
	}

	@Test
	void givenDifferentDates_whenCreateReport_thenEmptyReport() {
		LocalTime timeStart = LocalTime.parse("12:02:58.917");
		LocalTime timeEnd = LocalTime.parse("12:04:03.332");
		LocalDate dateSart = LocalDate.parse("2018-05-24");
		LocalDate dateEnd = LocalDate.parse("2018-05-23");
		startData.add(new Lap("SVF", dateSart, timeStart));
		endData.add(new Lap("SVF", dateEnd, timeEnd));
		
		actual = LapReport.createReport(startData, endData);

		assertEquals(expected, actual);
	}
	
	@Test
	void givenDifferentTeams_whenCreateReport_thenEmptyReport() {
		LocalTime timeStart = LocalTime.parse("12:02:58.917");
		LocalTime timeEnd = LocalTime.parse("12:04:03.332");
		LocalDate date = LocalDate.parse("2018-05-24");		
		startData.add(new Lap("SVF", date, timeStart));
		endData.add(new Lap("FAM", date, timeEnd));
		
		actual = LapReport.createReport(startData, endData);
		
		assertEquals(expected, actual);
	}
}
