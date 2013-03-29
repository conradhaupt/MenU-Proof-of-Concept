package com.conradhaupt.MenU;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;

import com.slidingmenu.lib.SlidingMenu;
import com.slidingmenu.lib.app.SlidingFragmentActivity;

public class HomeActivity extends SlidingFragmentActivity implements
		OnClickListener
{

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
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		ft.add(R.id.fragment_frame, new HomeFragment());
		ft.commit();

		// This code assigns the onClickListener to all views requiring it
		int[] viewIDs = { R.id.slidingmenu_home_button,
				R.id.slidingmenu_about_button,
				R.id.slidingmenu_featured_button,
				R.id.slidingmenu_information_panel,
				R.id.slidingmenu_restaurant_button,
				R.id.slidingmenu_setting_button,
				R.id.slidingmenu_account_button };
		for (int i = 0; i < viewIDs.length; i++)
		{
			(findViewById(viewIDs[i])).setOnClickListener(this);
		}
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
		FragmentTransaction ft = this.getSupportFragmentManager()
				.beginTransaction();
		switch (item.getItemId())
		{
		case R.id.menu_search:
			ft.setCustomAnimations(R.anim.fragment_change_enter,
					R.anim.fragment_change_exit);
			ft.replace(R.id.fragment_frame, new RestaurantFragment());
			ft.addToBackStack(null);
			ft.commit();
			break;
		case android.R.id.home:
			getSlidingMenu().showMenu();
			break;
		case R.id.menu_dropdown:
			getSlidingMenu().showMenu();
			break;
		default:
			return super.onOptionsItemSelected(item);
		}
		return true;
	}

	public void onHomeMenuClicked(View v)
	{
		System.out.println("Home");
		for (int i = 0; i < this.getSupportFragmentManager()
				.getBackStackEntryCount(); i++)
		{
			this.getSupportFragmentManager().popBackStack();
		}
		getSlidingMenu().showContent();
	}

	public void onFeaturedMenuClicked(View v)
	{
		System.out.println("Featured");
	}

	public void onRestaurantsMenuClicked(View v)
	{
		System.out.println("Restaurants");
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		ft.setCustomAnimations(R.anim.fragment_change_enter,
				R.anim.fragment_change_exit);
		ft.replace(R.id.fragment_frame, new RestaurantFragment());
		ft.addToBackStack("RestaurantFragment");
		ft.commit();
	}

	public void onAccountsMenuClicked(View v)
	{
		System.out.println("Account has been pressed");
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		ft.setCustomAnimations(R.anim.fragment_change_enter,
				R.anim.fragment_change_exit);
		ft.replace(R.id.fragment_frame, new AccountFragment());
		ft.addToBackStack("AccountFragment");
		ft.commit();
		getSlidingMenu().showContent();
	}

	public void onSettingsMenuClicked(View v)
	{
		System.out.println("Settings has been pressed");
		Intent intent = new Intent(this, SettingsActivity.class);
		startActivity(intent);
		getSlidingMenu().showContent();
	}

	public void onAboutMenuClicked(View v)
	{
		System.out.println("About has been pressed");
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		ft.setCustomAnimations(R.anim.fragment_change_enter,
				R.anim.fragment_change_exit);
		ft.replace(R.id.fragment_frame, new AboutFragment());
		ft.addToBackStack("AboutFragment");
		ft.commit();
		getSlidingMenu().showContent();
	}

	public void onBrowserClicked(View view)
	{
		System.out.println("Browser has been pressed");
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		ft.setCustomAnimations(R.anim.fragment_change_enter,
				R.anim.fragment_change_exit);
		ft.replace(R.id.fragment_frame, new RestaurantBrowserFragment());
		ft.addToBackStack("RestaurantBrowserFragment");
		ft.commit();
	}

	public void onInformationClicked(View view)
	{
		System.out.println("Information panel has been pressed");
		AboutDialogFragment aboutDialogFragment = new AboutDialogFragment();
		aboutDialogFragment.show(this.getSupportFragmentManager(),
				"AboutDialog");
		getSlidingMenu().showContent();
	}

	@Override
	public void onClick(View v)
	{
		System.out.println("onClick run for view with id " + v.getId() + "!");
		switch (v.getId())
		{
		case R.id.slidingmenu_home_button:
			this.onHomeMenuClicked(v);
			break;
		case R.id.slidingmenu_featured_button:
			this.onFeaturedMenuClicked(v);
			break;
		case R.id.slidingmenu_restaurant_button:
			this.onRestaurantsMenuClicked(v);
			break;
		case R.id.slidingmenu_account_button:
			this.onAccountsMenuClicked(v);
			break;
		case R.id.slidingmenu_setting_button:
			this.onSettingsMenuClicked(v);
			break;
		case R.id.slidingmenu_about_button:
			this.onAboutMenuClicked(v);
			break;
		case R.id.slidingmenu_information_panel:
			this.onInformationClicked(v);
			break;
		default:
			return;
		}
	}
}
