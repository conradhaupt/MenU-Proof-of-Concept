package com.conradhaupt.MenU;

import java.util.ArrayList;
import java.util.Arrays;

import android.content.Context;
import android.content.Intent;
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
import android.widget.ListView;
import android.widget.ProgressBar;

import com.conradhaupt.MenU.Core.MenUServerInteraction;
import com.conradhaupt.MenU.Core.Restaurant;
import com.conradhaupt.MenU.Core.RestaurantBrowserFragmentMenuAdapter;

public class RestaurantBrowserFragment extends Fragment implements
		OnItemClickListener
{

	public ListView listView;
	public ProgressBar progressBar;
	public RestaurantBrowserFragmentMenuAdapter adapter = null;
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
		progressBar = (ProgressBar) this.getActivity().findViewById(
				R.id.fragment_restaurant_browser_progressbar);
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
			refreshRestaurants();
			break;
		default:
		}
		return true;
	}

	private void refreshRestaurants()
	{
		progressBar.setVisibility(View.VISIBLE);
		listView.setVisibility(View.GONE);
		System.out.println("Refreshing restaurants");
		new AsyncTask<Context, Integer, ArrayList<Restaurant>>()
		{

			private Context context;

			@Override
			protected ArrayList<Restaurant> doInBackground(Context... params)
			{
				context = params[0];
				return MenUServerInteraction.RestaurantInteraction
						.getRestaurants(context);
			}

			@Override
			protected void onPostExecute(ArrayList<Restaurant> result)
			{
				super.onPostExecute(result);

				try
				{
					// Retrieve all restaurants and sort into array
					Object[] restaurantsObj = result.toArray();
					Restaurant[] restaurantsTemp = new Restaurant[restaurantsObj.length];
					for (int i = 0; i < restaurantsObj.length; i++)
					{
						restaurantsTemp[i] = (Restaurant) restaurantsObj[i];
					}
					System.out.println("Retrieved " + restaurantsTemp.length
							+ "restaurants");
					Arrays.sort(restaurantsTemp);

					// Assign data set
					restaurants = restaurantsTemp;

					// Handle adapter
					if (adapter == null)
					{
						System.out.println("Adapter was null, instantiating");
						adapter = new RestaurantBrowserFragmentMenuAdapter(
								context, R.layout.list_restaurant_restaurant,
								restaurants);
						listView.setAdapter(adapter);
					}

					// Update data set
					adapter.notifyDataSetChanged();

					// Change visibility of progressbar
					listView.setVisibility(View.VISIBLE);
					progressBar.setVisibility(View.GONE);
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
		try
		// Try catch is to check if the tag is a restaurant object
		{
			Restaurant itemRestaurant = (Restaurant) (view.getTag());
			// Handle according to one pane
			Intent intent = new Intent(
					RestaurantBrowserFragment.this.getActivity(),
					RestaurantActivity.class);
			Bundle bundle = new Bundle();
			bundle.putString("restaurantName",
					itemRestaurant.getRestaurantName());
			bundle.putInt("restaurantID", itemRestaurant.getRestaurantID());
			bundle.putInt("addressID", itemRestaurant.getAddressID());
			bundle.putInt("categoryID", itemRestaurant.getCategoryID());
			bundle.putInt("franchiseID", itemRestaurant.getFranchiseID());

			intent.putExtras(bundle);
			startActivity(intent);

		} catch (Exception e)
		{
			System.out
					.println("Found a tag that wasn't a restaurant object, WHY!?!?!?");
		}
	}
}
