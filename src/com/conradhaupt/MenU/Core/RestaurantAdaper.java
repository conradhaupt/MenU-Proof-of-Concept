package com.conradhaupt.MenU.Core;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class RestaurantAdaper<T> extends ArrayAdapter<T>
{

	public RestaurantAdaper(Context context, int resource,
			int textViewResourceId, T[] objects)
	{
		super(context, resource, textViewResourceId, objects);
		// TODO Auto-generated constructor stub
	}

}
