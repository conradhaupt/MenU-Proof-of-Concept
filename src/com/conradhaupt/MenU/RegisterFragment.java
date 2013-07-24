package com.conradhaupt.MenU;

import java.util.List;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;

import com.conradhaupt.MenU.Core.Account;
import com.conradhaupt.MenU.Core.AccountError;
import com.conradhaupt.MenU.Core.MenUServerInteraction;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;

public class RegisterFragment extends Fragment implements OnClickListener
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
		// Assign OnClickListeners
		int[] ids = { R.id.activity_login_register_button };
		for (int i = 0; i < ids.length; i++)
		{
			this.getActivity().findViewById(ids[i]).setOnClickListener(this);
		}

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

	public void loadError(AccountError error)
	{
		if (error != null)
		{
			if (error.getState() == AccountError.STATE_UNACCEPTABLE)
			{
				try
				{
					List<String> temp = error.getErrorMessages();
					Object[] messages = temp.toArray();
					for (int i = 0; i < messages.length; i++)
					{
						System.out.println(messages[i].toString());
						Crouton.makeText(this.getActivity(),
								messages[i].toString(), Style.ALERT).show();
					}
				} catch (Exception e)
				{
					System.out.println(e);
				}
			} else
			{
				System.out.println("Account creation was successful");
				Crouton.makeText(this.getActivity(),
						R.string.account_verification_successful, Style.CONFIRM)
						.show();
			}
		}
	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
		case R.id.activity_login_register_button:
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
				System.out.println(passwordEditText.getText().toString()
						+ " VS " + passwordRepeatEditText.getText().toString());
				return;
			}
			account.setFirstName(firstnameEditText.getText().toString());
			account.setLastName(lastnameEditText.getText().toString());
			account.setEmail(emailEditText.getText().toString());

			final Context context = this.getActivity();
			// Launch account connection
			new AsyncTask<Account, AccountError, AccountError>()
			{
				@Override
				protected AccountError doInBackground(Account... account)
				{
					try
					{
						AccountError error = Account.validate(account[0],
								context);
						if (error.getState() == AccountError.STATE_ACCEPTABLE)
						{
							// No errors, submitting account for registration
							error = MenUServerInteraction.registerAccount(
									account[0], context);
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
				};
			}.execute(account);
			break;
		default:
			System.out
					.println("That hasn't been assigned any code here, it is a worthless OnClickReciever.");
			break;
		}
	}
}
