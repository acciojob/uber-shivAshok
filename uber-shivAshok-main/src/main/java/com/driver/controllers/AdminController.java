package com.driver.controllers;

import com.driver.Exceptions.AdminNotpresent;
import com.driver.model.Admin;
import com.driver.model.Customer;
import com.driver.model.Driver;
import com.driver.services.AdminService;
import com.driver.services.impl.AdminServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
	AdminServiceImpl adminService;
	@PostMapping("/register")
	public ResponseEntity<Void> registerAdmin(@RequestBody Admin admin){
        adminService.adminRegister(admin);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PutMapping("/update")
	public ResponseEntity<Admin> updateAdminPassword(@RequestParam Integer adminId, @RequestParam String password){
		try {
			Admin updatedAdmin = adminService.updatePassword(adminId, password);
			return new ResponseEntity<>(updatedAdmin, HttpStatus.OK);
		}
		catch(AdminNotpresent ex){
			return new ResponseEntity<>(new Admin(),HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/delete")
	public void deleteAdmin(@RequestParam Integer adminId){
		try {
			adminService.deleteAdmin(adminId);
		}
		catch(AdminNotpresent ex){
			System.out.println("Admin not present");
		}
	}

	@GetMapping("/listOfCustomers")
	public List<Customer> listOfCustomers() {
		List<Customer> listOfCustomers=adminService.getListOfCustomers();
		return listOfCustomers;
	}

	@GetMapping("/listOfDrivers")
	public List<Driver> listOfDrivers() {
		List<Driver> listOfDrivers=adminService.getListOfDrivers();
		return listOfDrivers;
	}
}