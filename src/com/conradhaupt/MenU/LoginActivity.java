package com.conradhaupt.MenU;

import java.util.Arrays;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

import com.conradhaupt.MenU.Core.Account;
import com.conradhaupt.MenU.Core.AccountValidationError;
import com.conradhaupt.MenU.Core.MenUServerInteraction;

public class LoginActivity extends Activity implements OnClickListener
{

	private EditText usernameEditText;
	private EditText passwordEditText;
	private EditText passwordRepeatEditText;
	private EditText firstnameEditText;
	private EditText lastnameEditText;
	private EditText emailEditText;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login_register);

		// Show the Up button in the action bar.
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setDisplayShowTitleEnabled(false);

		// Assign OnClickListeners
		int[] ids = { R.id.activity_login_register_button_submit };
		for (int i = 0; i < ids.length; i++)
		{
			this.findViewById(ids[i]).setOnClickListener(this);
		}

		// Retrieve editTexts
		usernameEditText = (EditText) this
				.findViewById(R.id.activity_login_register_textview_username);
		passwordEditText = (EditText) this
				.findViewById(R.id.activity_login_register_textview_password);
		passwordRepeatEditText = (EditText) this
				.findViewById(R.id.activity_login_register_textview_password_repeat);
		firstnameEditText = (EditText) this
				.findViewById(R.id.activity_login_register_textview_firstname);
		lastnameEditText = (EditText) this
				.findViewById(R.id.activity_login_register_textview_lastname);
		emailEditText = (EditText) this
				.findViewById(R.id.activity_login_register_textview_email);
	}

	@Override
	public void onBackPressed()
	{
		super.onBackPressed();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_login, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch (item.getItemId())
		{
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
		case R.id.activity_login_register_button_submit:
			System.out.println("Register button clicked.");
			Account account = new Account();
			account.setUsername(usernameEditText.getText().toString());
			if (passwordEditText.getText().equals(
					passwordRepeatEditText.getText()))
			{
				account.setPassword(passwordEditText.getText().toString());
			} else
			{
				Toast.makeText(this, "Passwords do not match!",
						Toast.LENGTH_LONG).show();
			}
			account.setFirstName(firstnameEditText.getText().toString());
			account.setLastName(lastnameEditText.getText().toString());
			account.setEmail(emailEditText.getText().toString());

			new AsyncTask<Account, Integer, Integer>()
			{

				@Override
				protected Integer doInBackground(Account... account)
				{
					try
					{
						AccountValidationError error = Account
								.validate(account[0]);
						if (error.getHeader() == AccountValidationError.HEADER_NO_ERRORS)
						{
							// No errors, submitting account for registration
							MenUServerInteraction.registerAccount(account[0]);
						} else
						{
							// Error retrieved, displaying the errors
						}
						System.out
								.println(error.getHeader() == AccountValidationError.HEADER_NO_ERRORS ? "YES"
										: "NO");
						System.out.println(Arrays.toString(error.getErrors()));
					} catch (Exception e)
					{
						System.out.println(e);
					}
					return null;
				}
			}.execute(account);
			break;
		default:
			System.out
					.println("That hasn't been assigned any code here, it is a worthless OnClickReciever.");
			break;
		}
	}
}
