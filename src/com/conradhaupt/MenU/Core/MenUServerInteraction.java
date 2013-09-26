package com.conradhaupt.MenU.Core;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.content.Context;

import com.conradhaupt.MenU.R;

public class MenUServerInteraction
{

	public static class AccountInteraction
	{
		public static AccountError registerAccount(Account account,
				Context context)
		{
			System.out.println("Registering account");
			AccountError outputErrors = new AccountError();
			String output = null;
			try
			{
				List<NameValuePair> variables = new ArrayList<NameValuePair>();
				variables.add(new BasicNameValuePair("username", account
						.getUsername()));
				variables.add(new BasicNameValuePair("password", account
						.getPassword()));
				variables.add(new BasicNameValuePair("email", account
						.getEmail()));
				variables.add(new BasicNameValuePair("firstname", account
						.getFirstName()));
				variables.add(new BasicNameValuePair("lastname", account
						.getLastName()));

				HttpClient client = new DefaultHttpClient();
				HttpResponse response;
				HttpPost post = new HttpPost(context.getResources().getString(
						R.string.connection_url_account_create));
				post.setEntity(new UrlEncodedFormEntity(variables));
				response = client.execute(post);
				if (response != null)
				{
					InputStream in = response.getEntity().getContent();

					if (in != null)
					{
						StringBuilder sb = new StringBuilder();
						String line;
						try
						{
							BufferedReader reader = new BufferedReader(
									new InputStreamReader(in, "UTF-8"));
							while ((line = reader.readLine()) != null)
							{
								sb.append(line).append("\n");
							}
						} finally
						{
							in.close();
						}
						output = sb.toString();
					} else
					{
						output = "";
					}
				}
			} catch (Exception t)
			{
				output = t.toString();
			}

			System.out
					.println("Connected to server with output or returned message of:\n"
							+ output);
			// Add all errors if found
			if (output.contains(AccountError.CONNECTION_NOT_AVAILABLE_KEYWORD))
			{
				outputErrors.addError(AccountError.CONNECTION_NOT_AVAILABLE);
			}
			if (output.contains(AccountError.USERNAME_NOT_AVAILABLE_KEYWORD))
			{
				outputErrors.addError(AccountError.USERNAME_NOT_AVAILABLE);
			}
			if (output.contains(AccountError.EMAIL_NOT_AVAILABLE_KEYWORD))
			{
				outputErrors.addError(AccountError.EMAIL_NOT_AVAILABLE);
			}
			System.out
					.println("The RegistrationError object recorded the following errors:\n"
							+ outputErrors.getStringErrors());
			return outputErrors;
		}

		public static AccountError loginAccount(Account account, Context context)
		{
			System.out.println("Logging into account");
			AccountError outputErrors = new AccountError();
			String output = null;
			String accessCode = null;
			try
			{
				List<NameValuePair> variables = new ArrayList<NameValuePair>();
				variables.add(new BasicNameValuePair("username", account
						.getUsername()));
				variables.add(new BasicNameValuePair("password", account
						.getPassword()));
				HttpClient client = new DefaultHttpClient();
				HttpResponse response;
				HttpPost post = new HttpPost(context.getResources().getString(
						R.string.connection_url_account_login));
				post.setEntity(new UrlEncodedFormEntity(variables));
				response = client.execute(post);
				if (response != null)
				{
					InputStream in = response.getEntity().getContent();
					if (in != null)
					{
						StringBuilder sb = new StringBuilder();
						String line;
						try
						{
							BufferedReader reader = new BufferedReader(
									new InputStreamReader(in, "UTF-8"));
							int position = 0;
							while ((line = reader.readLine()) != null)
							{
								sb.append(line).append("\n");
								System.out.println("Line " + position + ": "
										+ line);
								Scanner scan = new Scanner(line)
										.useDelimiter("\".\"");
								System.out.println("Scan output for line "
										+ position);
								boolean nextIsAccessCode = false;
								while (scan.hasNext())
								{
									if (!nextIsAccessCode)
									{
										// The next scan output is not the
										// access
										// code
										String temp = scan.next();
										if (temp.equals("accessCode"))
										{
											// If the current line reads
											// accessCode
											// then the next line is the
											// accessCode
											nextIsAccessCode = true;
										}
										System.out.println(temp);
									} else
									{
										// The next scan output is the access
										// code
										accessCode = scan.next();
										// Remove the last 3 characters that do
										// not
										// apply
										accessCode = accessCode.replace("\"}",
												"");
									}
								}
							}
						} finally
						{
							in.close();
						}
						output = sb.toString();
					} else
					{
						output = "";
					}
				}
			} catch (Exception t)
			{
				output = t.toString();
			}

			System.out
					.println("Connected to server with output or returned message of:\n"
							+ output);
			// Add all errors if found
			if (output.contains(AccountError.ACCOUNT_DOESNT_EXIST_KEYWORD))
			{
				outputErrors.addError(AccountError.ACCOUNT_DOESNT_EXIST);
			}
			if (output.contains(AccountError.PASSWORD_NOT_CORRECT_KEYWORD))
			{
				outputErrors.addError(AccountError.PASSWORD_NOT_CORRECT);
			}
			if (output.contains(AccountError.ACCOUNT_SUSPENDED_KEYWORD))
			{
				outputErrors.addError(AccountError.ACCOUNT_SUSPENDED);
			}
			if (accessCode != null)
			{
				outputErrors.setAccessCode(accessCode);
			}
			System.out
					.println("The RegistrationError object recorded the following errors:\n"
							+ outputErrors.getStringErrors());
			return outputErrors;
		}

	}

