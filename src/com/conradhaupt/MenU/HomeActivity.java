package com.conradhaupt.MenU;

import com.conradhaupt.MenU.R;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.support.v4.widget.DrawerLayout;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;

public class HomeActivity extends FragmentActivity implements OnClickListener
{

	private DrawerLayout mDrawer;
	private RelativeLayout mDrawerView;
	private ActionBarDrawerToggle mDrawerToggle;
	private CharSequence mDrawerTitle;
	private CharSequence mTitle;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		SharedPreferences pref = PreferenceManager
				.getDefaultSharedPreferences(this);
		super.onCreate(savedInstanceState);
		// This code sets the App theme
		int themeValue = Integer.parseInt(pref.getString(
				"theme_listpreference", "-1"));
		switch (themeValue)
		{
		case 0:
			this.setTheme(R.style.holo_light);
			break;
		case 1:
			this.setTheme(R.style.holo_light_darkactionbar);
			break;
		default:
			System.out.println("Preference value is not assigned to a theme.");
			break;
		}

		getActionBar().setDisplayShowTitleEnabled(false);
		getActionBar().setHomeButtonEnabled(true);
		setContentView(R.layout.activity_home);

		// This instantiates the drawer variables
		mDrawerView = (RelativeLayout) this
				.findViewById(R.id.activity_home_navigation_drawer);
		mDrawer = (DrawerLayout) this.findViewById(R.id.activity_home_drawer);

		// This assigns the sliding drawer shadow
		mDrawer.setDrawerShadow(R.drawable.activity_home_drawer_shadow,
				Gravity.LEFT);
		mDrawer.setDrawerShadow(R.drawable.activity_home_drawer_shadow,
				Gravity.RIGHT);

		// This code assigns the sliding menu parameters
		if (!pref.getBoolean("smallslidingmenu_checkbox", false))
		{
			LayoutParams param = (LayoutParams) mDrawerView.getLayoutParams();
			param.width = (int) this.getResources().getDimension(
					R.dimen.activity_home_drawer_width_large);
			mDrawerView.setLayoutParams(param);
			View menu = this.getLayoutInflater().inflate(R.layout.sliding_menu,
					null);
			mDrawerView.addView(menu);
		} else
		{
			LayoutParams param = (LayoutParams) mDrawerView.getLayoutParams();
			param.width = (int) this.getResources().getDimension(
					R.dimen.activity_home_drawer_width_small);
			mDrawerView.setLayoutParams(param);
			View menu = this.getLayoutInflater().inflate(
					R.layout.sliding_menu_small, null);
			mDrawerView.addView(menu);
		}

		// This code assigns the onClickListener to all views requiring it
		int[] viewIDs = { R.id.slidingmenu_home_button,
				R.id.slidingmenu_about_button,
				R.id.slidingmenu_featured_button,
				R.id.slidingmenu_information_panel,
				R.id.slidingmenu_setting_button,
				R.id.slidingmenu_account_button };
		for (int i = 0; i < viewIDs.length; i++)
		{
			(findViewById(viewIDs[i])).setOnClickListener(this);
		}

