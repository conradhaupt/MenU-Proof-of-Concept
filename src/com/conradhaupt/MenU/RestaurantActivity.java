package com.conradhaupt.MenU;

import java.util.ArrayList;
import java.util.Arrays;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.conradhaupt.MenU.Core.MenUServerInteraction;
import com.conradhaupt.MenU.Core.ResMenuItem;
import com.conradhaupt.MenU.Core.Restaurant;
import com.conradhaupt.MenU.Core.RestaurantMenUAdapter;

public class RestaurantActivity extends Activity
{

	public Restaurant restaurant;
	public ListView listView;
	public ProgressBar progressBar;
	public ResMenuItem[] menuItems;
	public RestaurantMenUAdapter adapter = null;

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
			System.out.println("res Name");
			restaurant.setRestaurantName(extras.getString("restaurantName"));
			System.out.println("res ID");
			restaurant.setRestaurantID(extras.getInt("restaurantID"));
			System.out.println("address ID");
			restaurant.setAddressID(extras.getInt("addressID"));
			System.out.println("cat ID");
			restaurant.setCategoryID(extras.getInt("categoryID"));
			System.out.println("franchise ID");
			restaurant.setFranchiseID(extras.getInt("franchiseID"));

			// Assign views
			listView = (ListView) (this
					.findViewById(R.id.fragment_restaurant_menu_listview));
			progressBar = (ProgressBar) (this
					.findViewById(R.id.fragment_restaurant_menu_progressbar));

			// Load restaurants from server
			loadRestaurant(restaurant);
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

	public void loadRestaurant(final Restaurant loadRestaurant)
	{
		progressBar.setVisibility(View.VISIBLE);
		listView.setVisibility(View.GONE);
		new AsyncTask<Context, Integer, ArrayList<ResMenuItem>>()
		{
			Context context;

			@Override
			protected ArrayList<ResMenuItem> doInBackground(Context... params)
			{
				context = params[0];
				return MenUServerInteraction.RestaurantInteraction
						.getRestaurantMenu(loadRestaurant, context);
			}

			@Override
			protected void onPostExecute(ArrayList<ResMenuItem> result)
			{
				try
				{
					System.out.println("Handling data");
					// Retrieve all restaurants and sort into array
					Object[] restaurantsObj = result.toArray();
					ResMenuItem[] menuItemsTemp = new ResMenuItem[restaurantsObj.length];
					for (int i = 0; i < restaurantsObj.length; i++)
					{
						menuItemsTemp[i] = (ResMenuItem) restaurantsObj[i];
					}
					System.out.println("Retrieved " + menuItemsTemp.length
							+ " menu items");
					// Arrays.sort(menuItemsTemp);

					// Assign data set
					menuItems = menuItemsTemp;

					System.out.println("Handling adapter");
					// Handle adapter
					if (adapter == null)
					{
						System.out.println("Adapter was null, instantiating");
						adapter = new RestaurantMenUAdapter(context,
								R.layout.list_restaurant_menuitem, menuItems);
						listView.setAdapter(adapter);
					}

					// Update data set
					adapter.notifyDataSetChanged();

					// Change visibility of progressbar
					listView.setVisibility(View.VISIBLE);
					progressBar.setVisibility(View.GONE);
				} catch (Exception e)
				{
					System.out.println("Data loading error");
					System.out.println(e);
				}
			}

		}.execute(this);
	}
}
