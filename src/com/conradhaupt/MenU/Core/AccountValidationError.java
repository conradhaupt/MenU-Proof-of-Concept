package com.conradhaupt.MenU.Core;

import java.util.List;

public class AccountValidationError
{
	// Object variables
	private int header = 0;
	private List<Integer> error;

	// Error codes
	public static final int HEADER_NO_ERRORS = 0;
	public static final int HEADER_ERRORS = 1;
	public static final int USERNAME_INVALID_CHARACTERS = 0;
	public static final int USERNAME_INVALID_LENGTH = 1;
	public static final int PASSWORD_INVALID_LENGTH = 3;
	public static final int EMAIL_INVALID_NOT_EMAIL = 4;
	public static final int FIRSTNAME_INVALID_LENGTH = 5;
	public static final int LASTNAME_INVALID_LENGTH = 6;

	public AccountValidationError()
	{

	}

	public boolean addError(int errorCode)
	{
		if (!error.contains(errorCode))
		{
			error.add(errorCode);
		} else
		{
			updateHeader();
			return false;
		}
		updateHeader();
		return true;
	}

	public boolean removeError(int errorCode)
	{
		if (error.contains(errorCode))
		{
			while (error.contains(errorCode))
			{
				error.remove(error.lastIndexOf(errorCode));
			}
		} else
		{
			updateHeader();
			return false;
		}
		updateHeader();
		return true;
	}

	private void updateHeader()
	{
		if (error.size() > 0)
		{
			header = AccountValidationError.HEADER_ERRORS;
		} else
		{
			header = AccountValidationError.HEADER_NO_ERRORS;
		}
	}

	public List<Integer> getErrors()
	{
		return error;
	}

	public int getHeader()
	{
		return header;
	}

	public boolean hasErrors()
	{
		return header == AccountValidationError.HEADER_ERRORS ? true : false;
	}
}
