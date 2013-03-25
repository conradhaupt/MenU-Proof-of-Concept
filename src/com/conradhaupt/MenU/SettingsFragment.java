package com.conradhaupt.MenU;

import android.os.Bundle;
import android.preference.PreferenceFragment;

public class SettingsFragment extends PreferenceFragment
{

	@SuppressWarnings("deprecation")
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		System.out.println("Settings fragment created!");
		super.onCreate(savedInstanceState);
		this.addPreferencesFromResource(R.xml.preferences);
	}

}
