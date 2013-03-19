package com.conradhaupt.MenU;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;

import com.slidingmenu.lib.SlidingMenu;
import com.slidingmenu.lib.app.SlidingFragmentActivity;

public class HomeActivity extends SlidingFragmentActivity
{
	private OnClickListener clickListen = new OnClickListener()
	{
		@Override
		public void onClick(View v)
		{
			switch (v.getId())
			{
			default:
				return;
			}
		}
	};

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		// This code sets the App theme
		SharedPreferences pref = PreferenceManager
				.getDefaultSharedPreferences(this);
		int themeValue = Integer.parseInt(pref.getString(
				"theme_listpreference", "-1"));
		switch (themeValue)
		{
		case 0:
			this.setTheme(R.style.holo_light_darkactionbar);
			break;
		case 1:
			this.setTheme(R.style.holo_light);
			break;
		case 2:
			this.setTheme(R.style.holo);
			break;
		default:
			System.out.println("Preference value is not assigned to a theme.");
			break;
		}

		getActionBar().setDisplayShowTitleEnabled(false);
		getActionBar().setHomeButtonEnabled(true);
		setContentView(R.layout.activity_home);
		setBehindContentView(R.layout.sliding_menu);

		// This code assigns the sliding menu parameters
		SlidingMenu slide = this.getSlidingMenu();
		slide.setMode(SlidingMenu.LEFT);
		slide.setBehindOffsetRes(R.dimen.menu_ic_logomenusize);
		slide.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		slide.setShadowDrawable(R.drawable.menusliding_shadow);
		slide.setShadowWidthRes(R.dimen.menusliding_shadow_width);

		// This code assigns the current fragment
		FragmentTransaction ft = getFragmentManager().beginTransaction();
		ft.add(R.id.fragment_frame, new HomeFragment());
		ft.commit();
	}

	@Override
	public void onBackPressed()
	{
		System.out.println("Back pressed!");
		if (this.getFragmentManager().getBackStackEntryCount() == 0)
		{
			super.onBackPressed();
		} else
		{
			this.getFragmentManager().popBackStack();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_home, menu);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item)
	{
		System.out.println("Action bar button was pressed!");
		System.out.println(item.getItemId());
		FragmentTransaction ft = this.getFragmentManager().beginTransaction();
		switch (item.getItemId())
		{
		case R.id.menu_search:
			ft.setCustomAnimations(android.R.animator.fade_in,
					android.R.animator.fade_out);
			ft.replace(R.id.fragment_frame, new RestaurantFragment());
			ft.addToBackStack(null);
			ft.commit();
			break;
		case android.R.id.home:
			// getSlidingMenu().showMenu();
			break;
		case R.id.menu_drop_about:
			ft.setCustomAnimations(android.R.animator.fade_in,
					android.R.animator.fade_out);
			ft.replace(R.id.fragment_frame, new AboutFragment());
			ft.addToBackStack(null);
			ft.commit();
			break;
		case R.id.menu_drop_account:
			ft.setCustomAnimations(android.R.animator.fade_in,
					android.R.animator.fade_out);
			ft.replace(R.id.fragment_frame, new AccountFragment());
			ft.addToBackStack(null);
			ft.commit();
			break;
		case R.id.menu_drop_menu:
			break;
		case R.id.menu_drop_settings:
			ft.setCustomAnimations(android.R.animator.fade_in,
					android.R.animator.fade_out);
			ft.replace(R.id.fragment_frame, new SettingsFragment());
			ft.addToBackStack(null);
			ft.commit();
			break;
		default:
			return super.onOptionsItemSelected(item);
		}
		return true;
	}
}
