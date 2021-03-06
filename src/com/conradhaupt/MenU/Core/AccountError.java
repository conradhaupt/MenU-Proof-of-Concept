package com.conradhaupt.MenU.Core;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;

import com.conradhaupt.MenU.R;

public class AccountError
{
	// Error states
	public static final int STATE_ACCEPTABLE = 0;
	public static final int STATE_UNACCEPTABLE = 1;

	// Account information error codes
	public static final int USERNAME_NOT_AVAILABLE = 0;
	public static final int USERNAME_INVALID_CHARACTERS = 1;
	public static final int USERNAME_INVALID_LENGTH = 2;
	public static final int PASSWORD_INVALID_LENGTH = 3;
	public static final int PASSWORD_INVALID_CHARACTERS = 4;
	public static final int FIRSTNAME_INVALID_CHARACTERS = 5;
	public static final int FIRSTNAME_INVALID_LENGTH = 6;
	public static final int LASTNAME_INVALID_CHARACTERS = 7;
	public static final int LASTNAME_INVALID_LENGTH = 8;
	public static final int EMAIL_INVALID_CHARACTERS = 9;
	public static final int EMAIL_INVALID_LENGTH = 10;
	public static final int EMAIL_NOT_AVAILABLE = 11;

	// Account validation error codes
	public static final int ACCOUNT_DOESNT_EXIST = 20;
	public static final int PASSWORD_NOT_CORRECT = 21;
	public static final int ACCOUNT_SUSPENDED = 22;
	public static final int ACCOUNT_UNKNOWN_LOGIN_ERROR = 23;

	// Connection or back end errors
	public static final int CONNECTION_TIME_OUT = 40;
	public static final int CONNECTION_NOT_AVAILABLE = 41;
	public static final int CONNECTION_NOT_AVAILABLE_UNKNOWN = 42;

	// Account information error keywords
	public static final String USERNAME_NOT_AVAILABLE_KEYWORD = "Username_UNIQUE";
	public static final String USERNAME_INVALID_CHARACTERS_KEYWORD = "";
	public static final String USERNAME_INVALID_LENGTH_KEYWORD = "";
	public static final String PASSWORD_INVALID_LENGTH_KEYWORD = "";
	public static final String PASSWORD_INVALID_CHARACTERS_KEYWORD = "";
	public static final String FIRSTNAME_INVALID_CHARACTERS_KEYWORD = "";
	public static final String FIRSTNAME_INVALID_LENGTH_KEYWORD = "";
	public static final String LASTNAME_INVALID_CHARACTERS_KEYWORD = "";
	public static final String LASTNAME_INVALID_LENGTH_KEYWORD = "";
	public static final String EMAIL_INVALID_CHARACTERS_KEYWORD = "";
	public static final String EMAIL_INVALID_LENGTH_KEYWORD = "";
	public static final String EMAIL_NOT_AVAILABLE_KEYWORD = "Email_UNIQUE";

	// Account validation error keywords
	public static final String ACCOUNT_DOESNT_EXIST_KEYWORD = "That account doesn't exist";
	public static final String PASSWORD_NOT_CORRECT_KEYWORD = "That password is incorrect";
	public static final String ACCOUNT_SUSPENDED_KEYWORD = "That account has been suspended";
	public static final String ACCOUNT_UNKNOWN_LOGIN_ERROR_KEYWORD = "";

	// Connection or back end error keywords
	public static final String CONNECTION_TIME_OUT_KEYWORD = "";
	public static final String CONNECTION_NOT_AVAILABLE_KEYWORD = "UnknownHostException";
	public static final String CONNECTION_NOT_AVAILABLE_UNKNOWN_KEYWORD = "";

