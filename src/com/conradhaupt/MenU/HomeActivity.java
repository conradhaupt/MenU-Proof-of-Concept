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

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		getActionBar().setDisplayShowTitleEnabled(false);
		getActionBar().setHomeButtonEnabled(true);
		setContentView(R.layout.activity_home);
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		ft.add(R.id.framelayout, new HomeFragment());
		ft.commit();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_home, menu);
		return true;
	}

	public void onBackPressed()
	{
	}

	public boolean onOptionsItemSelected(MenuItem item)
	{
		System.out.println("Action bar button was pressed!");
		System.out.println(item.getItemId());
		switch (item.getItemId())
		{
		case R.id.menu_search:
			break;
		case android.R.id.home:
			break;
		case R.id.menu_drop_about:
			break;
		case R.id.menu_drop_account:
			break;
		case R.id.menu_drop_menu:
			break;
		case R.id.menu_drop_settings:
			break;
		default:
			return super.onOptionsItemSelected(item);
		}
		return true;
	}

	// public class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter
	// {
	//
	// public FragmentManager fm;
	//
	// public ScreenSlidePagerAdapter(FragmentManager fm)
	// {
	// super(fm);
	// this.fm = fm;
	// }
	//
	// @Override
	// public Fragment getItem(int arg0)
	// {
	// try
	// {
	// System.out.println("getItem run!");
	// } catch (NullPointerException e)
	// {
	// return null;
	// }
	// }
	//
	// @Override
	// public int getCount()
	// {
	// }
	//
	// @Override
	// public int getItemPosition(Object object)
	// {
	// return -2;
	//
	// }
	// }
}
