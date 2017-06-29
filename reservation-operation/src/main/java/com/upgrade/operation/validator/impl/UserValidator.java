package com.upgrade.operation.validator.impl;

import com.upgrade.bean.User;
import com.upgrade.exception.InvalidUserException;
import com.upgrade.operation.validator.IUserValidator;

public class UserValidator implements IUserValidator {

	public boolean validate(User user) throws InvalidUserException {
		return true;
	}

}
