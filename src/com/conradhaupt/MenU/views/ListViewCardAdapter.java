package com.conradhaupt.MenU.views;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ArrayAdapter;

public class ListViewCardAdapter extends ArrayAdapter<String>
{

	Context context;

	public ListViewCardAdapter(Context context, int textViewResourceId,
			List<String> objects)
	{
		super(context, textViewResourceId, objects);
		// TODO Auto-generated constructor stub
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		View view = super.getView(position, convertView, null);
		if (convertView == null)
		{
			// view = ((Activity) context).getLayoutInflater().inflate(
			// R.layout.card_featured, null, false);
			Animation animation = new AlphaAnimation(0, 1);
			animation.setDuration(300);
			animation.setStartOffset(40 * position);
			view.startAnimation(animation);
		}
		return view;
	}
}
