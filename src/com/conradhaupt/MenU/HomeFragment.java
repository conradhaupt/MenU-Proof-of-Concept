package com.conradhaupt.MenU;

import com.conradhaupt.MenU.views.FeaturedCard;
import com.fima.cardsui.views.CardUI;
import com.github.espiandev.showcaseview.ShowcaseView;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class HomeFragment extends Fragment
{

	public CardUI mCardView;

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
		mCardView = (CardUI) this.getActivity().findViewById(R.id.cardUI1);
		mCardView.setSwipeable(true);
		mCardView.addCard(new FeaturedCard("1"));
		mCardView.addCard(new FeaturedCard("2"));
		mCardView.addCard(new FeaturedCard("3"));
		mCardView.addCard(new FeaturedCard("4"));
		mCardView.addCard(new FeaturedCard("5"));
		mCardView.addCard(new FeaturedCard("6"));
		mCardView.refresh();
		System.out.println("Added card!");
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
			mCardView.addCard(new FeaturedCard("Title"));
			mCardView.addCardToLastStack(new FeaturedCard("New Card"), true);
			System.out.println("Added card!");
			mCardView.refresh();
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
