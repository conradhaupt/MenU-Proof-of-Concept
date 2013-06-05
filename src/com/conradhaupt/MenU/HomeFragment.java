package com.conradhaupt.MenU;

import android.app.Activity;
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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.conradhaupt.MenU.objects.Card;
import com.conradhaupt.MenU.views.ListViewCardAdapter;

public class HomeFragment extends Fragment implements OnClickListener
{

	private HomeFragmentLoader homeFragmentLoader = null;
	public String[] values = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J" };

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
		int[] itemIDs = {};
		for (int i = 0; i < itemIDs.length; i++)
		{
			this.getActivity().findViewById(itemIDs[i])
					.setOnClickListener(this);
		}
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
			// Assign the temporary variable the value of the new object and
			// execute
			homeFragmentLoader = (HomeFragmentLoader) new HomeFragmentLoader();
			homeFragmentLoader.execute(this.getActivity());
			break;
		default:
			return super.onOptionsItemSelected(item);
		}
		return true;
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
			// Instantiate the listview
			ListView listView = (ListView) result
					.findViewById(R.id.home_fragment_listview);
			final ListViewCardAdapter adapter = new ListViewCardAdapter(
					getActivity(), android.R.layout.simple_list_item_1, values);
			listView.setAdapter(adapter);
			listView.setOnItemClickListener(new OnItemClickListener()
			{

				@Override
				public void onItemClick(AdapterView<?> arg0, View v,
						int position, long id)
				{
					// values[position] = "NOTHING!";
					// arrayAdapter.notifyDataSetChanged();
				}
			});
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
