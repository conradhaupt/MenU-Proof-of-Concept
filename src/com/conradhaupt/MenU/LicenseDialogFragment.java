package com.conradhaupt.MenU;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class LicenseDialogFragment extends DialogFragment
{
	private int licenseString = -1;
	private int licenseTitle = -1;
	private int licenseCopyright = -1;
	private int licenseWebsiteLink = -1;
	private int licenseColourPrimary = -1;
	private int licenseColourSecondary = -1;
	private int licenseDrawable = -1;
	private String websiteURL;

	public void create(int licenseTitleID, int licenseStringID,
			int licenseCopyrightID, int licenseWebsiteLink,
			int licenseColourPrimaryID, int licenseColourSecondaryID,
			int licenseDrawableID, FragmentManager fragmentManager, String tag)
	{
		this.licenseTitle = licenseTitleID;
		this.licenseString = licenseStringID;
		this.licenseCopyright = licenseCopyrightID;
		this.licenseWebsiteLink = licenseWebsiteLink;
		this.licenseColourPrimary = licenseColourPrimaryID;
		this.licenseColourSecondary = licenseColourSecondaryID;
		this.licenseDrawable = licenseDrawableID;

		this.show(fragmentManager, tag);
	}

	public void onResume()
	{
		RelativeLayout relativeLayout2 = (RelativeLayout) this.getDialog()
				.findViewById(R.id.license_dialog_header_background);
		relativeLayout2.setBackgroundResource(this.licenseColourPrimary);
		RelativeLayout relativeLayout = (RelativeLayout) this.getDialog()
				.findViewById(R.id.license_dialog_header_bar);
		relativeLayout.setBackgroundResource(this.licenseColourSecondary);
		ImageView image = (ImageView) this.getDialog().findViewById(
				R.id.license_dialog_drawable);
		image.setImageResource(this.licenseDrawable);
		TextView text = (TextView) this.getDialog().findViewById(
				R.id.license_dialog_textView);
		text.setText(this.licenseString);
		TextView text2 = (TextView) this.getDialog().findViewById(
				R.id.license_dialog_copyright);
		text2.setText(this.licenseCopyright);
		TextView text3 = (TextView) this.getDialog().findViewById(
				R.id.license_dialog_title);
		text3.setText(this.licenseTitle);
		this.websiteURL = this.getString(this.licenseWebsiteLink);
		super.onResume();
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState)
	{
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		// Get the layout inflater
		LayoutInflater inflater = getActivity().getLayoutInflater();

		// Inflate and set the layout for the dialog
		// Pass null as the parent view because its going in the dialog layout
		builder.setView(inflater.inflate(R.layout.dialog_license, null))
				// Add action buttons
				.setNegativeButton(R.string.aboutMenU_dialog_license_close,
						new DialogInterface.OnClickListener()
						{
							public void onClick(DialogInterface dialog, int id)
							{
								LicenseDialogFragment.this.getDialog().cancel();
							}
						})
				.setPositiveButton(R.string.aboutMenU_dialog_website,
						new DialogInterface.OnClickListener()
						{
							public void onClick(DialogInterface dialog, int id)
							{
								LicenseDialogFragment.this.navigateToWebsite();
							}
						});
		;

		return builder.create();
	}

	public void navigateToWebsite()
	{
		Uri uri = Uri.parse(this.websiteURL);
		Intent intent = new Intent(Intent.ACTION_VIEW, uri);
		startActivity(intent);
	}
}