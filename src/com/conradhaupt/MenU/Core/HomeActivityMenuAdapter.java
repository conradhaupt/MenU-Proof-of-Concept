package com.conradhaupt.MenU.Core;

import com.conradhaupt.MenU.R;
import com.conradhaupt.MenU.R.id;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class HomeActivityMenuAdapter extends ArrayAdapter<String>
{

	String[] titles;
	int drawablesID;
	boolean smallMenu;
	int resourceLayout;
	Context context;

	public HomeActivityMenuAdapter(Context context, int resource,
			String[] objects, int drawablesID, boolean smallMenu)
	{
		super(context, resource, objects);
		this.titles = objects;
		this.smallMenu = smallMenu;
		this.resourceLayout = resource;
		this.context = context;
		this.drawablesID = drawablesID;
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

			// Get variables
			TextView title = null;
			if (!this.smallMenu)
			{
				title = (TextView) convertView
						.findViewById(R.id.list_menu_title);
			}
			ImageView image = (ImageView) convertView
					.findViewById(R.id.list_menu_icon);

			// Assign view values
			if (!this.smallMenu)
			{
				title.setText(titles[position]);
			}
			System.out.println("Assigning images");
			image.setImageResource(context.getResources()
					.obtainTypedArray(drawablesID).getResourceId(position, 0));
		} catch (Exception e)
		{
			System.out.println("Adapter Error:");
			System.out.println(e);
			return null;
		}
		return convertView;
	}
}
