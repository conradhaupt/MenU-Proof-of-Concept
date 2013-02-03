package com.conradhaupt.MenU;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class HomeFragment extends Activity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_home);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.fragment_home, menu);
		return true;
	}

}
