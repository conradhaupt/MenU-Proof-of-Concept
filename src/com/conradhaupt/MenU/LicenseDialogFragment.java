package com.conradhaupt.MenU;

import com.conradhaupt.MenU.R;
import android.annotation.SuppressLint;
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
	private String licenseText;
	private String licenseTitle;
	private String licenseCopyright;
	private String licenseWebsiteLink;
	private int licenseBackgroundDrawable = -1;
	private int licenseDrawable = -1;

	public void create(String licenseTitle, String licenseText,
			String licenseCopyright, String licenseWebsiteLink,
			int licenseBackgroundDrawableID, int licenseDrawableID,
			FragmentManager fragmentManager, String tag)
	{
		this.licenseText = licenseText;
		this.licenseTitle = licenseTitle;
		this.licenseCopyright = licenseCopyright;
		this.licenseWebsiteLink = licenseWebsiteLink;
		this.licenseBackgroundDrawable = licenseBackgroundDrawableID;
		this.licenseDrawable = licenseDrawableID;

		this.show(fragmentManager, tag);
	}

	public void onResume()
	{
		super.onResume();
		System.out.println("Adjusting header");
		RelativeLayout header = (RelativeLayout) this.getDialog().findViewById(
				R.id.license_dialog_header_background);
		header.setBackgroundResource(licenseBackgroundDrawable);

		System.out.println("Adjusting header image");
		ImageView headerImage = (ImageView) this.getDialog().findViewById(
				R.id.license_dialog_header_image);
		headerImage.setImageResource(licenseDrawable);

		System.out.println("Adjusting title");
		TextView title = (TextView) this.getDialog().findViewById(
				R.id.license_dialog_title);
		title.setText(licenseTitle);

		System.out.println("Adjusting copyright");
		TextView copyright = (TextView) this.getDialog().findViewById(
				R.id.license_dialog_copyright);
		copyright.setText(licenseCopyright);

		System.out.println("Adjusting text");
		TextView text = (TextView) this.getDialog().findViewById(
				R.id.license_dialog_text);
		text.setText(licenseText);
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
		Uri uri = Uri.parse(this.licenseWebsiteLink);
		Intent intent = new Intent(Intent.ACTION_VIEW, uri);
		startActivity(intent);
	}
}