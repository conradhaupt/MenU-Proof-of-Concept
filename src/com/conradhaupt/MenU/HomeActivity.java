package com.conradhaupt.MenU;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;

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
		switch (item.getItemId())
		{
		case R.id.menu_search:
			FragmentTransaction ft = this.getSupportFragmentManager()
					.beginTransaction();
			ft.setCustomAnimations(R.anim.fragment_change_enter,
					R.anim.fragment_change_exit);
			ft.replace(R.id.fragment_frame, new RestaurantFragment());
			ft.addToBackStack(null);
			ft.commit();
			break;
		case android.R.id.home:
			getSlidingMenu().showMenu();
			// FragmentTransaction ft1 = this.getSupportFragmentManager()
			// .beginTransaction();
			// ft1.setCustomAnimations(R.anim.fragment_change_enter,
			// R.anim.fragment_change_exit);
			// ft1.replace(R.id.fragment_frame, new HomeFragment());
			// ft1.addToBackStack(null);
			// ft1.commit();
			break;
		case R.id.menu_drop_about:
			FragmentTransaction ft11 = this.getSupportFragmentManager()
					.beginTransaction();
			ft11.setCustomAnimations(R.anim.fragment_change_enter,
					R.anim.fragment_change_exit);
			ft11.replace(R.id.fragment_frame, new AboutFragment());
			ft11.addToBackStack(null);
			ft11.commit();
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
}
