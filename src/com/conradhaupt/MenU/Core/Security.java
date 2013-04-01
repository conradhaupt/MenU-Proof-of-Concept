package com.conradhaupt.MenU.Core;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class Security
{

	public static String toSHA1(final String input)
			throws NoSuchAlgorithmException
	{

		MessageDigest md = MessageDigest.getInstance("SHA-256");
		md.update(input.getBytes());

		byte byteData[] = md.digest();

		// convert the byte to hex format
		StringBuffer hexString = new StringBuffer();
		for (int i = 0; i < byteData.length; i++)
		{
			String hex = Integer.toHexString(0xff & byteData[i]);
			if (hex.length() == 1)
				hexString.append('0');
			hexString.append(hex);
		}
		return (hexString.toString());
	}

	public class AccountDatabaseHelper extends SQLiteOpenHelper
	{

		public AccountDatabaseHelper(Context context, String name,
				CursorFactory factory, int version)
		{
			super(context, name, factory, version);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db)
		{
			// TODO Auto-generated method stub

		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
		{
			// TODO Auto-generated method stub

		}

	}
}
