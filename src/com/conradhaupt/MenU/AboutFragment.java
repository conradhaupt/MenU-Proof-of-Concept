package com.conradhaupt.MenU;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class AboutFragment extends Fragment
{

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		// Inflate the layout for this fragment
		setHasOptionsMenu(true);
		return inflater.inflate(R.layout.fragment_about, container, false);
	}

	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		inflater.inflate(R.menu.fragment_about, menu);
	}

	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch (item.getItemId())
		{
		default:
		}
		return true;
	}
}