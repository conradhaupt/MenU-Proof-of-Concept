package com.conradhaupt.MenU;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class HomeActivity extends FragmentActivity
{
	private static final int NUM_PAGES = 2;
	private ViewPager viewPage;
	private PagerAdapter pagerAdap;
	private Fragment[] fragments;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		getActionBar().setDisplayShowTitleEnabled(false);
		getActionBar().setHomeButtonEnabled(true);
		setContentView(R.layout.activity_home);

		fragments = new Fragment[NUM_PAGES];
		fragments[0] = new MenuFragment();
		fragments[1] = new HomeFragment();

		// Instantiate a ViewPager and a PagerAdapter.
		viewPage = (ViewPager) findViewById(R.id.pager);
		pagerAdap = new ScreenSlidePagerAdapter(getSupportFragmentManager());
		viewPage.setAdapter(pagerAdap);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_home, menu);

		// Instantiate the ViewPage and PagerAdapter
		return true;
	}

	public void onBackPressed()
	{
		if (viewPage.getCurrentItem() == 0)
		{
			// If the current item is the first, allow super back press
			super.onBackPressed();
		} else
		{
			viewPage.setCurrentItem(viewPage.getCurrentItem() - 1);
		}
	}

	public boolean onOptionsItemSelected(MenuItem item)
	{
		System.out.println("Action bar button was pressed!");
		System.out.println(item.getItemId());
		switch (item.getItemId())
		{
		case R.id.menu_search:
			System.out.println("Search button has been pressed!");
			fragments[1] = new RestaurantFragment();
			pagerAdap.setItem(1, Re);
			// MenuItem searchBar = (MenuItem)
			// menu.findItem(R.id.menu_search_editText);
			// try
			// {
			// searchBar.setVisible(!searchBar.isVisible());
			// } catch (Exception e)
			// {
			// System.out.println(e);
			// }
			// break;
			return true;
		case android.R.id.home:
			System.out.println("The icon has been pressed!");
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	public class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter
	{

		FragmentManager fm;

		public ScreenSlidePagerAdapter(FragmentManager fm)
		{
			super(fm);
			this.fm = fm;
		}

		@Override
		public Fragment getItem(int arg0)
		{
			try
			{
				return fragments[arg0];
			} catch (NullPointerException e)
			{
				return null;
			}
		}

		public void setItem(int position, Fragment newFragment)
		{
			this.fm.beginTransaction().remove(fragments[position]).commit();
			fragments[position] = newFragment.newInstance();
			notifyDataSetChanged();
		}

		@Override
		public int getCount()
		{
			return fragments.length;
		}

	}
}