	// Account information error messages
	public static final int USERNAME_NOT_AVAILABLE_MESSAGE = R.string.account_verification_username_not_available;
	public static final int USERNAME_INVALID_CHARACTERS_MESSAGE = R.string.account_verification_username_invalid_characters;
	public static final int USERNAME_INVALID_LENGTH_MESSAGE = R.string.account_verification_username_invalid_length;
	public static final int PASSWORD_INVALID_LENGTH_MESSAGE = R.string.account_verification_password_invalid_length;
	public static final int PASSWORD_INVALID_CHARACTERS_MESSAGE = R.string.account_verification_password_invalid_characters;
	public static final int FIRSTNAME_INVALID_CHARACTERS_MESSAGE = R.string.account_verification_firstname_invalid_characters;
	public static final int FIRSTNAME_INVALID_LENGTH_MESSAGE = R.string.account_verification_firstname_invalid_length;
	public static final int LASTNAME_INVALID_CHARACTERS_MESSAGE = R.string.account_verification_lastname_invalid_characters;
	public static final int LASTNAME_INVALID_LENGTH_MESSAGE = R.string.account_verification_lastname_invalid_length;
	public static final int EMAIL_INVALID_CHARACTERS_MESSAGE = R.string.account_verification_email_invalid_characters;
	public static final int EMAIL_INVALID_LENGTH_MESSAGE = R.string.account_verification_email_invalid_length;
	public static final int EMAIL_NOT_AVAILABLE_MESSAGE = R.string.account_verification_email_not_available;

	// Account validation error message
	public static final int ACCOUNT_DOESNT_EXIST_MESSAGE = R.string.account_verification_account_doesnt_exist;
	public static final int PASSWORD_NOT_CORRECT_MESSAGE = R.string.account_verification_password_not_correct;
	public static final int ACCOUNT_SUSPENDED_MESSAGE = R.string.account_verification_account_suspended;
	public static final int ACCOUNT_UNKNOWN_LOGIN_ERROR_MESSAGE = R.string.account_verification_account_unknown_login_error;

	// Connection or back end error message
	public static final int CONNECTION_TIME_OUT_MESSAGE = R.string.account_verification_connection_time_out;
	public static final int CONNECTION_NOT_AVAILABLE_MESSAGE = R.string.account_verification_connection_not_available;
	public static final int CONNECTION_NOT_AVAILABLE_UNKNOWN_MESSAGE = R.string.account_verification_connection_not_available_unknown;
	// Account limitations
	public static final String USERNAME_CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890()_-";

	public static final int PASSWORD_LENGTH_MIN = 6;
	public static final int USERNAME_LENGTH_MIN = 6;
	public static final int USERNAME_LENGTH_MAX = 20;
	public static final int PASSWORD_LENGTH_MAX = 25;
	public static final int FIRSTNAME_LENGTH_MAX = 50;
	public static final int LASTNAME_LENGTH_MAX = 50;
	public static final int FIRSTNAME_LENGTH_MIN = 1;
	public static final int LASTNAME_LENGTH_MIN = 1;
	public static final int EMAIL_LENGTH_MIN = 5;
	public static final int EMAIL_LENGTH_MAX = 255;
	public static final String EMAIL_CHARACTERS_LOCAL = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890!#$%&'*+-/=?^_`{|}~.";
	public static final String EMAIL_CHARACTERS_DOMAIN = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890-.[]";

	// Object variables
	private List<Integer> errors = new ArrayList<Integer>();
	private int state = STATE_ACCEPTABLE;
	private String accessCode = "NO_ACCESS_CODE";

	public AccountError()
	{
	}

	public String getAccessCode()
	{
		return accessCode;
	}

	public void setAccessCode(String accessCode)
	{
		this.accessCode = accessCode;
	}

	private void updateState()
	{
		if (errors.size() != 0)
		{
			state = STATE_UNACCEPTABLE;
		} else
		{
			state = STATE_ACCEPTABLE;
		}
	}

	public int getState()
	{
		return state;
	}

	public void addError(int errorCode)
	{
		if (!errors.contains(errorCode))
		{
			errors.add(errorCode);
		}
		updateState();
	}

