package com.mk.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mk.dto.ActivateAccount;
import com.mk.dto.LoginApp;
import com.mk.dto.UserData;
import com.mk.service.IUserService;

@RestController
public class UserMangeController {

	@Autowired
	private IUserService service;

	@PostMapping("/register")
	public ResponseEntity<String> registerUser(@RequestBody UserData user) {
		String response = service.registerUser(user);
		if (response.equals("register Successfull")) {
			return new ResponseEntity<String>(response, HttpStatus.CREATED);
		} //if
		else if (response.equals("User Already Registerd")) {
			return new ResponseEntity<String>(response, HttpStatus.IM_USED);
		} //else if
		else {
			return new ResponseEntity<String>(response, HttpStatus.BAD_REQUEST);
		} //else
	}//registerUser

	@PostMapping("/activate")
	public ResponseEntity<String> activateAccount(@RequestBody ActivateAccount activateAccount) {
		String response = service.activateAccount(activateAccount);
		if (response.equals("Account Activated")) {
			return new ResponseEntity<String>(response, HttpStatus.OK);
		} //if
		else if (response.equals("Invalid Credentials")) {
			return new ResponseEntity<String>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		} //else if
		else {
			return new ResponseEntity<String>(response, HttpStatus.BAD_REQUEST);
		} //else
	}//activateAccount()

	@PostMapping("/login")
	public ResponseEntity<String> loginUser(@RequestBody LoginApp login) {
		String loginUser = service.loginUser(login);
		if (loginUser.equals("")) {
			return new ResponseEntity<String>("User", HttpStatus.OK);
		} //if
		else {
			return new ResponseEntity<String>("", HttpStatus.NOT_FOUND);
		} //else
	}//login

	@GetMapping("/allUsers")
	public ResponseEntity<List<UserData>> getAllUsers() {
		List<UserData> allUsers = service.getAllUsers();
		if (!allUsers.isEmpty()) {
			return new ResponseEntity<List<UserData>>(allUsers, HttpStatus.OK);
		} //if
		else {
			return new ResponseEntity<List<UserData>>(allUsers, HttpStatus.NO_CONTENT);
		} //else
	}//getAllUsers

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable Integer id) {
		Boolean delete = service.deleteUserById(id);

		if (delete) {
			return new ResponseEntity<String>("User Deleted Successfully", HttpStatus.GONE);
		} //if
		else {
			return new ResponseEntity<String>("User Not Deleted", HttpStatus.BAD_REQUEST);
		} //else
	}//deleteUser()

	@PutMapping("/update")
	public ResponseEntity<String> updateUserInfo(@RequestBody UserData userData) {
		Boolean updateUser = service.updateUser(userData);
		if (updateUser) {
			return new ResponseEntity<String>("User Updated Successfully", HttpStatus.OK);
		} //if
		else {
			return new ResponseEntity<String>("User Not Updated", HttpStatus.NOT_MODIFIED);
		} //else
	}//updateUser()

	@GetMapping("/forgot/{email}")
	public ResponseEntity<String> forgotPassword(@PathVariable String email) {
		String response = service.forgotPswd(email);
		if (response.equals("Password Sent to Your Email Id")) {
			return new ResponseEntity<String>(response, HttpStatus.OK);
		} //if
		else {
			return new ResponseEntity<String>(response, HttpStatus.NOT_FOUND);
		} //else
	}//fogotPassword()

	@PatchMapping("/status/{id}/{status}")
	public ResponseEntity<String> changeStatus(@PathVariable Integer id, @PathVariable String status) {
		Boolean changeStatus = service.changeStatus(id, status);
		if (changeStatus) {
			return new ResponseEntity<String>(status, HttpStatus.OK);
		} //if
		else {
			return new ResponseEntity<String>(status, HttpStatus.NOT_MODIFIED);
		} //else
	}//chageStatus()

}//class