package com.conradhaupt.MenU;

import com.conradhaupt.MenU.R;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

public class HomeFragment extends Fragment
{

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		System.out.println("Home fragment created!");
		// Inflate the layout for this fragment
		setHasOptionsMenu(true);
		return inflater.inflate(R.layout.fragment_home, container, false);
	}

	public void onResume()
	{
		super.onResume();
	}

	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		inflater.inflate(R.menu.fragment_home, menu);
	}

	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch (item.getItemId())
		{
		case R.id.menu_search:
			break;
		default:
			return super.onOptionsItemSelected(item);
		}
		return true;
	}

	public class HomeFragmentLoader extends AsyncTask<Bundle, Integer, Integer>
	{

		@Override
		protected Integer doInBackground(Bundle... params)
		{
			return null;
		}

		@Override
		protected void onProgressUpdate(Integer... values)
		{
			// TODO Auto-generated method stub
			super.onProgressUpdate(values);
		}

		@Override
		protected void onPostExecute(Integer result)
		{
			super.onPostExecute(result);
		}

	}

}
