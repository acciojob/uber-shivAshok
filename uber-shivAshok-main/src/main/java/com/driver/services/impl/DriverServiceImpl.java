package com.driver.services.impl;

import com.driver.Exceptions.DriverNotAvailable;
import com.driver.model.Cab;
import com.driver.model.TripBooking;
import com.driver.repository.CabRepository;
import com.driver.repository.TripBookingRepository;
import com.driver.services.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.driver.model.Driver;
import com.driver.repository.DriverRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DriverServiceImpl implements DriverService {

	@Autowired
	DriverRepository driverRepository3;

	@Autowired
	CabRepository cabRepository3;

	@Autowired
	TripBookingRepository tripBookingRepository;
	@Override
	public void register(String mobile, String password){
		//Save a driver in the database having given details and a cab with ratePerKm as 10 and availability as True by default.
		Driver driver=new Driver();
		driver.setMobile(mobile);
		driver.setPassword(password);

		driverRepository3.save(driver);
	}

	@Override
	public void removeDriver(int driverId){
		// Delete driver without using deleteById function
		Optional<Driver> driver=driverRepository3.findById(driverId);
		if(driver.isPresent()==false){
			throw new DriverNotAvailable("driver doesnt exist");
		}
       Driver dv=driver.get();
		List<TripBooking> bookings=dv.getTripList();
		for(TripBooking tp:bookings){
			tripBookingRepository.delete(tp);
		}
		cabRepository3.delete(dv.getCab());
		driverRepository3.delete(dv);

	}

	@Override
	public void updateStatus(int driverId){
		//Set the status of respective car to unavailable
		Optional<Driver> driver=driverRepository3.findById(driverId);
		if(driver.isPresent()==false){
			throw new DriverNotAvailable("driver doesnt exist");
		}
		Driver dv=driver.get();
		dv.getCab().setAvailable(false);
		driverRepository3.save(dv);
	}
}
