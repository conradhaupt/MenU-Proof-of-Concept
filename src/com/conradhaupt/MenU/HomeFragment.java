package com.conradhaupt.MenU;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import android.app.Activity;
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
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.conradhaupt.MenU.views.HomeFragmentListViewAdapter;

public class HomeFragment extends Fragment implements OnClickListener
{

	private HomeFragmentLoader homeFragmentLoader = null;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		System.out.println("Home fragment created!");
		// Inflate the layout for this fragment
		setHasOptionsMenu(true);
		return inflater.inflate(R.layout.fragment_home, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState)
	{
		super.onActivityCreated(savedInstanceState);
		System.out.println();
	}

	public void onResume()
	{
		// Assign onClickListeners to the corresponding views in this fragment
		System.out.println("onResume run");
		int[] itemIDs = {};
		for (int i = 0; i < itemIDs.length; i++)
		{
			this.getActivity().findViewById(itemIDs[i])
					.setOnClickListener(this);
		}

		refresh();

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
		case R.id.fragment_home_refresh:
			System.out
					.println("Refreshing home fragment, starting up a new HomeFragmentLoader");
			refresh();
			break;
		default:
			return super.onOptionsItemSelected(item);
		}
		return true;
	}

	private void refresh()
	{
		// Refresh has been run, doing stuff
		System.out.println("Home fragment refresh run.");
	}

	public class HomeFragmentLoader extends
			AsyncTask<Activity, Activity, Activity>
	{

		@Override
		protected Activity doInBackground(Activity... params)
		{
			System.out.println("doInBackground run");
			return params[0];
		}

		@Override
		protected void onProgressUpdate(Activity... values)
		{
			super.onProgressUpdate(values);
		}

		@Override
		protected void onPostExecute(Activity result)
		{
			// If the asyntask has finished successfully then run this code
			System.out.println("onProgressUpdate run");
			super.onPostExecute(result);
		}

		@Override
		protected void onCancelled()
		{
			// If the asynctask is cancelled run this code
			System.out.println("Canceled");
		}

	}

	@Override
	public void onPause()
	{
		System.out.println("onPause run");
		// The fragment is invisible now, if the HomeFragmentLoader is still
		// loading then cancel it to prevent background processing
		if (homeFragmentLoader != null)
		{
			homeFragmentLoader.cancel(true);
		}
		super.onPause();
	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
		default:
			break;
		}
	}
}
