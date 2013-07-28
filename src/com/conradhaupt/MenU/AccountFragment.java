package com.conradhaupt.MenU;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.conradhaupt.MenU.Core.Account;
import com.conradhaupt.MenU.Core.MenUServerInteraction;

public class AccountFragment extends Fragment implements OnClickListener
{
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		System.out.println("Account fragment created!");
		// Inflate the layout for this fragment
		setHasOptionsMenu(true);
		return inflater.inflate(R.layout.fragment_account, container, false);
	}

	@Override
	public void onResume()
	{
		this.getActivity().findViewById(R.id.fragment_account_temp_submit)
				.setOnClickListener(this);
		super.onResume();
	}

	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		inflater.inflate(R.menu.fragment_account, menu);
	}

	@Override
	public void onPause()
	{
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
	public void onClick(View view)
	{
		if (view.getId() == R.id.fragment_account_temp_submit)
		{
			System.out.println("Button was clicked");
			new AsyncTask<Integer, Integer, Integer>()
			{

				@Override
				protected Integer doInBackground(Integer... params)
				{
					System.out.println("doInBackground run");
					Account account = new Account();
					account.setEmail("me@conradhaupt.co.za");
					account.setFirstName("Conrad");
					account.setLastName("Haupt");
					account.setPassword("misteryork");
					account.setPostalAddress("8 18th street, parkmore");
					account.setUsername("Phenominal");
					MenUServerInteraction.registerAccount(account);
					return null;
				}
			}.execute(1);
		}
	}
}
