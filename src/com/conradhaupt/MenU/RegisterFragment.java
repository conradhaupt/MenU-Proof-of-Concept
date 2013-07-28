package com.conradhaupt.MenU;

import android.content.Context;
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

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;

public class RegisterFragment extends Fragment
{

	private EditText usernameEditText;
	private EditText passwordEditText;
	private EditText passwordRepeatEditText;
	private EditText firstnameEditText;
	private EditText lastnameEditText;
	private EditText emailEditText;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		System.out.println("Register fragment created!");
		// Inflate the layout for this fragment
		setHasOptionsMenu(true);
		return inflater.inflate(R.layout.fragment_register, container, false);
	}

	@Override
	public void onResume()
	{
		super.onResume();

		// Retrieve editTexts
		usernameEditText = (EditText) this.getActivity().findViewById(
				R.id.fragment_login_register_textview_username);
		passwordEditText = (EditText) this.getActivity().findViewById(
				R.id.fragment_login_register_textview_password);
		passwordRepeatEditText = (EditText) this.getActivity().findViewById(
				R.id.fragment_login_register_textview_password_repeat);
		firstnameEditText = (EditText) this.getActivity().findViewById(
				R.id.fragment_login_register_textview_firstname);
		lastnameEditText = (EditText) this.getActivity().findViewById(
				R.id.fragment_login_register_textview_lastname);
		emailEditText = (EditText) this.getActivity().findViewById(
				R.id.fragment_login_register_textview_email);
	}

	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		inflater.inflate(R.menu.fragment_register, menu);
	}

	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch (item.getItemId())
		{
		default:
		}
		return true;
	}

	private void loadError(AccountError result)
	{
		try
		{
			if (result.getState() == AccountError.STATE_UNACCEPTABLE)
			{
				System.out.println("Register fragment recieved errors");
			} else
			{
				System.out.println("Register fragment recieved no errors");
				usernameEditText.setText("");
				passwordEditText.setText("");
				passwordRepeatEditText.setText("");
				firstnameEditText.setText("");
				lastnameEditText.setText("");
				emailEditText.setText("");
				((LoginActivity) (this.getActivity())).mPager.setCurrentItem(0);
			}
			((LoginActivity) this.getActivity()).loadError(
					result,
					this.getResources().getString(
							R.string.account_verification_successful),false);
		} catch (Exception e)
		{
			System.out
					.println("Error calling loadError on LoginActivity, is this fragment in a LoginActivity?");
		}
	};

	public void register()
	{
		System.out.println("Register button clicked.");
		Account account = new Account();
		account.setUsername(usernameEditText.getText().toString());
		if (passwordEditText.getText().toString()
				.equals(passwordRepeatEditText.getText().toString()))
		{
			account.setPassword(passwordEditText.getText().toString());
		} else
		{
			Crouton crouton = Crouton.makeText(this.getActivity(),
					"Passwords are not the same", Style.ALERT);
			crouton.show();
			System.out.println(passwordEditText.getText().toString() + " VS "
					+ passwordRepeatEditText.getText().toString());
			return;
		}
		account.setFirstName(firstnameEditText.getText().toString());
		account.setLastName(lastnameEditText.getText().toString());
		account.setEmail(emailEditText.getText().toString());

		// Launch account connection
		new AsyncTask<Account, AccountError, AccountError>()
		{
			@Override
			protected AccountError doInBackground(Account... account)
			{
				try
				{
					AccountError error = Account.validate(account[0]);
					if (error.getState() == AccountError.STATE_ACCEPTABLE)
					{
						// No errors, submitting account for registration
						error = MenUServerInteraction
								.registerAccount(account[0]);
					} else
					{
						// Error retrieved, displaying the errors
						System.out.println("Account verification error: "
								+ error.getStringErrors());
					}
					return error;
				} catch (Exception e)
				{
					System.out.println("Account submission error:");
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
