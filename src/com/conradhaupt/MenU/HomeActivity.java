package com.conradhaupt.MenU;

import java.util.Arrays;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.DrawerLayout.DrawerListener;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.conradhaupt.MenU.Core.HomeActivityMenuAdapter;
import com.github.espiandev.showcaseview.ShowcaseView;

public class HomeActivity extends FragmentActivity implements
		OnItemClickListener
{

	private DrawerLayout mDrawer;
	private ListView mDrawerView;
	private ActionBarDrawerToggle mDrawerToggle;
	private CharSequence mDrawerTitle;
	private CharSequence mTitle;
	public static int fragmentNewIntroAnimation = R.anim.fragment_new_enter;
	public static int fragmentNewOutroAnimation = R.anim.fragment_new_exit;
	public static int fragmentBackIntroAnimation = R.anim.fragment_back_enter;
	public static int fragmentBackOutroAnimation = R.anim.fragment_back_exit;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		SharedPreferences pref = PreferenceManager
				.getDefaultSharedPreferences(this);

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
		mDrawerView = (ListView) this
				.findViewById(R.id.activity_home_navigation_drawer);
		mDrawer = (DrawerLayout) this.findViewById(R.id.activity_home_drawer);

		// This assigns the sliding drawer shadow
		mDrawer.setDrawerShadow(R.drawable.activity_home_drawer_shadow,
				Gravity.LEFT);

		// This code assigns the sliding menu parameters
		if (!pref.getBoolean("smallslidingmenu_checkbox", false))
		{
			LayoutParams param = (LayoutParams) mDrawerView.getLayoutParams();
			param.width = (int) this.getResources().getDimension(
					R.dimen.activity_home_drawer_width_large);
			mDrawerView.setLayoutParams(param);
		} else
		{
			LayoutParams param = (LayoutParams) mDrawerView.getLayoutParams();
			param.width = (int) this.getResources().getDimension(
					R.dimen.activity_home_drawer_width_small);
			mDrawerView.setLayoutParams(param);
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
				"HomeFragment", HomeActivity.fragmentNewIntroAnimation,
				HomeActivity.fragmentNewOutroAnimation,
				HomeActivity.fragmentNewIntroAnimation,
				HomeActivity.fragmentBackOutroAnimation, null);

		SharedPreferences pref2 = PreferenceManager
				.getDefaultSharedPreferences(this);
		// This code gets the App theme
		int themeValue = Integer.parseInt(pref.getString(
				"theme_listpreference", "-1"));
		// This code gets the menu size
		boolean menuSmall = pref.getBoolean("smallslidingmenu_checkbox", false);

		// This code retreaves all menu values from the resources
		String[] titles = this.getResources().getStringArray(
				R.array.home_activity_slidingmenu);
		System.out.println("Instantiating menu with titles "
				+ Arrays.toString(titles));

		// This code propogates the listview
		mDrawerView
				.setAdapter(new HomeActivityMenuAdapter(
						this,
						menuSmall ? R.layout.list_menu_small
								: R.layout.list_menu_large,
						titles,
						themeValue == 1 ? R.array.home_activity_slidingmenu_drawables_holo_dark
								: R.array.home_activity_slidingmenu_drawables_holo_light,
						menuSmall));
		mDrawerView.setOnItemClickListener(this);

		// Process userguides
		this.userGuide();
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

		switch (item.getItemId())
		{
		default:
			return super.onOptionsItemSelected(item);
		}
		// return true;
	}

	public void onHomeMenuClicked()
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
						"HomeFragment", HomeActivity.fragmentNewIntroAnimation,
						HomeActivity.fragmentNewOutroAnimation,
						HomeActivity.fragmentNewIntroAnimation,
						HomeActivity.fragmentBackOutroAnimation, null);
			}
		} else
		{
			try
			{
				this.getSupportFragmentManager();
				this.getSupportFragmentManager().popBackStack(
						this.getSupportFragmentManager().getBackStackEntryAt(0)
								.getId(),
						FragmentManager.POP_BACK_STACK_INCLUSIVE);
			} catch (NullPointerException e)
			{

			}
		}
		mDrawer.closeDrawer(Gravity.LEFT);
		System.out.println("Home has been pressed.");
	}

	public void onFeaturedMenuClicked()
	{
		Toast toast = Toast.makeText(this, "That doesn't work right now",
				Toast.LENGTH_LONG);
		toast.show();
		System.out.println("Featured has been pressed.");
	}

	public void onRestaurantsMenuClicked()
	{
		this.instantiateFragment(new RestaurantBrowserFragment(), true, true,
				"RestaurantBrowserFragment",
				HomeActivity.fragmentNewIntroAnimation,
				HomeActivity.fragmentNewOutroAnimation,
				HomeActivity.fragmentNewIntroAnimation,
				HomeActivity.fragmentBackOutroAnimation, null);
		mDrawer.closeDrawer(Gravity.LEFT);
		System.out.println("Restaurant has been pressed.");
	}

	public void onAccountsMenuClicked()
	{

		Intent intent = new Intent(this, LoginActivity.class);
		startActivity(intent);
		System.out.println("Account has been pressed.");
	}

	public void onSettingsMenuClicked()
	{
		System.out.println("Settings has been pressed");
		mDrawer.closeDrawer(Gravity.LEFT);
		Intent intent = new Intent(this, SettingsActivity.class);
		startActivity(intent);
	}

	public void onAboutMenuClicked()
	{
		this.instantiateFragment(new AboutFragment(), true, true,
				"AboutFragment", HomeActivity.fragmentNewIntroAnimation,
				HomeActivity.fragmentNewOutroAnimation,
				HomeActivity.fragmentNewIntroAnimation,
				HomeActivity.fragmentBackOutroAnimation, null);
		mDrawer.closeDrawer(Gravity.LEFT);
		System.out.println("About has been pressed.");
	}

	public void onInformationClicked()
	{
		System.out.println("Information panel has been pressed");
		mDrawer.closeDrawer(Gravity.LEFT);
		AboutDialogFragment aboutDialogFragment = new AboutDialogFragment();
		aboutDialogFragment.show(this.getSupportFragmentManager(),
				"AboutDialog");
	}

	public void instantiateFragment(Fragment newFragment,
			boolean addToBackStack, boolean isAncestral, String tag,
			int introAnimation, int outroAnimation, int backIntroAnimation,
			int backOutroAnimation, Bundle arguments)
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
		ft.setCustomAnimations(introAnimation, outroAnimation,
				backIntroAnimation, backOutroAnimation);
		newFragment.setArguments(arguments);
		ft.replace(R.id.activity_home_fragment_frame, newFragment);
		if (addToBackStack)
		{
			ft.addToBackStack(tag);
		}
		ft.commit();
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id)
	{
		String[] titles = this.getResources().getStringArray(
				R.array.home_activity_slidingmenu);
		System.out.println("Navigating with name " + titles[position]);
		try
		{

			if (titles[position].equals(this.getResources().getString(
					R.string.slidingmenu_home)))
			{
				// Go to home
				this.onHomeMenuClicked();
			} else
			{
				if (titles[position].equals(this.getResources().getString(
						R.string.slidingmenu_featured)))
				{
					// Go to featured
					this.onFeaturedMenuClicked();
				} else
				{
					if (titles[position].equals(this.getResources().getString(
							R.string.slidingmenu_restaurants)))
					{
						// Go to restaurants
						this.onRestaurantsMenuClicked();
					} else
					{
						if (titles[position].equals(this.getResources()
								.getString(R.string.slidingmenu_accounts)))
						{
							// Go to accounts
							this.onAccountsMenuClicked();
						} else
						{
							if (titles[position].equals(this.getResources()
									.getString(R.string.slidingmenu_settings)))
							{
								// Go to settings
								this.onSettingsMenuClicked();
							} else
							{
								if (titles[position].equals(this.getResources()
										.getString(R.string.slidingmenu_about)))
								{
									// Go to about
									this.onAboutMenuClicked();
								} else
								{
									if (titles[position]
											.equals(this
													.getResources()
													.getString(
															R.string.slidingmenu_legal)))
									{
										// Go to legal
										this.onInformationClicked();
									} else
									{

									}

								}
							}
						}
					}
				}

			}
		} catch (Exception e)
		{

		}
	}

	public void userGuide()
	{
		SharedPreferences pref = PreferenceManager
				.getDefaultSharedPreferences(this);
		ShowcaseView.ConfigOptions co = new ShowcaseView.ConfigOptions();
		co.hideOnClickOutside = true;
		if (pref.getBoolean("userguide_drawer", false))
		{
			// the userguide must be shown
			ShowcaseView sv = ShowcaseView
					.insertShowcaseView(
							android.R.id.home,
							this,
							"Navigation Drawer",
							"Press this to open the navigation drawer. You can access everypart of the application from this drawer.",
							co);
			sv.show();
		}
		SharedPreferences.Editor prefEdit = pref.edit();
		prefEdit.putBoolean("userguide_drawer", false);
		prefEdit.commit();
	}
}