		// This code changes the actionbar to the drawer specific version
		mTitle = this.getResources().getString(
				R.string.activity_home_drawer_title_closed);
		mDrawerTitle = this.getResources().getString(
				R.string.activity_home_drawer_title_open);
		TypedValue typedValue = new TypedValue();
		getTheme().resolveAttribute(R.attr.ic_drawer, typedValue, true);
		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawer,
				typedValue.resourceId, R.string.activity_home_drawer_open,
				R.string.activity_home_drawer_close)
		{

			/** Called when a drawer has settled in a completely closed state. */
			public void onDrawerClosed(View view)
			{
				getActionBar().setTitle(mTitle);
				invalidateOptionsMenu(); // creates call to
				// onPrepareOptionsMenu()
			}

			/** Called when a drawer has settled in a completely open state. */
			public void onDrawerOpened(View drawerView)
			{
				getActionBar().setTitle(mDrawerTitle);
				invalidateOptionsMenu(); // creates call to
				// onPrepareOptionsMenu()
			}
		};
		mDrawer.setDrawerListener(mDrawerToggle);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);
	}

	@Override
	public void onResume()
	{
		// This code assigns the current fragment
		SharedPreferences pref = PreferenceManager
				.getDefaultSharedPreferences(this);
		boolean isAncestral = false;
		isAncestral = pref.getBoolean("homenewinstance_switch", false);
		this.instantiateFragment(new HomeFragment(), false, isAncestral,
				"HomeFragment", android.R.anim.fade_in,
				android.R.anim.fade_out, null);
		// switch (Integer.parseInt(pref.getString(
		// "slidingmenuside_listpreference", "-1")))
		// {
		// case 0:
		// System.out.println("Setting slide menu to slide from the left.");
		// mDrawerView.setGravity(Gravity.LEFT);
		// break;
		// case 1:
		// System.out.println("Setting slide menu to slide from the right.");
		// mDrawerView.setGravity(Gravity.RIGHT);
		// break;
		// default:
		// System.out
		// .println("Setting slide menu to slide from the left as no setting has been defined.");
		// mDrawerView.setGravity(Gravity.LEFT);
		// break;
		//
		// }
		super.onResume();
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState)
	{
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig)
	{
		super.onConfigurationChanged(newConfig);
		mDrawerToggle.onConfigurationChanged(newConfig);
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

		// Pass the event to ActionBarDrawerToggle, if it returns
		// true, then it has handled the app icon touch event
		if (mDrawerToggle.onOptionsItemSelected(item))
		{
			return true;
		}

		// Handle your other action bar items...
		FragmentTransaction ft = this.getSupportFragmentManager()
				.beginTransaction();
		switch (item.getItemId())
		{
		case R.id.menu_dropdown:
			mDrawer.openDrawer(Gravity.LEFT);
			break;
		default:
			return super.onOptionsItemSelected(item);
		}
		return true;
	}

	public void onHomeMenuClicked(View v)
	{
		SharedPreferences pref = PreferenceManager
				.getDefaultSharedPreferences(this);
		boolean isAncestral = false;
		isAncestral = pref.getBoolean("homenewinstance_switch", false);
		if (!isAncestral)
		{
			if (!this
					.getSupportFragmentManager()
					.getBackStackEntryAt(
							this.getSupportFragmentManager()
									.getBackStackEntryCount() - 1).getName()
					.equals("HomeFragment"))
			{
				this.instantiateFragment(new HomeFragment(), true, isAncestral,
						"HomeFragment", R.anim.fragment_change_enter,
						R.anim.fragment_change_exit, null);
			}
		} else
		{
			try
			{
				this.getSupportFragmentManager()
						.popBackStack(
								this.getSupportFragmentManager()
										.getBackStackEntryAt(0).getId(),
								this.getSupportFragmentManager().POP_BACK_STACK_INCLUSIVE);
			} catch (NullPointerException e)
			{

			}
		}
		mDrawer.closeDrawer(Gravity.LEFT);
		System.out.println("Home has been pressed.");
	}

	public void onFeaturedMenuClicked(View v)
	{
		Intent intent = new Intent(this, LoginActivity.class);
		startActivity(intent);
		System.out.println("Featured has been pressed.");
	}

	public void onRestaurantsMenuClicked(View v)
	{
		// this.instantiateFragment(new RestaurantBrowserFragment(), true, true,
		// "RestaurantFragment", R.anim.fragment_change_enter,
		// R.anim.fragment_change_exit, null);
		mDrawer.closeDrawer(Gravity.LEFT);
		System.out.println("Restaurant has been pressed.");
	}

	public void onAccountsMenuClicked(View v)
	{
		this.instantiateFragment(new AccountFragment(), true, true,
				"AccountFragment", R.anim.fragment_change_enter,
				R.anim.fragment_change_exit, null);
		mDrawer.closeDrawer(Gravity.LEFT);
		System.out.println("Account has been pressed.");
	}

	public void onSettingsMenuClicked(View v)
	{
		System.out.println("Settings has been pressed");
		mDrawer.closeDrawer(Gravity.LEFT);
		Intent intent = new Intent(this, SettingsActivity.class);
		startActivity(intent);
	}

	public void onAboutMenuClicked(View v)
	{
		this.instantiateFragment(new AboutFragment(), true, true,
				"AboutFragment", R.anim.fragment_change_enter,
				R.anim.fragment_change_exit, null);
		mDrawer.closeDrawer(Gravity.LEFT);
		System.out.println("About has been pressed.");
	}

	public void onInformationClicked(View view)
	{
		System.out.println("Information panel has been pressed");
		mDrawer.closeDrawer(Gravity.LEFT);
		AboutDialogFragment aboutDialogFragment = new AboutDialogFragment();
		aboutDialogFragment.show(this.getSupportFragmentManager(),
				"AboutDialog");
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

	public void instantiateFragment(Fragment newFragment,
			boolean addToBackStack, boolean isAncestral, String tag,
			int introAnimation, int outroAnimation, Bundle arguments)
	{
		System.out.println("Instantiate Fragment Run!");
		boolean isCurrentFragment = false;

		try
		{
			// Check if the current fragment is the one being instantiated
			isCurrentFragment = this
					.getSupportFragmentManager()
					.getBackStackEntryAt(
							this.getSupportFragmentManager()
									.getBackStackEntryCount() - 1).getName()
					.equals(tag);
			if (!isCurrentFragment)
			{
				// If the fragment is ancestral then check for previous
				// instantiations of the fragment
				if (isAncestral)
				{
					// The current fragment is not the one being instantiated
					// Attempt to pop back to an instance of the fragment, if
					// there
					try
					{
						this.getSupportFragmentManager()
								.popBackStack(
										tag,
										this.getSupportFragmentManager().POP_BACK_STACK_INCLUSIVE);

						// Check if the requested fragment was in the BackStack
						// and
						// has been brought forward
						isCurrentFragment = this
								.getSupportFragmentManager()
								.getBackStackEntryAt(
										this.getSupportFragmentManager()
												.getBackStackEntryCount() - 1)
								.getName().equals(tag);

						if (isCurrentFragment)
						{
							System.out
									.println("The fragment was in the BackStack and has been navigated to.");
							return;
						}
					} catch (Exception e)
					{
						System.out.println("Error thrown on popping!");
					}
				}
			} else
			{
				System.out
						.println("The fragment is already the current one, doing nothing!");
				return;
			}
		} catch (NullPointerException e)
		{
			System.out.println("You're at the front page, instantiate away!");
		} catch (ArrayIndexOutOfBoundsException e)
		{
			System.out.println("You're at the front page, instantiate away!");
		}

		// Since the fragment does not exist in the backstack or
		// as the current fragment then instantiate it
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		ft.setCustomAnimations(introAnimation, outroAnimation, introAnimation,
				outroAnimation);
		newFragment.setArguments(arguments);
		ft.replace(R.id.activity_home_fragment_frame, newFragment);
		if (addToBackStack)
		{
			ft.addToBackStack(tag);
		}
		ft.commit();
	}
}
