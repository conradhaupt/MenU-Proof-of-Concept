package com.conradhaupt.MenU;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.conradhaupt.MenU.Core.Account;
import com.conradhaupt.MenU.Core.AccountError;
import com.conradhaupt.MenU.Core.MenUServerInteraction;

public class LoginFragment extends Fragment
{

	private EditText usernameEditText;
	private EditText passwordEditText;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		System.out.println("Login fragment created!");
		// Inflate the layout for this fragment
		setHasOptionsMenu(true);
		return inflater.inflate(R.layout.fragment_login, container, false);
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

	}

	private void loadError(AccountError result)
	{
		try
		{
			if (result.getState() == AccountError.STATE_UNACCEPTABLE)
			{
				System.out.println("Login fragment recieved errors");
			} else
			{
				System.out.println("Login fragment recieved no errors");
				usernameEditText.setText("");
				passwordEditText.setText("");
			}
			((LoginActivity) (this.getActivity())).loadError(
					result,
					this.getResources().getString(
							R.string.account_verification_login_successful),
					true);
			if (result.getAccessCode() != null)
			{
				System.out.println("Account access code is: "
						+ result.getAccessCode());
			} else
			{
				System.out
						.println("No access code was given, this isn't meant to happen :(");
			}
		} catch (Exception e)
		{
			System.out
					.println("Error calling loadError on LoginActivity, is this fragment in a LoginActivity?");
		}
	}

	public void login()
	{
		System.out.println("Logging in");
		Account account = new Account();

		// Assign values to account object
		account.setUsername(this.usernameEditText.getText().toString());
		account.setPassword(this.passwordEditText.getText().toString());

		// Launch account connection
		new AsyncTask<Account, AccountError, AccountError>()
		{
			@Override
			protected AccountError doInBackground(Account... account)
			{
				try
				{
					AccountError error;
					error = MenUServerInteraction.loginAccount(account[0]);
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
