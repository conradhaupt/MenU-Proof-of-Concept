package com.conradhaupt.MenU.cards;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.conradhaupt.MenU.R;
import com.fima.cardsui.objects.Card;

public class FeaturedCard extends Card
{
	public FeaturedCard()
	{
		// TODO Auto-generated constructor stub
	}

	public FeaturedCard(String title)
	{
		super(title);
		// TODO Auto-generated constructor stub
	}

	public FeaturedCard(String title, int image)
	{
		super(title, image);
		// TODO Auto-generated constructor stub
	}

	public FeaturedCard(String title, String desc, int image)
	{
		super(title, desc, image);
		// TODO Auto-generated constructor stub
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
