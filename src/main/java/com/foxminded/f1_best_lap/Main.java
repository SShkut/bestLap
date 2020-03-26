package com.foxminded.f1_best_lap;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		DriverRepository driverRepository = new DriverRepository(new DriverService(new QualifyingDataParser(), new DriverDataParser()));
		List<Driver> drivers = driverRepository.getDrivers();
		print(drivers);
	}
	
	private static void print(List<Driver> drivers) {
		char[] offset = new char[20];
		Arrays.fill(offset, ' ');
		
		Collections.sort(drivers, (d1, d2) -> d1.getBestLapTime().compareTo(d2.getBestLapTime()));
		int chartNumber = 0;
		for (Driver driver : drivers) {
			if (chartNumber == 15) {
				System.out.println("------------------------------------------------------------------------");
			}
			LocalTime timeSpent = LocalTime.ofSecondOfDay(driver.getBestLapTime().getSeconds()).plusNanos(driver.getBestLapTime().getNano());
			System.out.format("%-25s%-30s%-15s%n", String.valueOf(++chartNumber) + ". " + driver.getName(), "|" + driver.getTeam(), "|" + timeSpent);
		}
	}
}
