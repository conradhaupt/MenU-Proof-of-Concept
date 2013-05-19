package com.conradhaupt.MenU.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

public class SquareRelativeLayout extends RelativeLayout
{
	public boolean squareBasedHorizontal = true;

	public SquareRelativeLayout(Context context)
	{
		super(context);
	}

	public SquareRelativeLayout(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		// TypedArray a = context.obtainStyledAttributes(attrs,
		// R.styleable.squareRelativeLayout);
		// final int N = a.getIndexCount();
		// for (int i = 0; i < N; i++)
		// {
		// int attr = a.getIndex(i);
		// switch (attr)
		// {
		// case R.styleable.squareRelativeLayout_square_relative_horizontally:
		// boolean squareRelativeHorizontally = a.getBoolean(attr, false);
		// break;
		// }
		// }
	}

	public SquareRelativeLayout(Context context, AttributeSet attrs,
			int defStyle)
	{
		super(context, attrs, defStyle);
		// TypedArray a = context.obtainStyledAttributes(attrs,
		// R.styleable.squareRelativeLayout);
		// final int N = a.getIndexCount();
		// for (int i = 0; i < N; i++)
		// {
		// int attr = a.getIndex(i);
		// switch (attr)
		// {
		// case R.styleable.squareRelativeLayout_square_relative_horizontally:
		// this.squareBasedHorizontal = a.getBoolean(attr, false);
		// break;
		// }
		// }
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
