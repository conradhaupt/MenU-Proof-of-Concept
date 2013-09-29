package com.conradhaupt.MenU;

import com.conradhaupt.MenU.R;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;

public class AboutFragment extends Fragment implements OnClickListener
{

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		System.out.println("About fragment created!");
		// Inflate the layout for this fragment
		setHasOptionsMenu(true);
		return inflater.inflate(R.layout.fragment_about, container, false);
	}

	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		inflater.inflate(R.menu.fragment_about, menu);
	}

	@Override
	public void onResume()
	{
		super.onResume();

		Button button1 = (Button) this.getActivity().findViewById(
				R.id.persWebButton);
		button1.setOnClickListener(this);
		Button button2 = (Button) this.getActivity().findViewById(
				R.id.gitWebButton);
		button2.setOnClickListener(this);
	}

	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch (item.getItemId())
		{
		default:
		}
		return true;
	}

	// Handle button clicks
	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
		case R.id.persWebButton:
			Uri uri = Uri.parse("http://www.conradhaupt.co.za");
			Intent intent = new Intent(Intent.ACTION_VIEW, uri);
			startActivity(intent);
			break;
		case R.id.gitWebButton:
			Uri uri1 = Uri.parse("https://github.com/conradhaupt/MenU");
			Intent intent1 = new Intent(Intent.ACTION_VIEW, uri1);
			startActivity(intent1);
			break;
		default:
			break;
		}

	}
}
