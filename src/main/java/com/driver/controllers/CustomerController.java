package com.driver.controllers;

import com.driver.Exceptions.CustomerNotPresent;
import com.driver.Exceptions.DriverNotAvailable;
import com.driver.Exceptions.TripNotExist;
import com.driver.model.Customer;
import com.driver.model.TripBooking;
import com.driver.services.CustomerService;
import com.driver.services.impl.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	@Autowired
	CustomerServiceImpl customerService;
	@PostMapping("/register")
	public ResponseEntity<Void> registerCustomer(@RequestBody Customer customer){
		customerService.register(customer);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@DeleteMapping("/delete")
	public void deleteCustomer(@RequestParam Integer customerId){
		try {
			customerService.deleteCustomer(customerId);
		}
		catch(CustomerNotPresent ex){
			System.out.println("Not present");
		}
	}

	@PostMapping("/bookTrip")
	public ResponseEntity<Integer> bookTrip(@RequestParam Integer customerId, @RequestParam String fromLocation, @RequestParam String toLocation, @RequestParam Integer distanceInKm) throws Exception {
		try {
			TripBooking bookedTrip = customerService.bookTrip(customerId, fromLocation, toLocation, distanceInKm);
			return new ResponseEntity<>(bookedTrip.getId(), HttpStatus.CREATED);
		}
		catch(DriverNotAvailable ex){
			return new ResponseEntity<>(0,HttpStatus.NOT_FOUND);
		}
		catch(CustomerNotPresent ex){
			return new ResponseEntity<>(0,HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/complete")
	public void completeTrip(@RequestParam Integer tripId){
		try {
			customerService.completeTrip(tripId);
		}
		catch(TripNotExist ex){
			System.out.println(" trip doesnt exist");
		}
	}

	@DeleteMapping("/cancelTrip")
	public void cancelTrip(@RequestParam Integer tripId){
		try{
			customerService.cancelTrip(tripId);
		}
		catch(TripNotExist ex){
			System.out.println(" trip doesnt exist");
		}
	}
}