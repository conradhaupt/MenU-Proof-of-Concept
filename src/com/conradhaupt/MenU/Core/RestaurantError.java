package com.conradhaupt.MenU.Core;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;

import com.conradhaupt.MenU.R;

public class RestaurantError
{
	// Error states
	public static final int STATE_ACCEPTABLE = 0;
	public static final int STATE_UNACCEPTABLE = 1;

	// Account information error codes

	// Account validation error codes

	// Connection or back end errors
	public static final int CONNECTION_TIME_OUT = 40;
	public static final int CONNECTION_NOT_AVAILABLE = 41;
	public static final int CONNECTION_NOT_AVAILABLE_UNKNOWN = 42;

	// Account information error keywords

	// Account validation error keywords

	// Connection or back end error keywords
	public static final String CONNECTION_TIME_OUT_KEYWORD = "";
	public static final String CONNECTION_NOT_AVAILABLE_KEYWORD = "UnknownHostException";
	public static final String CONNECTION_NOT_AVAILABLE_UNKNOWN_KEYWORD = "";

	// Account information error messages

	// Account validation error message

	// Connection or back end error message
	public static final int CONNECTION_TIME_OUT_MESSAGE = R.string.account_verification_connection_time_out;
	public static final int CONNECTION_NOT_AVAILABLE_MESSAGE = R.string.account_verification_connection_not_available;
	public static final int CONNECTION_NOT_AVAILABLE_UNKNOWN_MESSAGE = R.string.account_verification_connection_not_available_unknown;

	// Account limitations

	// Object variables
	private List<Integer> errors = new ArrayList<Integer>();
	private int state = STATE_ACCEPTABLE;
	private String restaurantCode = "NO_ACCESS_CODE";

	public RestaurantError()
	{
	}

	public String getRestaurantCode()
	{
		return restaurantCode;
	}

	public void setRestaurantCode(String accessCode)
	{
		this.restaurantCode = accessCode;
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

		return errorMessages;
	}

	public List<String> getStringErrors()
	{
		List<String> stringErrors = new ArrayList<String>();
		return stringErrors;
	}
}