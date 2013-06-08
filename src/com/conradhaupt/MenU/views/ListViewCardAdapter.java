package com.conradhaupt.MenU.views;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;

import com.conradhaupt.MenU.R;

public class ListViewCardAdapter extends ArrayAdapter<String>
{

	Context context;
	public int card_animation_duration;
	public int card_animation_duration_addition;
	public int card_animation_delay;

	public ListViewCardAdapter(Context context, int textViewResourceId,
			List<String> objects)
	{
		super(context, textViewResourceId, objects);
		this.context = context;
		card_animation_duration = context.getResources().getInteger(
				R.integer.fragment_home_card_appear_anim_duration);
		card_animation_duration_addition = context.getResources().getInteger(
				R.integer.fragment_home_card_appear_anim_duration_addition);
		card_animation_delay = context.getResources().getInteger(
				R.integer.fragment_home_card_appear_anim_delay);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		View view = convertView;
		if (view == null)
		{
			try
			{
				LayoutInflater inflater = LayoutInflater.from(context);
				view = inflater.inflate(R.layout.card_featured, null);
			} catch (Exception e)
			{
				System.out.println(e);
				view = super.getView(position, convertView, null);
			}
			Animation animation = AnimationUtils.loadAnimation(context,
					R.anim.card_add);
			animation.setDuration(card_animation_duration
					+ (position * card_animation_duration_addition));
			animation.setStartOffset(position * card_animation_delay);
			view.startAnimation(animation);
			view.findViewById(R.id.card_featured_cancel).setOnClickListener(
					new OnClickListener()
					{

						@Override
						public void onClick(View v)
						{

						}
					});
		}
		return view;
	}
}
