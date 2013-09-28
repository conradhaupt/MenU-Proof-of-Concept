package com.conradhaupt.MenU.Core;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.conradhaupt.MenU.R;

public class RestaurantBrowserFragmentMenuAdapter extends
		ArrayAdapter<Restaurant>
{

	Restaurant[] restaurants;
	int resourceLayout;
	Context context;

	public RestaurantBrowserFragmentMenuAdapter(Context context, int resource,
			Restaurant[] objects)
	{
		super(context, resource, objects);
		this.restaurants = objects;
		this.resourceLayout = resource;
		this.context = context;
	}

	@Override
	public boolean isEnabled(int position)
	{
		return true;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		try
		{
			// If the view has not been instantiate then infalte the layout
			if (convertView == null)
			{
				convertView = LayoutInflater.from(context).inflate(
						resourceLayout, parent, false);
			}
			TextView title = (TextView) (convertView
					.findViewById(R.id.list_restaurant_restaurant_title));
			title.setText(restaurants[position].getRestaurantName());
			convertView.setTag(restaurants[position]);

		} catch (Exception e)
		{
			System.out.println("Adapter Error:");
			System.out.println(e);
			return null;
		}
		return convertView;
	}
}
