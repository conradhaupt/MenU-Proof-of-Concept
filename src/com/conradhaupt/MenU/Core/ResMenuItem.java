package com.conradhaupt.MenU.Core;

// Menu Item Class
public class ResMenuItem implements Comparable<ResMenuItem>
{
	/* Menu Item Variables (taken from MySQL table) */
	private int productID;
	private int restaurantID;
	private String productName;
	private String description;
	private String ingredients;
	private int currencyID;
	private String currencyCode;
	private double price;
	private int categoryID;
	private String categoryName;
	private boolean kosher;
	private boolean hallal;
	private boolean vegeterian;
	private boolean vegan;
	private boolean containsNuts;
	private boolean containsDairy;
	private boolean containsWheat;

	public ResMenuItem()
	{

	}

	public ResMenuItem(int productID, int restaurantID, String productName,
			String description, String ingredients, int currencyID,
			double price, int categoryID, boolean kosher, boolean hallal,
			boolean vegeterian, boolean vegan, boolean containsNuts,
			boolean containsDairy, boolean containsWheat)
	{
		super();
		this.productID = productID;
		this.restaurantID = restaurantID;
		this.productName = productName;
		this.description = description;
		this.ingredients = ingredients;
		this.currencyID = currencyID;
		this.price = price;
		this.categoryID = categoryID;
		this.kosher = kosher;
		this.hallal = hallal;
		this.vegeterian = vegeterian;
		this.vegan = vegan;
		this.containsNuts = containsNuts;
		this.containsDairy = containsDairy;
		this.containsWheat = containsWheat;
	}

	/**
	 * @return the productID
	 */
	public int getProductID()
	{
		return productID;
	}

	/**
	 * @param productID
	 *            the productID to set
	 */
	public void setProductID(int productID)
	{
		this.productID = productID;
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
	 * @return the productName
	 */
	public String getProductName()
	{
		return productName;
	}

	/**
	 * @param productName
	 *            the productName to set
	 */
	public void setProductName(String productName)
	{
		this.productName = productName;
	}

	/**
	 * 
	 * @return the description
	 */
	public String getDescription()
	{
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description)
	{
		this.description = description;
	}

	/**
	 * @return the ingredients
	 */
	public String getIngredients()
	{
		return ingredients;
	}

	/**
	 * @param ingredients
	 *            the ingredients to set
	 */
	public void setIngredients(String ingredients)
	{
		this.ingredients = ingredients;
	}

	/**
	 * @return the currencyID
	 */
	public int getCurrencyID()
	{
		return currencyID;
	}

	/**
	 * @param currencyID
	 *            the currencyID to set
	 */
	public void setCurrencyID(int currencyID)
	{
		this.currencyID = currencyID;
	}

	/**
	 * @return the currencyCode
	 */
	public String getCurrencyCode()
	{
		return currencyCode;
	}

	/**
	 * @param currencyCode
	 *            the currencyCode to set
	 */
	public void setCurrencyCode(String currencyCode)
	{
		this.currencyCode = currencyCode;
	}

	/**
	 * @return the price
	 */
	public double getPrice()
	{
		return price;
	}

	/**
	 * @param price
	 *            the price to set
	 */
	public void setPrice(double price)
	{
		this.price = price;
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
	 * @return the categoryName
	 */
	public String getCategoryName()
	{
		return categoryName;
	}

	/**
	 * @param categoryName
	 *            the categoryName to set
	 */
	public void setCategoryName(String categoryName)
	{
		this.categoryName = categoryName;
	}

	/**
	 * @return the kosher
	 */
	public boolean isKosher()
	{
		return kosher;
	}

	/**
	 * @param kosher
	 *            the kosher to set
	 */
	public void setKosher(boolean kosher)
	{
		this.kosher = kosher;
	}

	/**
	 * @return the hallal
	 */
	public boolean isHallal()
	{
		return hallal;
	}

	/**
	 * @param hallal
	 *            the hallal to set
	 */
	public void setHallal(boolean hallal)
	{
		this.hallal = hallal;
	}

	/**
	 * @return the vegeterian
	 */
	public boolean isVegeterian()
	{
		return vegeterian;
	}

	/**
	 * @param vegeterian
	 *            the vegeterian to set
	 */
	public void setVegeterian(boolean vegeterian)
	{
		this.vegeterian = vegeterian;
	}

	/**
	 * @return the vegan
	 */
	public boolean isVegan()
	{
		return vegan;
	}

	/**
	 * @param vegan
	 *            the vegan to set
	 */
	public void setVegan(boolean vegan)
	{
		this.vegan = vegan;
	}

	/**
	 * @return the containsNuts
	 */
	public boolean isContainsNuts()
	{
		return containsNuts;
	}

	/**
	 * @param containsNuts
	 *            the containsNuts to set
	 */
	public void setContainsNuts(boolean containsNuts)
	{
		this.containsNuts = containsNuts;
	}

	/**
	 * @return the containsDairy
	 */
	public boolean isContainsDairy()
	{
		return containsDairy;
	}

	/**
	 * @param containsDairy
	 *            the containsDairy to set
	 */
	public void setContainsDairy(boolean containsDairy)
	{
		this.containsDairy = containsDairy;
	}

	/**
	 * @return the containsWheat
	 */
	public boolean isContainsWheat()
	{
		return containsWheat;
	}

	/**
	 * @param containsWheat
	 *            the containsWheat to set
	 */
	public void setContainsWheat(boolean containsWheat)
	{
		this.containsWheat = containsWheat;
	}

	@Override
	public int compareTo(ResMenuItem another)
	{
		return this.getProductName().compareTo(another.getProductName());
	}
}
