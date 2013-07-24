package com.conradhaupt.MenU.Core;

import android.content.Context;

public class Account
{

	// Object variables
	private String accountID = null;
	private String username = null;
	private String password = null;
	private String email = null;
	private String firstName = null;
	private String lastName = null;
	private String postalAddress = null;

	public Account()
	{

	}

	/**
	 * @return the accountID
	 */
	public String getAccountID()
	{
		return accountID;
	}

	/**
	 * @param accountID
	 *            the accountID to set
	 */
	public void setAccountID(String accountID)
	{
		this.accountID = accountID;
	}

	/**
	 * @return the username
	 */
	public String getUsername()
	{
		return username;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username)
	{
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword()
	{
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password)
	{
		this.password = password;
	}

	/**
	 * @return the email
	 */
	public String getEmail()
	{
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email)
	{
		this.email = email;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName()
	{
		return firstName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName()
	{
		return lastName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	/**
	 * @return the postalAddress
	 */
	public String getPostalAddress()
	{
		return postalAddress;
	}

	/**
	 * @param postalAddress
	 *            the postalAddress to set
	 */
	public void setPostalAddress(String postalAddress)
	{
		this.postalAddress = postalAddress;
	}

	public static AccountError validate(Account account, Context context)
	{
		AccountError validationError = new AccountError(context);

		boolean temp;

		System.out.println("Checking username");
		// Check the username
		// Check for invalid characters
		temp = true;
		for (int i = 0; i < account.getUsername().length(); i++)
		{
			if (AccountError.USERNAME_CHARACTERS.indexOf(account.getUsername()
					.charAt(i)) == -1)
			{
				temp = false;
			}
		}
		if (!temp)
		{
			validationError.addError(AccountError.USERNAME_INVALID_CHARACTERS);
		}
		// Check for specified length
		temp = true;
		if (!(account.getUsername().length() >= AccountError.USERNAME_LENGTH_MIN)
				|| !(account.getUsername().length() < AccountError.USERNAME_LENGTH_MAX))
		{
			temp = false;
		}
		if (!temp)
		{
			validationError.addError(AccountError.USERNAME_INVALID_LENGTH);
		}

		System.out.println("Checking password");
		// Check the password
		// Check the specified length
		temp = true;
		if (!(account.getPassword().length() >= AccountError.PASSWORD_LENGTH_MIN)
				|| !(account.getPassword().length() < AccountError.PASSWORD_LENGTH_MAX))
		{
			temp = false;
		}
		if (!temp)
		{
			validationError.addError(AccountError.PASSWORD_INVALID_LENGTH);
		}

		System.out.println("Checking first name");
		// Check the first name
		// Check the specified length
		temp = true;
		if (!(account.getFirstName().length() >= AccountError.FIRSTNAME_LENGTH_MIN)
				|| !(account.getFirstName().length() < AccountError.FIRSTNAME_LENGTH_MAX))
		{
			temp = false;
		}
		if (!temp)
		{
			validationError.addError(AccountError.FIRSTNAME_INVALID_LENGTH);
		}

		System.out.println("Checking last name");
		// Check the last name
		// Check the specified length
		temp = true;
		if (!(account.getLastName().length() >= AccountError.LASTNAME_LENGTH_MIN)
				|| !(account.getLastName().length() < AccountError.LASTNAME_LENGTH_MAX))
		{
			temp = false;
		}
		if (!temp)
		{
			validationError.addError(AccountError.LASTNAME_INVALID_LENGTH);
		}

		return validationError;
	}
}