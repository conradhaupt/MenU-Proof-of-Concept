package com.conradhaupt.MenU;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;

public class AboutDialogFragment extends DialogFragment implements
		OnClickListener
{
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState)
	{
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		// Get the layout inflater
		LayoutInflater inflater = getActivity().getLayoutInflater();

		// Inflate and set the layout for the dialog
		// Pass null as the parent view because its going in the dialog layout
		builder.setView(inflater.inflate(R.layout.dialog_aboutmenu, null))
		// Add action buttons
				.setNegativeButton(R.string.aboutMenU_dialog_close,
						new DialogInterface.OnClickListener()
						{
							public void onClick(DialogInterface dialog, int id)
							{
								AboutDialogFragment.this.getDialog().cancel();
							}
						});
		return builder.create();
	}

	public void onResume()
	{
		int[] options = { R.id.aboutMenU_dialog_website,
				R.id.aboutMenU_dialog_license,
				R.id.aboutMenU_dialog_termsandconditions,
				R.id.aboutMenU_dialog_slidingmenulicense,
				R.id.aboutMenU_dialog_appmsgcroutonlicense };
		for (int i = 0; i < options.length; i++)
		{
			this.getDialog().findViewById(options[i])
					.setOnClickListener(AboutDialogFragment.this);
		}
		super.onResume();
	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
		case R.id.aboutMenU_dialog_website:
			System.out.println("Navigating to app website!");
			break;
		case R.id.aboutMenU_dialog_license:
			System.out.println("Navigating to app license!");
			new LicenseDialogFragment().create(
					R.string.aboutMenU_dialog_license,
					R.string.aboutMenU_dialog_license_text,
					R.string.aboutMenU_dialog_license_copyright,
					R.string.aboutMenU_dialog_website_link,
					R.color.MenU_Red_Light, R.color.MenU_Red_Dark,
					R.drawable.ic_logo_monochrome_dark, this.getActivity()
							.getSupportFragmentManager(),
					"LicenseDialogFragment");
			break;
		case R.id.aboutMenU_dialog_termsandconditions:
			System.out.println("Navigating to app terms and conditions!");
			new LicenseDialogFragment().create(
					R.string.aboutMenU_dialog_license_termsandconditions,
					R.string.aboutMenU_dialog_license_termsandconditions_text,
					R.string.aboutMenU_dialog_license_copyright,
					R.string.aboutMenU_dialog_website_link,
					R.color.MenU_Turquoise_Light, R.color.MenU_Turquoise_Dark,
					R.drawable.ic_action_settings_holo_dark, this.getActivity()
							.getSupportFragmentManager(),
					"LicenseDialogFragment");
			break;
		case R.id.aboutMenU_dialog_slidingmenulicense:
			System.out.println("Navigating to slidingmenu library license!");
			new LicenseDialogFragment().create(
					R.string.aboutMenU_dialog_license_slidingmenu,
					R.string.ApacheLicense,
					R.string.aboutMenU_dialog_license_slidingmenu_copyright,
					R.string.ApacheLicenseURL, R.color.MenU_Peach_Light,
					R.color.MenU_Peach_Dark,
					R.drawable.ic_action_settings_holo_dark, this.getActivity()
							.getSupportFragmentManager(),
					"Slidingmenu LicenseDialogFragment");
			break;
		case R.id.aboutMenU_dialog_appmsgcroutonlicense:
			System.out.println("Navigating to appmsg crouton library license!");
			new LicenseDialogFragment().create(
					R.string.aboutMenU_dialog_license_crouton,
					R.string.ApacheLicense,
					R.string.aboutMenU_dialog_license_crouton_copyright,
					R.string.ApacheLicenseURL, R.color.MenU_Green_Light,
					R.color.MenU_Green_Dark,
					R.drawable.ic_action_settings_holo_dark, this.getActivity()
							.getSupportFragmentManager(),
					"Crouton LicenseDialogFragment");
			break;
		default:
			break;
		}
	}
}