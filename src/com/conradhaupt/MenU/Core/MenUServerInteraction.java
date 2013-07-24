package com.conradhaupt.MenU.Core;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.content.Context;

public class MenUServerInteraction
{

	public static AccountError registerAccount(Account account, Context context)
	{
		System.out.println("Registering account");
		AccountError outputErrors = new AccountError(context);
		String output = null;
		try
		{
			List<NameValuePair> variables = new ArrayList<NameValuePair>();
			variables.add(new BasicNameValuePair("username", account
					.getUsername()));
			variables.add(new BasicNameValuePair("password", account
					.getPassword()));
			variables.add(new BasicNameValuePair("email", account.getEmail()));
			variables.add(new BasicNameValuePair("firstname", account
					.getFirstName()));
			variables.add(new BasicNameValuePair("lastname", account
					.getLastName()));

			HttpClient client = new DefaultHttpClient();
			HttpResponse response;
			HttpPost post = new HttpPost(
					"http://conradhaupt.co.za/menuOrderSystem/account_create.php");
			post.setEntity(new UrlEncodedFormEntity(variables));
			response = client.execute(post);
			if (response != null)
			{
				InputStream in = response.getEntity().getContent();

				if (in != null)
				{
					StringBuilder sb = new StringBuilder();
					String line;
					try
					{
						BufferedReader reader = new BufferedReader(
								new InputStreamReader(in, "UTF-8"));
						while ((line = reader.readLine()) != null)
						{
							sb.append(line).append("\n");
						}
					} finally
					{
						in.close();
					}
					output = sb.toString();
				} else
				{
					output = "";
				}
			}
		} catch (Exception t)
		{
			output = t.toString();
		}

		System.out
				.println("Connected to server with output or returned message of:\n"
						+ output);
		// Add all errors if found
		if (output.contains(AccountError.CONNECTION_NOT_AVAILABLE_KEYWORD))
		{
			outputErrors.addError(AccountError.CONNECTION_NOT_AVAILABLE);
		}
		if (output.contains(AccountError.USERNAME_NOT_AVAILABLE_KEYWORD))
		{
			outputErrors.addError(AccountError.USERNAME_NOT_AVAILABLE);
		}
		if (output.contains(AccountError.EMAIL_NOT_AVAILABLE_KEYWORD))
		{
			outputErrors.addError(AccountError.EMAIL_NOT_AVAILABLE);
		}
		System.out
				.println("The RegistrationError object recorded the following errors:\n"
						+ outputErrors.getStringErrors());
		return outputErrors;
	}
}
