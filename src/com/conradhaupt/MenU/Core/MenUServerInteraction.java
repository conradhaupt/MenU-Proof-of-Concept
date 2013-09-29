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
		public static ArrayList<ResMenuItem> getRestaurantMenu(
				Restaurant restaurant, Context context)
		{
			System.out.println("Loading restaurants");
			ArrayList<ResMenuItem> restaurantItems = new ArrayList<ResMenuItem>();
			String output = null;
			try
			{
				List<NameValuePair> variables = new ArrayList<NameValuePair>();
				variables.add(new BasicNameValuePair("restaurantID", restaurant
						.getRestaurantID() + ""));
				HttpClient client = new DefaultHttpClient();
				HttpResponse response;
				HttpPost post = new HttpPost(context.getResources().getString(
						R.string.connection_url_restaurant_menu_list));
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
										.useDelimiter("\".\"|\"...\"");
								System.out.println("Scan output for line "
										+ position);
								int elementPosition = 0;
								int totalCount = 15;

								// Temp values
								int tRestaurantID = 0;
								int tProductID = 0;
								String tProductName = null;
								String tDesc = null;
								String tIng = null;
								int tCurrencyID = 0;
								double tPrice = 0;
								int tCategoryID = 0;
								int tKosher = 0;
								int tHallal = 0;
								int tVegeterian = 0;
								int tVegan = 0;
								int tContainNuts = 0;
								int tContainDairy = 0;
								int tContainWheat = 0;

								while (scan.hasNext())
								{
									String element = scan.next();
									// Replace elements not needed
									element = element.replace("[{\"", "");
									element = element.replace("\"},{\"", "");
									element = element.replace("\"}]", "");

									String[] contains = { "RestaurantID",
											"ProductID", "ProductName",
											"Description", "Ingredients",
											"CurrencyID", "Price",
											"CategoryID", "Kosher", "Hallal",
											"Vegeterian", "Vegan",
											"ContainsNuts", "ContainsDairy",
											"ContainsWheat" };
									boolean containsStuff = false;
									for (int i = 0; i < contains.length; i++)
									{
										if (element.contains(contains[i]))
										{
											containsStuff = true;
										}
									}
									// Check if the element isn't a title
									if (!containsStuff)
									{
										// Element is not a title
										System.out.println(element
												+ " with element count "
												+ elementPosition);
										switch (elementPosition)
										{
										case 0:
											tRestaurantID = Integer
													.parseInt(element);
											break;
										case 1:
											tProductID = Integer
													.parseInt(element);
											break;
										case 2:
											tProductName = element;
											break;
										case 3:
											tDesc = element;
											break;
										case 4:
											tIng = element;
											break;
										case 5:
											tCurrencyID = Integer
													.parseInt(element);
											break;
										case 6:
											tPrice = Double
													.parseDouble(element);
											break;
										case 7:
											tCategoryID = Integer
													.parseInt(element);
											break;
										case 8:
											tKosher = Integer.parseInt(element);
											break;
										case 9:
											tHallal = Integer.parseInt(element);
											break;
										case 10:
											tVegeterian = Integer
													.parseInt(element);
											break;
										case 11:
											tVegan = Integer.parseInt(element);
											break;
										case 12:
											tContainNuts = Integer
													.parseInt(element);
											break;
										case 13:
											tContainDairy = Integer
													.parseInt(element);
											break;
										case 14:
											tContainWheat = Integer
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
											System.out
													.println("Adding restaurant item");
											ResMenuItem temp = new ResMenuItem(
													tProductID,
													tRestaurantID,
													tProductName,
													tDesc,
													tIng,
													tCurrencyID,
													tPrice,
													tCategoryID,
													tKosher == 1 ? true : false,
													tHallal == 1 ? true : false,
													tVegeterian == 1 ? true
															: false,
													tVegan == 1 ? true : false,
													tContainNuts == 1 ? true
															: false,
													tContainDairy == 1 ? true
															: false,
													tContainWheat == 1 ? true
															: false);
											restaurantItems.add(temp);
											System.out
													.println("Just added an objetc with the values"
															+ temp.getProductName()
															+ " "
															+ tProductName);
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
				System.out.println("Server error:");
				System.out.println(t);
				output = t.toString();
			}
			return restaurantItems;
		}

		public static ArrayList<Restaurant> getRestaurants(Context context)
		{
			System.out.println("Loading restaurants");
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
								int elementPosition = 0;
								int totalCount = 5;

								// Temp values
								String restaurantName = null;
								int restaurantID = 0;
								int addressID = 0;
								int categoryID = 0;
								int franchiseID = 0;
								int restCount = 1;

								while (scan.hasNext())
								{
									String element = scan.next();
									// Replace elements not needed
									element = element.replace("[{\"", "");
									element = element.replace("\"},{\"", "");
									element = element.replace("\"}]", "");

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
											// System.out.println("RestaurantID");
											restaurantID = Integer
													.parseInt(element);
											break;
										case 1:
											// System.out
											// .println("RestaurantName");
											restaurantName = element;
											break;
										case 2:
											// System.out.println("AddressID");
											addressID = Integer
													.parseInt(element);
											break;
										case 3:
											// System.out.println("CategoryID");
											categoryID = Integer
													.parseInt(element);
											break;
										case 4:
											// System.out.println("FranchiseID");
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
											System.out
													.println("Adding restaurant number "
															+ restCount);
											restaurants.add(new Restaurant(
													restaurantID,
													restaurantName, addressID,
													categoryID, franchiseID));
											elementPosition = 0;
											restCount++;

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
