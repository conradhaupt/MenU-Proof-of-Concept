package com.conradhaupt.MenU;

import java.util.Arrays;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class AboutDialogFragment extends DialogFragment implements
		OnItemClickListener
{
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState)
	{
		System.out.println("");
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

	@Override
	public void onResume()
	{
		super.onResume();

		// Assign values to layout
		// Populate listview
		ListView list = (ListView) this.getDialog().findViewById(
				R.id.license_dialog_listview);
		System.out.println("Setting adapter");
		String[] temp = this.getResources().getStringArray(
				R.array.licenseDialog_licenses);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_list_item_1, temp);
		System.out.println(Arrays.toString(temp));
		list.setAdapter(adapter);
		System.out.println("Setting OnItemClickListener");
		list.setOnItemClickListener(this);
		System.out.println("OnCreateView");
	}

	@Override
	public void onItemClick(AdapterView<?> adapterView, View view,
			int position, long id)
	{
		if (position == 0)
		{
			Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(this
					.getResources().getStringArray(
							R.array.licenseDialog_license_website)[0]));
			this.startActivity(intent);
			System.out.println("Navigating to app website!");
		} else
		{
			SharedPreferences pref = this.getActivity().getPreferences(
					Context.MODE_PRIVATE);
			String[] titles = this.getResources().getStringArray(
					R.array.licenseDialog_licenses);
			String[] copyrights = this.getResources().getStringArray(
					R.array.licenseDialog_license_copyrights);
			String[] texts = this.getResources().getStringArray(
					R.array.licenseDialog_license);
			String[] websites = this.getResources().getStringArray(
					R.array.licenseDialog_license_website);
			TypedArray headerDrawables = this.getResources().obtainTypedArray(
					R.array.licenseDialog_license_header_drawable);
			int drawableID = headerDrawables.getResourceId(position, -1);
			if (drawableID != -1)
			{
				System.out.println(position);
				new LicenseDialogFragment().create(titles[position],
						texts[position], copyrights[position],
						websites[position], drawableID,
						R.drawable.ic_logo_monochrome_dark,
						this.getFragmentManager(), position + "");
			}
			headerDrawables.recycle();
		}
	}
}