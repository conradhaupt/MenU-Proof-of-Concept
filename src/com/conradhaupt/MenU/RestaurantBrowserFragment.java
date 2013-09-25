package com.conradhaupt.MenU;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
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

public class RestaurantBrowserFragment extends ListFragment implements
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
		new AsyncTask<Context, Integer, Restaurant[]>()
		{

			@Override
			protected Restaurant[] doInBackground(Context... params)
			{
				Context context = params[0];
				MenUServerInteraction.RestaurantInteraction
						.getRestaurants(context);
				return null;
			}

			@Override
			protected void onPostExecute(Restaurant[] result)
			{
				super.onPostExecute(result);
			}
		};
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id)
	{

	}

}
