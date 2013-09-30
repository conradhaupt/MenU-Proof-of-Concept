package com.conradhaupt.MenU.Core;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.conradhaupt.MenU.R;

public class RestaurantMenUAdapter extends ArrayAdapter<ResMenuItem>
{

	ResMenuItem[] restaurants;
	int resourceLayout;
	Context context;

	public RestaurantMenUAdapter(Context context, int resource,
			ResMenuItem[] objects)
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
					.findViewById(R.id.menuitem_title));
			title.setText(restaurants[position].getProductName());
			TextView price = (TextView) (convertView
					.findViewById(R.id.menuitem_price));
			price.setText("R" + restaurants[position].getPrice());
			ImageView currImage = (ImageView) (convertView
					.findViewById(R.id.menuitem_dairy));
			currImage
					.setVisibility(restaurants[position].isContainsDairy() ? View.VISIBLE
							: View.GONE);
			ImageView currImage2 = (ImageView) (convertView
					.findViewById(R.id.menuitem_kosher));
			currImage2
					.setVisibility(restaurants[position].isKosher() ? View.VISIBLE
							: View.GONE);
			ImageView currImage3 = (ImageView) (convertView
					.findViewById(R.id.menuitem_nuts));
			currImage3
					.setVisibility(restaurants[position].isContainsNuts() ? View.VISIBLE
							: View.GONE);
			ImageView currImage4 = (ImageView) (convertView
					.findViewById(R.id.menuitem_vegan));
			currImage4
					.setVisibility(restaurants[position].isVegan() ? View.VISIBLE
							: View.GONE);
			ImageView currImage5 = (ImageView) (convertView
					.findViewById(R.id.menuitem_vegeterian));
			currImage5
					.setVisibility(restaurants[position].isVegeterian() ? View.VISIBLE
							: View.GONE);
			ImageView currImage6 = (ImageView) (convertView
					.findViewById(R.id.menuitem_wheat));
			currImage6
					.setVisibility(restaurants[position].isContainsWheat() ? View.VISIBLE
							: View.GONE);

		} catch (Exception e)
		{
			System.out.println("Adapter Error:");
			System.out.println(e);
			return null;
		}
		return convertView;
	}
}
