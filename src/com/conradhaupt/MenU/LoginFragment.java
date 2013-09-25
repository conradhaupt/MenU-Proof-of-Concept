package com.conradhaupt.MenU;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.conradhaupt.MenU.Core.Account;
import com.conradhaupt.MenU.Core.AccountError;
import com.conradhaupt.MenU.Core.MenUServerInteraction;

public class LoginFragment extends Fragment
{

	private EditText usernameEditText;
	private EditText passwordEditText;
	private LinearLayout progressLayout;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		System.out.println("Login fragment created!");
		// Inflate the layout for this fragment
		setHasOptionsMenu(true);
		return inflater.inflate(R.layout.fragment_login, container, false);
	}

	public void checkAccountLoggedIn()
	{
		// Check if an account is already logged in
		SharedPreferences pref = this.getActivity().getSharedPreferences(
				"accountdata", 0);
		if (pref.getBoolean("accountLoggedIn", false))
		{

			usernameEditText.setEnabled(false);
			passwordEditText.setEnabled(false);
		}
	}

	@Override
	public void onResume()
	{
		super.onResume();

		// Retrieve editTexts
		usernameEditText = (EditText) this.getActivity().findViewById(
				R.id.fragment_login_login_textview_username);
		passwordEditText = (EditText) this.getActivity().findViewById(
				R.id.fragment_login_login_textview_password);
		progressLayout = (LinearLayout) this.getActivity().findViewById(
				R.id.fragment_login_login_linearlayout_progress);
		progressLayout.setVisibility(View.INVISIBLE);

		checkAccountLoggedIn();
	}

	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		inflater.inflate(R.menu.fragment_login, menu);
	}

	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch (item.getItemId())
		{
		default:
		}
		return true;
	}

	private void saveAccountInformation(String accessCode, String username)
	{
		SharedPreferences pref = this.getActivity().getSharedPreferences(
				"accountdata", 0);
		SharedPreferences.Editor prefEdit = pref.edit();
		System.out.println("Replacing the following account information:");
		System.out.println("Account username: "
				+ pref.getString("accountUsername",
						"Username preference doesn\'t exist"));
		System.out.println("Account access code: "
				+ pref.getString("accountAccessCode",
						"Access code preference doesn\'t exist"));
		System.out.println("Account logged in: "
				+ pref.getBoolean("accountLoggedIn", false));
		prefEdit.putString("accountUsername", username);
		prefEdit.putString("accountAccessCode", accessCode);
		prefEdit.putBoolean("accountLoggedIn", true);
		prefEdit.commit();
		System.out.println("with this information:");
		System.out.println("Account username: "
				+ pref.getString("accountUsername", "DEFAULT_USERNAME"));
		System.out.println("Account access code: "
				+ pref.getString("accountAccessCode", "DEFAULT_ACCESS_CODE"));
		System.out.println("Account logged in: "
				+ pref.getBoolean("accountLoggedIn", false));
	}

	private void loadError(AccountError result)
	{
		// Set progresslayout as invisible
		progressLayout.setVisibility(View.INVISIBLE);

		try
		{
			if (result.getState() == AccountError.STATE_UNACCEPTABLE)
			{
				System.out.println("Login fragment recieved errors");
			} else
			{
				System.out.println("Login fragment recieved no errors");

				// Register account information into preferences
				this.saveAccountInformation(result.getAccessCode(),
						usernameEditText.getText().toString());

				// Reset editText values
				usernameEditText.setText("");
				passwordEditText.setText("");
			}
			((LoginActivity) (this.getActivity())).loadError(
					result,
					this.getResources().getString(
							R.string.account_verification_login_successful),
					true);
		} catch (Exception e)
		{
			System.out
					.println("Error calling loadError on LoginActivity, is this fragment in a LoginActivity?");
		}
		// Unfreeze editTexts
		usernameEditText.setEnabled(true);
		passwordEditText.setEnabled(true);
	}

	public void login()
	{
		System.out.println("Logging in");
		Account account = new Account();

		// Freeze editTexts
		usernameEditText.setEnabled(false);
		passwordEditText.setEnabled(false);

		// Assign values to account object
		account.setUsername(this.usernameEditText.getText().toString());
		account.setPassword(this.passwordEditText.getText().toString());

		// Set progresslayout as visible
		progressLayout.setVisibility(View.VISIBLE);

		final Context context = this.getActivity();
		// Launch account connection
		new AsyncTask<Account, AccountError, AccountError>()
		{
			@Override
			protected AccountError doInBackground(Account... account)
			{
				try
				{
					AccountError error;
					error = MenUServerInteraction.AccountInteraction.loginAccount(account[0],
							context);
					return error;
				} catch (Exception e)
				{
					System.out.println("Account login error:");
					System.out.println(e);
				}
				return null;
			}

			protected void onPostExecute(AccountError result)
			{
				loadError(result);
			}

		}.execute(account);
	}
}
