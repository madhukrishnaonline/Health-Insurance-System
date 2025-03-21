package com.mk.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.mk.dto.ActivateAccount;
import com.mk.dto.LoginApp;
import com.mk.dto.UserData;
import com.mk.entity.UserManagement;
import com.mk.mail.EmailUtils;
import com.mk.repository.UserManageRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserManageRepository repository;

	@Autowired
	private EmailUtils emailUtils;

	private Random random = new Random();

	@Override
	public String registerUser(UserData user) {
		Optional<UserManagement> email = repository.findByEmail(user.getEmail());
		if (email.isEmpty()) {
			UserManagement userInfo = new UserManagement();

			BeanUtils.copyProperties(user, userInfo);
			userInfo.setPassword(randomPswd());
			userInfo.setStatus("INACTIVE");
			UserManagement data = repository.save(userInfo);

			String fileName = "REG-MAIL-BODY.txt";
			String sub = "Registration Successfull";
			String mailBody = sendMailBody(userInfo.getName(), userInfo.getPassword(), fileName);
			emailUtils.sendMail(userInfo.getEmail(), sub, mailBody);

			if (data.getId() == null) {
				return "register failed";
			} //if
			return "Registration Successfull";
		} //if
		else {
			return "User Already Registerd";
		} //else
	}//registerUser

	@Override
	public String activateAccount(ActivateAccount activateAccount) {
		UserManagement userData = new UserManagement();
		userData.setEmail(activateAccount.getEmail());
		userData.setPassword(activateAccount.getTempPswd());

		Example<UserManagement> example = Example.of(userData);
		List<UserManagement> all = repository.findAll(example);
		if (!all.isEmpty()) {
			UserManagement user = all.get(0);
			if (activateAccount.getNewPswd().equals(activateAccount.getCnfPswd())) {
				user.setPassword(activateAccount.getNewPswd());
				user.setStatus("ACTIVE");
				repository.save(user);
				return "Account Activated";
			} //if
			else {
				return "Please Match Password with new Password";
			} //else
		} //if
		return "Invalid Credentials";
	}//activateAccount

	@Override
	public String loginUser(LoginApp login) {
		UserManagement data = repository.findByEmailAndPassword(login.getEmail(), login.getPassword());
		if (data != null) {
			if (data.getStatus().equals("ACTIVE")) {
				return "Login Successfull";
			}
			return "Login Failed";
		} //if
		else {
			return "Invalid Credentials";
		} //else
	}//loginUser

	@Override
	public List<UserData> getAllUsers() {
		List<UserData> userData = new ArrayList<>();
		List<UserManagement> findAll = repository.findAll();
		for (UserManagement entity : findAll) {
			UserData user = new UserData();
			BeanUtils.copyProperties(entity, user);
			userData.add(user);
		} //for
		return userData;
	}//getAllUsers

	@Override
	public boolean changeStatus(Integer id, String status) {
		Optional<UserManagement> byId = repository.findById(id);
		if (!byId.isEmpty()) {
			UserManagement user = byId.get();
			user.setStatus(status);
			repository.save(user);
			return true;
		} //if
		return false;
	}//changeStatus()

	@Override
	public UserData getUserById(Integer id) {
		Optional<UserManagement> id2 = repository.findById(id);
		UserData userData = new UserData();
		if (id2.isPresent()) {
			UserManagement user = id2.get();
			BeanUtils.copyProperties(user, userData);
		} //if
		else {
			UserManagement orElseThrow = id2.orElseThrow(() -> new IllegalArgumentException("User Not Found"));
			BeanUtils.copyProperties(orElseThrow, userData);
		} //else
		return userData;
	}//getUserById

	@Override
	public boolean updateUser(UserData user) {
		UserManagement data = new UserManagement();
		BeanUtils.copyProperties(user, data);
		repository.save(data);
		return true;
	}//updateUser

	@Override
	public boolean deleteUserById(Integer id) {
		repository.deleteById(id);
		return true;
	}

	@Override
	public String forgotPswd(String email) {
		Optional<UserManagement> email2 = repository.findByEmail(email);
		if (!email2.isEmpty()) {
			UserManagement user = email2.get();

			String fileName = "RECOVER_PWD_BODY.txt";
			String sub = "Forgot Password";
			String sendMail = sendMailBody(user.getName(), user.getPassword(), fileName);
			emailUtils.sendMail(user.getEmail(), sub, sendMail);
			return "Password Sent to Your Email Id";
		} //if
		else {
			return "No User found with given Email Id";
		} //else
	}//forgotPswd

	private String randomPswd() {
		String upperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String lowerCase = "abcdefghijklmnopqrstuvwxyz";
		String numeric = "0123456789";
		String alphaNumeric = upperCase + lowerCase + numeric;
		StringBuilder builder = new StringBuilder();
		int len = 6;
		for (int i = 0; i < len; i++) {
			int index = random.nextInt(alphaNumeric.length());
			char charAt = alphaNumeric.charAt(index);
			builder.append(charAt);
		} //for
		return builder.toString();
	}//randomPWd

	private String sendMailBody(String fullName, String pwd, String fileName) {
		final String url = "http://localhost:3030/activate";
		String mailBody = null;
		try (FileReader reader = new FileReader(fileName);
				BufferedReader bufferedReader = new BufferedReader(reader);) {
			StringBuilder builder = new StringBuilder();
			String line = bufferedReader.readLine();
			while (line != null) {
				builder.append(line);
				line = bufferedReader.readLine();
			} //while
			mailBody = builder.toString();
			mailBody = mailBody.replace("{FULLNAME}", fullName);
			mailBody = mailBody.replace("{TEMP-PWD}", pwd);
			mailBody = mailBody.replace("{URL}", url);
			mailBody = mailBody.replace("{PWD}", pwd);
		} //try
		catch (Exception e) {
			log.error("Exception Occured " + e.getMessage());
		} //catch
		return mailBody;
	}//sendMail

}//class