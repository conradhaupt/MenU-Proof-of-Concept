package com.conradhaupt.MenU.Core;

import java.util.Scanner;

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

	// Static constants
	public static String OUTPUT_TEMPLATE = "@ID#@USER#@PASS#@EMAIL#@FIRSTNAME#@LASTNAME#@ADDRESS";
	public static String ACCOUNTID_CODE = "@ID";
	public static String USERNAME_CODE = "@USER";
	public static String PASSWORD_CODE = "@PASS";
	public static String EMAIL_CODE = "@EMAIL";
	public static String FIRSTNAME_CODE = "@FIRSTNAME";
	public static String LASTNAME_CODE = "@LASTNAME";
	public static String ADDRESS_CODE = "@ADDRESS";

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

	/**
	 * @return the account instantiate code
	 * 
	 */
	public String getCode()
	{
		String output = Account.OUTPUT_TEMPLATE;
		// Replace all the values for the corresponding variable
		output.replaceAll(Account.ACCOUNTID_CODE,
				(this.accountID == null) ? "NULL" : this.accountID);
		output.replaceAll(Account.ADDRESS_CODE,
				(this.accountID == null) ? "NULL" : this.postalAddress);
		output.replaceAll(Account.EMAIL_CODE, (this.accountID == null) ? "NULL"
				: this.email);
		output.replaceAll(Account.FIRSTNAME_CODE,
				(this.accountID == null) ? "NULL" : this.firstName);
		output.replaceAll(Account.LASTNAME_CODE,
				(this.accountID == null) ? "NULL" : this.lastName);
		output.replaceAll(Account.PASSWORD_CODE,
				(this.accountID == null) ? "NULL" : this.password);
		output.replaceAll(Account.USERNAME_CODE,
				(this.accountID == null) ? "NULL" : this.username);
		return output;

	}

	public static Account instantiateCode(String code)
	{

		Account output = new Account();
		// Assign all the values to the account object
		Scanner codeScan = new Scanner(code).useDelimiter("#");
		String temp;
		try
		{
			temp = codeScan.next();
			output.setAccountID((temp.equals("NULL") ? null : temp));
			temp = codeScan.next();
			output.setEmail((temp.equals("NULL") ? null : temp));
			temp = codeScan.next();
			output.setFirstName((temp.equals("NULL") ? null : temp));
			temp = codeScan.next();
			output.setLastName((temp.equals("NULL") ? null : temp));
			temp = codeScan.next();
			output.setPassword((temp.equals("NULL") ? null : temp));
			temp = codeScan.next();
			output.setPostalAddress((temp.equals("NULL") ? null : temp));
			temp = codeScan.next();
			output.setUsername((temp.equals("NULL") ? null : temp));
		} catch (Exception e)
		{

		}
		return output;
	}
}
