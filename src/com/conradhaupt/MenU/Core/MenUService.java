package com.conradhaupt.MenU.Core;

import java.io.FileNotFoundException;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

public class MenUService extends Service
{

	public MenUService()
	{
		// TODO Auto-generated constructor stub
	}

	@Override
	public IBinder onBind(Intent intent)
	{
		// TODO Auto-generated method stub
		return null;
	}

	public static String getUserAuth(Context context, String username,
			String password)
	{
		try
		{
			context.openFileInput("accounts");
		} catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