	public void removeError(int errorCode)
	{
		while (errors.contains(errorCode))
		{
			errors.remove(errorCode);
		}
		updateState();
	}

	public List<Integer> getErrors()
	{
		return errors;
	}

	public boolean contains(int errorCode)
	{
		return errors.contains(errorCode);
	}

	public List<String> getErrorMessages(Context context)
	{
		List<String> errorMessages = new ArrayList<String>();

		if (errors.contains(USERNAME_NOT_AVAILABLE))
		{
			errorMessages.add(context.getResources().getString(
					AccountError.USERNAME_NOT_AVAILABLE_MESSAGE));
		}
		if (errors.contains(USERNAME_INVALID_CHARACTERS))
		{
			errorMessages.add(context.getResources().getString(
					AccountError.USERNAME_INVALID_CHARACTERS_MESSAGE));
		}
		if (errors.contains(USERNAME_INVALID_LENGTH))
		{
			errorMessages.add(context.getResources().getString(
					AccountError.USERNAME_INVALID_LENGTH_MESSAGE));
		}
		if (errors.contains(PASSWORD_INVALID_LENGTH))
		{
			errorMessages.add(context.getResources().getString(
					AccountError.PASSWORD_INVALID_LENGTH_MESSAGE));
		}
		if (errors.contains(PASSWORD_INVALID_CHARACTERS))
		{
			errorMessages.add(context.getResources().getString(
					AccountError.PASSWORD_INVALID_CHARACTERS_MESSAGE));
		}
		if (errors.contains(FIRSTNAME_INVALID_CHARACTERS))
		{
			errorMessages.add(context.getResources().getString(
					AccountError.FIRSTNAME_INVALID_CHARACTERS_MESSAGE));
		}
		if (errors.contains(FIRSTNAME_INVALID_LENGTH))
		{
			errorMessages.add(context.getResources().getString(
					AccountError.FIRSTNAME_INVALID_LENGTH_MESSAGE));
		}
		if (errors.contains(LASTNAME_INVALID_CHARACTERS))
		{
			errorMessages.add(context.getResources().getString(
					AccountError.LASTNAME_INVALID_CHARACTERS_MESSAGE));
		}
		if (errors.contains(LASTNAME_INVALID_LENGTH))
		{
			errorMessages.add(context.getResources().getString(
					AccountError.LASTNAME_INVALID_LENGTH_MESSAGE));
		}
		if (errors.contains(EMAIL_INVALID_CHARACTERS))
		{
			errorMessages.add(context.getResources().getString(
					AccountError.EMAIL_INVALID_CHARACTERS_MESSAGE));
		}
		if (errors.contains(EMAIL_INVALID_LENGTH))
		{
			errorMessages.add(context.getResources().getString(
					AccountError.EMAIL_INVALID_LENGTH_MESSAGE));
		}
		if (errors.contains(EMAIL_NOT_AVAILABLE))
		{
			errorMessages.add(context.getResources().getString(
					AccountError.EMAIL_NOT_AVAILABLE_MESSAGE));
		}
		if (errors.contains(CONNECTION_TIME_OUT))
		{
			errorMessages.add(context.getResources().getString(
					AccountError.CONNECTION_TIME_OUT_MESSAGE));
		}
		if (errors.contains(CONNECTION_NOT_AVAILABLE))
		{
			errorMessages.add(context.getResources().getString(
					AccountError.CONNECTION_NOT_AVAILABLE_MESSAGE));
		}
		if (errors.contains(CONNECTION_NOT_AVAILABLE_UNKNOWN))
		{
			errorMessages.add(context.getResources().getString(
					AccountError.CONNECTION_NOT_AVAILABLE_UNKNOWN_MESSAGE));
		}
		if (errors.contains(ACCOUNT_DOESNT_EXIST))
		{
			errorMessages.add(context.getResources().getString(
					AccountError.ACCOUNT_DOESNT_EXIST_MESSAGE));
		}
		if (errors.contains(PASSWORD_NOT_CORRECT))
		{
			errorMessages.add(context.getResources().getString(
					AccountError.PASSWORD_NOT_CORRECT_MESSAGE));
		}
		if (errors.contains(ACCOUNT_SUSPENDED))
		{
			errorMessages.add(context.getResources().getString(
					AccountError.ACCOUNT_SUSPENDED_MESSAGE));
		}
		if (errors.contains(ACCOUNT_UNKNOWN_LOGIN_ERROR_KEYWORD))
		{
			errorMessages.add(context.getResources().getString(
					AccountError.ACCOUNT_UNKNOWN_LOGIN_ERROR_MESSAGE));
		}

		return errorMessages;
	}

