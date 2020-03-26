package com.foxminded.f1_best_lap;

import java.util.List;

public class DriverRepository {
	
	private final DriverService driverService;
	
	public DriverRepository(DriverService driverService) {
		this.driverService = driverService;
	}

	public List<Driver> getDrivers() {
		return driverService.getDrivers();
	}
}
