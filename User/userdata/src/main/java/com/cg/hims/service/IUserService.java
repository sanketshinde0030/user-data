package com.cg.hims.service;

import com.cg.hims.entities.UserTable;
import com.cg.hims.exceptions.UserNotFoundException;

public interface IUserService {
	public UserTable addNewUser(UserTable user);

	public UserTable signIn(String userName,String password)throws UserNotFoundException;
}
