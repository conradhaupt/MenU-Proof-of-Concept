package com.conradhaupt.MenU;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;

import com.conradhaupt.MenU.Core.Restaurant;

public class RestaurantActivity extends Activity
{

	public Restaurant restaurant;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_restaurant);
		// Show the Up button in the action bar.
		getActionBar().setDisplayHomeAsUpEnabled(true);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_restaurant, menu);
		return true;
	}

	@Override
	protected void onResume()
	{
		// TODO Auto-generated method stub
		super.onResume();
		try
		{
			// Get restaurant data
			Bundle extras = getIntent().getExtras();
			restaurant = new Restaurant();
			restaurant.setRestaurantName(extras.getString("restaurantName"));
			restaurant.setRestaurantID(extras.getInt("restaurantID"));
			restaurant.setAddressID(extras.getInt("addressID"));
			restaurant.setCategoryID(extras.getInt("categoryID"));
			restaurant.setFranchiseID(extras.getInt("franchiseID"));
		} catch (Exception e)
		{
			System.out.println("Exception caught:");
			System.out.println(e);
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch (item.getItemId())
		{
		case android.R.id.home:
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public void loadRestaurant(Restaurant loadRestaurant)
	{

	}

}
