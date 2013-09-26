package com.conradhaupt.MenU;

import java.util.ArrayList;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.conradhaupt.MenU.Core.MenUServerInteraction;
import com.conradhaupt.MenU.Core.Restaurant;

public class RestaurantBrowserFragment extends Fragment implements
		OnItemClickListener
{

	public ListView listView;
	public ArrayAdapter adapter;
	private Restaurant[] restaurants = null;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		System.out.println("Restaurant Browser fragment created!");
		// Inflate the layout for this fragment
		setHasOptionsMenu(true);
		return inflater.inflate(R.layout.fragment_restaurant_browser,
				container, false);
	}

	public void onResume()
	{
		listView = (ListView) this.getActivity().findViewById(
				R.id.fragment_restaurant_browser_listview);
		listView.setOnItemClickListener(this);
		refreshRestaurants();

		super.onResume();
	}

	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		inflater.inflate(R.menu.fragment_restaurant_browser, menu);
	}

	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch (item.getItemId())
		{
		case R.id.menu_refresh:
			System.out.println("Refreshing restuarants");
			break;
		default:
		}
		return true;
	}

	private void refreshRestaurants()
	{
		System.out.println("Refreshing restaurants");
		new AsyncTask<Context, Integer, ArrayList<Restaurant>>()
		{

			@Override
			protected ArrayList<Restaurant> doInBackground(Context... params)
			{
				Context context = params[0];
				return MenUServerInteraction.RestaurantInteraction
						.getRestaurants(context);
			}

			@Override
			protected void onPostExecute(ArrayList<Restaurant> result)
			{
				super.onPostExecute(result);

				try
				{
					// Retrieve all restaurants
					Object[] restaurants = result.toArray();
					System.out.println("Names of all restaurants");
					System.out.println(restaurants.length);
					for (int i = 0; i < restaurants.length; i++)
					{
						System.out.println(((Restaurant) restaurants[i])
								.getRestaurantName());
					}
				} catch (Exception e)
				{
					System.out.println(e);
				}
			}
		}.execute(this.getActivity());
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id)
	{

	}

}
