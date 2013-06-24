package com.conradhaupt.MenU.Core;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.json.JSONObject;

import android.util.Log;

public class MenUServerInteraction
{
	public static int registerAccount(Account account)
	{
		try
		{
			JSONObject json = new JSONObject();
			json.put("username", account.getUsername());
			json.put("password", account.getPassword());
			json.put("email", account.getEmail());
			json.put("firstname", account.getFirstName());
			json.put("lastname", account.getLastName());
			String jsonString = "{\"lastname\":\"Haupt\",\"username\":\"Phenominal\",\"firstname\":\"Conrad\",\"email\":\"me@conradhaupt.co.za\",\"password\":\"misteryork\"}";
			System.out.println(json.toString());

			HttpClient client = new DefaultHttpClient();
			HttpResponse response;
			HttpPost post = new HttpPost(
					"http://conradhaupt.co.za/menuOrderSystem/account_create.php");
			StringEntity se = new StringEntity("message=" + json.toString());
			post.addHeader("content-type", "application/json");
			post.setEntity(se);
			response = client.execute(post);
			if (response != null)
			{
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