	public List<String> getStringErrors()
	{
		List<String> stringErrors = new ArrayList<String>();

		if (errors.contains(USERNAME_NOT_AVAILABLE))
		{
			stringErrors.add("USERNAME_NOT_AVAILABLE");
		}
		if (errors.contains(USERNAME_INVALID_CHARACTERS))
		{
			stringErrors.add("USERNAME_INVALID_CHARACTERS");
		}
		if (errors.contains(USERNAME_INVALID_LENGTH))
		{
			stringErrors.add("USERNAME_INVALID_LENGTH");
		}
		if (errors.contains(PASSWORD_INVALID_LENGTH))
		{
			stringErrors.add("PASSWORD_INVALID_LENGTH");
		}
		if (errors.contains(PASSWORD_INVALID_CHARACTERS))
		{
			stringErrors.add("PASSWORD_INVALID_CHARACTERS");
		}
		if (errors.contains(FIRSTNAME_INVALID_CHARACTERS))
		{
			stringErrors.add("FIRSTNAME_INVALID_CHARACTERS");
		}
		if (errors.contains(FIRSTNAME_INVALID_LENGTH))
		{
			stringErrors.add("FIRSTNAME_INVALID_LENGTH");
		}
		if (errors.contains(LASTNAME_INVALID_CHARACTERS))
		{
			stringErrors.add("LASTNAME_INVALID_CHARACTERS");
		}
		if (errors.contains(LASTNAME_INVALID_LENGTH))
		{
			stringErrors.add("LASTNAME_INVALID_LENGTH");
		}
		if (errors.contains(EMAIL_INVALID_CHARACTERS))
		{
			stringErrors.add("EMAIL_INVALID_CHARACTERS");
		}
		if (errors.contains(EMAIL_INVALID_LENGTH))
		{
			stringErrors.add("EMAIL_INVALID_LENGTH");
		}
		if (errors.contains(EMAIL_NOT_AVAILABLE))
		{
			stringErrors.add("EMAIL_NOT_AVAILABLE");
		}
		if (errors.contains(CONNECTION_TIME_OUT))
		{
			stringErrors.add("CONNECTION_TIME_OUT");
		}
		if (errors.contains(CONNECTION_NOT_AVAILABLE))
		{
			stringErrors.add("CONNECTION_NOT_AVAILABLE");
		}
		if (errors.contains(CONNECTION_NOT_AVAILABLE_UNKNOWN))
		{
			stringErrors.add("CONNECTION_NOT_AVAILABLE_UNKNOWN");
		}
		if (errors.contains(ACCOUNT_DOESNT_EXIST))
		{
			stringErrors.add("ACCOUNT_DOESNT_EXIST");
		}
		if (errors.contains(PASSWORD_NOT_CORRECT))
		{
			stringErrors.add("PASSWORD_NOT_CORRECT");
		}
		if (errors.contains(ACCOUNT_SUSPENDED))
		{
			stringErrors.add("ACCOUNT_SUSPENDED");
		}
		if (errors.contains(ACCOUNT_UNKNOWN_LOGIN_ERROR))
		{
			stringErrors.add("ACCOUNT_UNKNOWN_LOGIN_ERROR");
		}
		if (stringErrors.size() == 0)
		{
			return null;
		}
		return stringErrors;
	}
}