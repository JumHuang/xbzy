package com.JumHuang.xbzy.Menu.Animation;

import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;

class ResizeAnimation extends Animation
{
	private static final long DEFAULT_DURATION_IN_MS = 250;

	private View view;
	private float toHeight;
	private float fromHeight;
	private float toWidth;
	private float fromWidth;

	ResizeAnimation(View view, float toWidth, float toHeight)
	{
		this.toHeight = toHeight;
		this.toWidth = toWidth;
		this.fromHeight = view.getHeight();
		this.fromWidth = view.getWidth();
		this.view = view;
		setDuration(DEFAULT_DURATION_IN_MS);
	}

	@Override
	protected void applyTransformation(float interpolatedTime, Transformation t)
	{
		float height = (toHeight - fromHeight) * interpolatedTime + fromHeight;
		float width = (toWidth - fromWidth) * interpolatedTime + fromWidth;
		ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
		layoutParams.height = (int) height;
		layoutParams.width = (int) width;
		view.requestLayout();
	}

	@Override
	public boolean willChangeBounds()
	{
		return true;
	}
}
