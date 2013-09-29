package com.conradhaupt.MenU.Core;

public class Restaurant implements Comparable<Restaurant>
{

	private String restaurantName;
	private int restaurantID;
	private int addressID;
	private int categoryID;
	private int franchiseID;

	/* Restaurant Constructor */
	public Restaurant()
	{

	}

	/* Parametised constructor */
	public Restaurant(int restaurantID, String restaurantName, int addressID,
			int categoryID, int franchiseID)
	{
		this.restaurantID = restaurantID;
		this.restaurantName = restaurantName;
		this.addressID = addressID;
		this.categoryID = categoryID;
		this.franchiseID = franchiseID;
	}

	/**
	 * @return the restaurantName
	 */
	public String getRestaurantName()
	{
		return restaurantName;
	}

	/**
	 * @param restaurantName
	 *            the restaurantName to set
	 */
	public void setRestaurantName(String restaurantName)
	{
		this.restaurantName = restaurantName;
	}

	/**
	 * @return the restaurantID
	 */
	public int getRestaurantID()
	{
		return restaurantID;
	}

	/**
	 * @param restaurantID
	 *            the restaurantID to set
	 */
	public void setRestaurantID(int restaurantID)
	{
		this.restaurantID = restaurantID;
	}

	/**
	 * @return the addressID
	 */
	public int getAddressID()
	{
		return addressID;
	}

	/**
	 * @param addressID
	 *            the addressID to set
	 */
	public void setAddressID(int addressID)
	{
		this.addressID = addressID;
	}

	/**
	 * @return the categoryID
	 */
	public int getCategoryID()
	{
		return categoryID;
	}

	/**
	 * @param categoryID
	 *            the categoryID to set
	 */
	public void setCategoryID(int categoryID)
	{
		this.categoryID = categoryID;
	}

	/**
	 * @return the franchiseID
	 */
	public int getFranchiseID()
	{
		return franchiseID;
	}

	/**
	 * @param franchiseID
	 *            the franchiseID to set
	 */
	public void setFranchiseID(int franchiseID)
	{
		this.franchiseID = franchiseID;
	}

	@Override
	public int compareTo(Restaurant another)
	{
		return this.getRestaurantName().compareTo(another.getRestaurantName());
	}
}
