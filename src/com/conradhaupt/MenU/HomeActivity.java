package com.conradhaupt.MenU;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;

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
		fragments[1] = new HomeContainerFragment();

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
			// fragments[1] = new HomeFragment();
			// pagerAdap.notifyDataSetChanged();
			System.out.println("Fragment change completed!");
			return super.onOptionsItemSelected(item);
//			break;
		case android.R.id.home:
			if (viewPage.getCurrentItem() != 0)
			{
				viewPage.setCurrentItem(0);
			} else
			{
				viewPage.setCurrentItem(1);
			}
			break;
		default:
			return super.onOptionsItemSelected(item);
		}
		return true;
	}

	public class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter
	{

		public FragmentManager fm;

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
				System.out.println("getItem run!");
				return fragments[arg0];
			} catch (NullPointerException e)
			{
				return null;
			}
		}

		@Override
		public int getCount()
		{
			return fragments.length;
		}

		@Override
		public int getItemPosition(Object object)
		{
			return -2;

		}
	}
}
