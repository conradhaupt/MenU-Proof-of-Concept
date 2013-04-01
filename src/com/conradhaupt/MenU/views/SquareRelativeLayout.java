package com.conradhaupt.MenU.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

public class SquareRelativeLayout extends RelativeLayout
{
	public boolean squareBasedHorizontal = false;

	public SquareRelativeLayout(Context context)
	{
		super(context);
	}

	public SquareRelativeLayout(Context context, AttributeSet attrs)
	{
		super(context, attrs);
	}

	public SquareRelativeLayout(Context context, AttributeSet attrs,
			int defStyle)
	{
		super(context, attrs, defStyle);
	}

	public boolean isSquareBasedHorizontal()
	{
		return squareBasedHorizontal;
	}

	public void setSquareBasedHorizontal(boolean squareBasedHorizontal)
	{
		this.squareBasedHorizontal = squareBasedHorizontal;
	}

	@Override
	public void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
	{
		if (this.squareBasedHorizontal)
		{
			super.onMeasure(widthMeasureSpec, widthMeasureSpec);
			return;
		}
		super.onMeasure(heightMeasureSpec, heightMeasureSpec);
	}
}
