package com.driver.services.impl;

import com.driver.Exceptions.CustomerNotPresent;
import com.driver.Exceptions.DriverNotAvailable;
import com.driver.Exceptions.TripNotExist;
import com.driver.model.TripBooking;
import com.driver.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.driver.model.Customer;
import com.driver.model.Driver;
import com.driver.repository.CustomerRepository;
import com.driver.repository.DriverRepository;
import com.driver.repository.TripBookingRepository;
import com.driver.model.TripStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepository customerRepository2;

	@Autowired
	DriverRepository driverRepository2;

	@Autowired
	TripBookingRepository tripBookingRepository2;

	@Override
	public void register(Customer customer) {
		customerRepository2.save(customer);
	}

	@Override
	public void deleteCustomer(Integer customerId) {
		// Delete customer without using deleteById function
		Optional<Customer> ct=customerRepository2.findById(customerId);
		if(ct.isPresent()==false){
			throw new CustomerNotPresent("customer doesnt exist");
		}
		Customer customer=ct.get();
		List<TripBooking> bookings=customer.getCustomerTrips();
		for(TripBooking tp:bookings){
			tripBookingRepository2.delete(tp);
		}
		customerRepository2.delete(customer);
	}

	@Override
	public TripBooking bookTrip(int customerId, String fromLocation, String toLocation, int distanceInKm) throws Exception{
		//Book the driver with lowest driverId who is free (cab available variable is Boolean.TRUE). If no driver is available, throw "No cab available!" exception
		//Avoid using SQL query
		List<Driver> drivers=driverRepository2.findAll();
		Driver mdriver=new Driver();
		boolean check=false;
		for(Driver driver:drivers){
			if(driver.getCab().isAvailable()){
				mdriver=driver;
				check=true;
				break;
			}
		}
		if(check==false){
			throw new DriverNotAvailable("No cab available!");
		}
		Optional<Customer> ct=customerRepository2.findById(customerId);
		if(ct.isPresent()==false){
			throw new CustomerNotPresent("customer doesnt exist");
		}
		Customer customer=ct.get();

		TripBooking tripBooking=new TripBooking();
		tripBooking.setFromLocation(fromLocation);
		tripBooking.setToLocation(toLocation);
		tripBooking.setDistanceInkm(distanceInKm);
		tripBooking.setCustomer(customer);
		tripBooking.setDriver(mdriver);
		tripBooking.setStatus(TripStatus.CONFIRMED);

		int bill=mdriver.getCab().getPerKmRate()*distanceInKm;
		tripBooking.setBill(bill);

		customer.getCustomerTrips().add(tripBooking);

		mdriver.getTripList().add(tripBooking);
		mdriver.getCab().setAvailable(false);

		tripBookingRepository2.save(tripBooking);
		customerRepository2.save(customer);
		Driver saved=driverRepository2.save(mdriver);
		TripBooking savedTp=saved.getTripList().get(saved.getTripList().size()-1);
		//savedTp
		return savedTp;
	}

	@Override
	public void cancelTrip(Integer tripId){
		//Cancel the trip having given trip Id and update TripBooking attributes accordingly
		Optional<TripBooking> tp=tripBookingRepository2.findById(tripId);
		if(tp.isPresent()==false){
			throw new TripNotExist("trip doesnt exists");
		}
		TripBooking tripBooking=tp.get();
		Driver dv=tripBooking.getDriver();
		dv.getCab().setAvailable(true);
		tripBooking.setStatus(TripStatus.CANCELED);

		driverRepository2.save(dv);
	}

	@Override
	public void completeTrip(Integer tripId){
		//Complete the trip having given trip Id and update TripBooking attributes accordingly
		Optional<TripBooking> tp=tripBookingRepository2.findById(tripId);
		if(tp.isPresent()==false){
			throw new TripNotExist("trip doesnt exists");
		}
		TripBooking tripBooking=tp.get();
		Driver dv=tripBooking.getDriver();
		dv.getCab().setAvailable(true);
		tripBooking.setStatus(TripStatus.COMPLETED);

		driverRepository2.save(dv);
	}
}