	public static class RestaurantInteraction
	{
		public static RestaurantError getRestaurantMenu(int restaurantID,
				Context context)
		{
			System.out.println("Logging into account");
			RestaurantError outputErrors = new RestaurantError();
			String output = null;
			try
			{
				List<NameValuePair> variables = new ArrayList<NameValuePair>();
				variables.add(new BasicNameValuePair("restaurantID",
						restaurantID + ""));
				HttpClient client = new DefaultHttpClient();
				HttpResponse response;
				HttpPost post = new HttpPost(context.getResources().getString(
						R.string.connection_url_account_login));
				post.setEntity(new UrlEncodedFormEntity(variables));
				response = client.execute(post);
				if (response != null)
				{
					InputStream in = response.getEntity().getContent();
					if (in != null)
					{
						StringBuilder sb = new StringBuilder();
						String line;
						try
						{
							BufferedReader reader = new BufferedReader(
									new InputStreamReader(in, "UTF-8"));
							int position = 0;
							while ((line = reader.readLine()) != null)
							{
								sb.append(line).append("\n");
								System.out.println("Line " + position + ": "
										+ line);
								Scanner scan = new Scanner(line)
										.useDelimiter("\".\"");
								System.out.println("Scan output for line "
										+ position);
								boolean nextIsAccessCode = false;
								while (scan.hasNext())
								{
									if (!nextIsAccessCode)
									{
										// The next scan output is not the
										// access
										// code
										String temp = scan.next();
										if (temp.equals("accessCode"))
										{
											// If the current line reads
											// accessCode
											// then the next line is the
											// accessCode
											nextIsAccessCode = true;
										}
										System.out.println(temp);
									} else
									{
									}
								}
							}
						} finally
						{
							in.close();
						}
						output = sb.toString();
					} else
					{
						output = "";
					}
				}
			} catch (Exception t)
			{
				output = t.toString();
			}

			System.out
					.println("Connected to server with output or returned message of:\n"
							+ output);
			// Add all errors if found
			if (output.contains(AccountError.ACCOUNT_DOESNT_EXIST_KEYWORD))
			{
				outputErrors.addError(AccountError.ACCOUNT_DOESNT_EXIST);
			}
			System.out
					.println("The RestaurantError object recorded the following errors:\n"
							+ outputErrors.getStringErrors());
			return outputErrors;
		}

		public static ArrayList<Restaurant> getRestaurants(Context context)
		{
			System.out.println("Logging into account");
			ArrayList<Restaurant> restaurants = new ArrayList<Restaurant>();
			String output = null;
			try
			{
				HttpClient client = new DefaultHttpClient();
				HttpResponse response;
				HttpPost post = new HttpPost(context.getResources().getString(
						R.string.connection_url_restaurant_retrieve_list));
				response = client.execute(post);
				if (response != null)
				{
					InputStream in = response.getEntity().getContent();
					if (in != null)
					{
						StringBuilder sb = new StringBuilder();
						String line;
						try
						{
							BufferedReader reader = new BufferedReader(
									new InputStreamReader(in, "UTF-8"));
							int position = 0;
							while ((line = reader.readLine()) != null)
							{
								sb.append(line).append("\n");
								System.out.println("Line " + position + ": "
										+ line);
								Scanner scan = new Scanner(line)
										.useDelimiter("\".\"|\"...\"");
								System.out.println("Scan output for line "
										+ position);
								boolean nextIsAccessCode = false;
								int elementPosition = 0;
								int totalCount = 5;

								// Temp values
								String restaurantName;
								int restaurantID;
								int addressID;
								int categoryID;
								int franchiseID;

								while (scan.hasNext())
								{
									String element = scan.next();
									// Replace elements not needed
									element.replace("[{\"", "");
									element.replace("\"},{\"", "");
									element.replace("\"}]", "");

									// Check if the element isn't a title
									if (!element.contains("RestaurantID")
											&& !element
													.contains("RestaurantName")
											&& !element.contains("AddressID")
											&& !element.contains("CategoryID")
											&& !element.contains("FranchiseID"))
									{
										// Element is not a title
										System.out.println(element
												+ " with element count "
												+ elementPosition);
										switch (elementPosition)
										{
										case 0:
											restaurantID = Integer
													.parseInt(element);
											break;
										case 1:
											restaurantName = element;
											break;
										case 2:
											addressID = Integer
													.parseInt(element);
											break;
										case 3:
											categoryID = Integer
													.parseInt(element);
											break;
										case 4:
											franchiseID = Integer
													.parseInt(element);
											break;
										default:
											System.out
													.println("It's dead JIM!!!");
											break;
										}
										elementPosition++;
										if (elementPosition >= totalCount)
										{
											restaurants.add(new Restaurant());
											elementPosition = 0;

										}
									}
								}
							}
						} finally
						{
							in.close();
						}
						output = sb.toString();
					} else
					{
						output = "";
					}
				}
			} catch (Exception t)
			{
				output = t.toString();
			}
			return restaurants;
		}
	}
}
