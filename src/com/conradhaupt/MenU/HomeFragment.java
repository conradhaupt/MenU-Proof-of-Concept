package com.conradhaupt.MenU;

import com.conradhaupt.MenU.R;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.TextView;

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
			AsyncTask<Activity, Integer, Integer>
	{

		private Activity activity;

		@Override
		protected Integer doInBackground(Activity... params)
		{
			this.activity = params[0];
			while (!isCancelled())
			{
				System.out.println("Not cancelled!");
			}
			return null;
		}

		@Override
		protected void onProgressUpdate(Integer... values)
		{
			super.onProgressUpdate(values);
		}

		@Override
		protected void onPostExecute(Integer result)
		{
			System.out.println("onProgressUpdate run");
			// TextView textView = (TextView) HomeFragment.this.getActivity()
			// .findViewById(R.id.textView1);
			// textView.setText("HELLO THERE!");
			super.onPostExecute(result);
		}

		@Override
		protected void onCancelled()
		{
			TextView textView = (TextView) activity
					.findViewById(R.id.textView1);
			textView.setText("HELLO THERE!");
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
