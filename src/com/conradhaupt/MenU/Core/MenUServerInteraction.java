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
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.util.Log;

public class MenUServerInteraction
{
	public static int registerAccount(Account account)
	{
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
			System.out.println("Executed");
			if (response != null)
			{
				System.out.println("Response doesn't equal null");
				InputStream in = response.getEntity().getContent();

				String a;
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
					a = sb.toString();
				} else
				{
					a = "";
				}
				Log.i("Read from Server", a);
				System.out.println("Read from server: " + a);
			}
			System.out.println("Hello?");
		} catch (Exception t)
		{
			System.out.println(t);
		}
		return -1;
	}
}
