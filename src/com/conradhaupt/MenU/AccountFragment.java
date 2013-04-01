package com.conradhaupt.MenU;

import android.app.ActionBar;
import android.app.ActionBar.OnNavigationListener;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class AccountFragment extends Fragment implements OnNavigationListener
{
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		System.out.println("Account fragment created!");
		// Inflate the layout for this fragment
		setHasOptionsMenu(true);
		// Create the actionbar dropdown menu
		ActionBar actionBar = this.getActivity().getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
		// Set up the dropdown list navigation in the action bar.
		actionBar.setListNavigationCallbacks(
		// Specify a SpinnerAdapter to populate the dropdown list.
				new ArrayAdapter<String>(actionBar.getThemedContext(),
						android.R.layout.simple_list_item_1,
						android.R.id.text1, new String[] {
								"Conradjhaupt@gmail.com",
								"conrad.haupt@gmail.com",
								"philip.b.haupt@gmail.com", }), this);
		return inflater.inflate(R.layout.fragment_account, container, false);
	}

	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		inflater.inflate(R.menu.fragment_account, menu);
	}

	@Override
	public void onPause()
	{
		// Create the actionbar dropdown menu
		ActionBar actionBar = this.getActivity().getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		super.onPause();
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
	public boolean onNavigationItemSelected(int itemPosition, long itemId)
	{
		// TODO Auto-generated method stub
		return false;
	}

}
