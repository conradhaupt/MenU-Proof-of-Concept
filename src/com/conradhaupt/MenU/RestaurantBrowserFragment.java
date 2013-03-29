package com.conradhaupt.MenU;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;

public class RestaurantBrowserFragment extends ListFragment implements
		OnScrollListener
{

	public ListView listView;
	public ArrayAdapter adapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		System.out.println("Restaurant Browser fragment created!");
		// Inflate the layout for this fragment
		setHasOptionsMenu(true);
		// String[] array = { "hello", "My name is", "Conrad" };
		// adapter = new ArrayAdapter<String>(this,
		// R.layout.fragment_restaurant,
		// array);
		return inflater.inflate(R.layout.fragment_restaurant_browser,
				container, false);
	}

	public void onResume()
	{
		listView = (ListView) this.getActivity().findViewById(R.id.listView1);
		listView.setOnScrollListener(this);
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
		default:
		}
		return true;
	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount)
	{
		System.out.println("On scroll: " + "firstVisibleItem = "
				+ firstVisibleItem + "; visibleItemCount = " + visibleItemCount
				+ "; totalItemCount = " + totalItemCount + ".");
	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState)
	{

		System.out.println("Scroll state changed: " + scrollState + ".");
	}

}
