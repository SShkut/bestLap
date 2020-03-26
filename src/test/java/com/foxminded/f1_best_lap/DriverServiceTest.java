package com.foxminded.f1_best_lap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

class DriverServiceTest {
	
	private static final DateTimeFormatter PATTERN = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss.SSS");
	
	private Map<String, LocalDateTime> startData;
	private Map<String, LocalDateTime> endData;
	private Map<String, String> driversData;
	private List<Driver> expected;	
	
	@InjectMocks
	private DriverService driverService;
	
	@Mock
	private DriverDataParser driverDataParser;
	
	@Mock
	private QualifyingDataParser qualifyingDataParser;

	@BeforeEach
	void setUp() throws Exception {
		initMocks(this);
		startData = new HashMap<>();
		endData = new HashMap<>();
		driversData = new HashMap<>();
		expected = new ArrayList<>();
	}

	@Test
	void givenCorrectData_whenGetDrivers_thenReturnDrivers() {
		startData.put("SVF", LocalDateTime.parse("2018-05-24_12:02:58.917", PATTERN));
		startData.put("NHR", LocalDateTime.parse("2018-05-24_12:02:49.914", PATTERN));
		when(qualifyingDataParser.parse("start.log")).thenReturn(startData);
		
		endData.put("SVF", LocalDateTime.parse("2018-05-24_12:04:03.332", PATTERN));
		endData.put("NHR", LocalDateTime.parse("2018-05-24_12:04:02.979", PATTERN));
		when(qualifyingDataParser.parse("end.log")).thenReturn(endData);
		
		driversData.put("SVF", "Sebastian Vettel_FERRARI");
		driversData.put("NHR", "Nico Hulkenberg_RENAULT");
		when(driverDataParser.parse("abbreviations.txt")).thenReturn(driversData);
		
		expected.add(new Driver("Sebastian Vettel", "FERRARI", Duration.between(LocalDateTime.parse("2018-05-24_12:02:58.917", PATTERN), LocalDateTime.parse("2018-05-24_12:04:03.332", PATTERN))));
		expected.add(new Driver("Nico Hulkenberg", "RENAULT", Duration.between(LocalDateTime.parse("2018-05-24_12:02:49.914", PATTERN), LocalDateTime.parse("2018-05-24_12:04:02.979", PATTERN))));
		
		assertEquals(expected, driverService.getDrivers());
	}
	
	@Test
	void givenEmptyDriverData_whenGetDrivers_thenEmptyDrivers() {
		assertEquals(expected, driverService.getDrivers());
	}
	
	@Test
	void givenEmptyQualifyingData_whenGetDrivers_thenEmptyDrivers() {
		assertEquals(expected, driverService.getDrivers());
	}
}
