package com.conradhaupt.MenU;

import java.util.List;

import com.conradhaupt.MenU.Core.AccountError;

import de.keyboardsurfer.android.widget.crouton.Configuration;
import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.NavUtils;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;

public class LoginActivity extends FragmentActivity implements OnClickListener
{

	public static int viewPagerPageCount = 2;
	public ViewPager mPager;
	public PagerAdapter mAdapter;
	private Button registerButton;
	private Button loginButton;
	private RegisterFragment registerFragment = null;
	private LoginFragment loginFragment = null;

	@Override
	protected void onCreate(Bundle savedInstanceState)
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
		setContentView(R.layout.activity_login);

		// Show the Up button in the action bar.
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setTitle("Login");

		// Assign button variables
		loginButton = (Button) this
				.findViewById(R.id.activity_login_login_button);
		registerButton = (Button) this
				.findViewById(R.id.activity_login_register_button);

		// Assign ViewPager variables
		mPager = (ViewPager) this.findViewById(R.id.activity_login_viewpager);
		mAdapter = new LoginPagerAdapter(this.getSupportFragmentManager());
		mPager.setAdapter(mAdapter);
		mPager.setOnPageChangeListener(new OnPageChangeListener()
		{

			@Override
			public void onPageSelected(int position)
			{
				switch (position)
				{
				case 0:
					getActionBar().setTitle("Login");
					break;
				case 1:
					getActionBar().setTitle("Register");
					break;
				}
			}

			@Override
			public void onPageScrolled(int position, float positionOffset,
					int positionOffsetPixels)
			{
				if (positionOffset != 0)
				{
					loginButton.setLayoutParams(new LinearLayout.LayoutParams(
							LayoutParams.WRAP_CONTENT,
							LayoutParams.WRAP_CONTENT,
							7f - (6 * positionOffset)));
					registerButton
							.setLayoutParams(new LinearLayout.LayoutParams(
									LayoutParams.WRAP_CONTENT,
									LayoutParams.WRAP_CONTENT,
									1f + (6 * positionOffset)));
				}
			}

			@Override
			public void onPageScrollStateChanged(int arg0)
			{

			}
		});

		// Assign OnClickListeners
		int[] ids = { R.id.activity_login_register_button,
				R.id.activity_login_login_button };
		for (int i = 0; i < ids.length; i++)
		{
			this.findViewById(ids[i]).setOnClickListener(this);
		}
	}

	@Override
	protected void onResume()
	{
		super.onResume();
		int pageVar = mPager.getCurrentItem();
		loginButton.setLayoutParams(new LinearLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT,
				7f - (6 * pageVar)));
		registerButton.setLayoutParams(new LinearLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT,
				1f + (6 * pageVar)));
	}

	@Override
	public void onBackPressed()
	{
		super.onBackPressed();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_login, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch (item.getItemId())
		{
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private class LoginPagerAdapter extends FragmentPagerAdapter
	{
		public LoginPagerAdapter(FragmentManager fm)
		{
			super(fm);
		}

		@Override
		public Fragment getItem(int position)
		{
			System.out.println("Getting item for position " + position);
			switch (position)
			{
			case 0:
				if (loginFragment != null)
				{
					return loginFragment;
				} else
				{
					loginFragment = new LoginFragment();
					return loginFragment;
				}
			case 1:
				if (registerFragment != null)
				{
					return registerFragment;
				} else
				{
					registerFragment = new RegisterFragment();
					return registerFragment;
				}
			}
			return null;
		}

		@Override
		public int getCount()
		{
			return viewPagerPageCount;
		}

	}

	public void loadError(AccountError error, String successfulMessage,
			boolean clickableClose)
	{
		if (error != null)
		{
			if (error.getState() == AccountError.STATE_UNACCEPTABLE)
			{
				try
				{
					List<String> temp = error.getErrorMessages(this);
					Object[] messages = temp.toArray();
					for (int i = 0; i < messages.length; i++)
					{
						System.out.println(messages[i].toString());
						Crouton crouton = Crouton.makeText(this,
								messages[i].toString(), Style.ALERT);
						crouton.show();
					}
				} catch (Exception e)
				{
					System.out.println(e);
				}
			} else
			{
				System.out.println(successfulMessage);
				Crouton crouton = Crouton.makeText(this, successfulMessage,
						Style.CONFIRM);
				Configuration.Builder configBuild = new Configuration.Builder();
				configBuild.setDuration(Configuration.DURATION_INFINITE);
				crouton.setConfiguration(configBuild.build());
				crouton.setOnClickListener(new OnClickListener()
				{

					@Override
					public void onClick(View v)
					{
						System.out.println("Crouton clicked");
						((LoginActivity) v.getContext()).onBackPressed();
					}
				});
				crouton.show();
			}
		}
	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
		case R.id.activity_login_register_button:
			if (mPager.getCurrentItem() == 1)
			{
				// If the user is on the register fragment then register
				registerFragment.register();
			} else
			{
				// If the user is not on the register fragment then move to it
				mPager.setCurrentItem(1);
			}
			break;
		case R.id.activity_login_login_button:
			if (mPager.getCurrentItem() == 0)
			{
				// If the user is on the login fragment then login
				loginFragment.login();
			} else
			{
				// If the user is not on the login fragment then move to it
				mPager.setCurrentItem(0);
			}
			break;
		default:
			break;
		}
	}
}
