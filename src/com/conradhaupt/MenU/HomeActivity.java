package com.conradhaupt.MenU;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;

public class HomeActivity extends FragmentActivity
{
	private static final int NUM_PAGES = 2;
	private ViewPager viewPage;
	private PagerAdapter pagerAdap;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		getActionBar().setDisplayShowTitleEnabled(false);
		setContentView(R.layout.activity_home);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_home, menu);

		// Instantiate the ViewPage and PagerAdapter
		// viewPage = (ViewPager) findViewById(R.id.home_viewPager);
		// pagerAdap = new ScreenSlidePagerAdapter(this.getFragmentManager());
		// viewPage.setAdapter(pagerAdap);
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
	//
	// private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter
	// {
	// public ScreenSlidePagerAdapter(
	// android.support.v4.app.FragmentManager fragmentManager)
	// {
	// super(fragmentManager);
	// }
	//
	// @Override
	// public Fragment getItem(int position)
	// {
	// return new MenuFragment();
	// }
	//
	// @Override
	// public int getCount()
	// {
	// return 0;
	// }
	// }
}
