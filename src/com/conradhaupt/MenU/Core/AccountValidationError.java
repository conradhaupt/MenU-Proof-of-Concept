package com.conradhaupt.MenU.Core;

public class AccountValidationError
{
	// Object variables
	private int header = 0;
	private int[] error = new int[0];
	{
	};

	// Error codes
	public static final int HEADER_NO_ERRORS = 0;
	public static final int HEADER_ERRORS = 1;
	public static final int USERNAME_INVALID_CHARACTERS = 0;
	public static final int USERNAME_INVALID_LENGTH = 1;
	public static final int PASSWORD_INVALID_LENGTH = 2;
	public static final int EMAIL_INVALID_NOT_EMAIL = 3;
	public static final int FIRSTNAME_INVALID_LENGTH = 4;
	public static final int LASTNAME_INVALID_LENGTH = 5;

	public AccountValidationError()
	{

	}

	public boolean addError(int errorCode)
	{
		if (!this.containsError(errorCode))
		{
			int[] temp = new int[error.length + 1];
			for (int i = 0; i < error.length; i++)
			{
				temp[i] = error[i];
			}
			temp[temp.length - 1] = errorCode;
			error = temp;
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
		if (this.containsError(errorCode))
		{
			int[] temp = new int[error.length - 1];
			int position = 0;
			for (int i = 0; i < error.length; i++)
			{
				if (error[i] != errorCode)
				{
					temp[position] = error[i];
					position++;
				}
			}
			error = temp;
			updateHeader();
		} else
		{
			return false;
		}
		return true;
	}

	private void updateHeader()
	{
		if (error.length > 0)
		{
			header = AccountValidationError.HEADER_ERRORS;
		} else
		{
			header = AccountValidationError.HEADER_NO_ERRORS;
		}
	}

	public int[] getErrors()
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

	public boolean containsError(int errorCode)
	{
		for (int i = 0; i < error.length; i++)
		{
			if (error[i] == errorCode)
			{
				return true;
			}
		}
		return false;
	}
}
