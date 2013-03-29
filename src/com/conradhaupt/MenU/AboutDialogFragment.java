package com.conradhaupt.MenU;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
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
			break;
		case R.id.aboutMenU_dialog_termsandconditions:
			System.out.println("Navigating to app terms and conditions!");
			break;
		case R.id.aboutMenU_dialog_slidingmenulicense:
			System.out.println("Navigating to slidingmenu library license!");
			break;
		case R.id.aboutMenU_dialog_appmsgcroutonlicense:
			System.out.println("Navigating to appmsg crouton library license!");
			break;
		default:
			break;
		}
	}
}