package com.conradhaupt.MenU.Core;

import android.os.AsyncTask;

public class RestaurantLoader extends AsyncTask<String, Integer, Boolean>
{

	@Override
	protected Boolean doInBackground(String... params)
	{
		try
		{

		} catch (Exception e)
		{
			System.out.println(e);
			return false;
		}
		return true;
	}

	protected void onProgressUpdate(Integer... progress)
	{
	}

	protected void onPostExecute(Long result)
	{
	}

}
