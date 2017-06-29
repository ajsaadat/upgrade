package com.upgrade.operation.validator;

import com.upgrade.bean.User;
import com.upgrade.exception.InvalidUserException;

public interface IUserValidator {

	public boolean validate(User user) throws InvalidUserException; 
}
