package com.conradhaupt.MenU.views;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.conradhaupt.MenU.R;
import com.fima.cardsui.objects.Card;

public class FeaturedCard extends Card
{

	public FeaturedCard(String title)
	{
		super(title);
	}

	@Override
	public View getCardContent(Context context)
	{
		View view = LayoutInflater.from(context).inflate(
				R.layout.card_featured, null);

		((TextView) view.findViewById(R.id.card_featured_title)).setText(title);

		return view;
	}

}
