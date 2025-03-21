package com.mk.service;

import java.util.List;

import com.mk.dto.ActivateAccount;
import com.mk.dto.LoginApp;
import com.mk.dto.UserData;

public interface IUserService {

	String registerUser(UserData user);

	String activateAccount(ActivateAccount activateAccount);

	String loginUser(LoginApp login);

	boolean changeStatus(Integer id, String status);

	//	UserManagement getUserByEmail(String email);

	List<UserData> getAllUsers();

	UserData getUserById(Integer id);

	boolean updateUser(UserData user);

	boolean deleteUserById(Integer id);

	String forgotPswd(String email);
}
