package com.JumHuang.xbzy.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import com.JumHuang.xbzy.R;

public class ThanksActivity extends Activity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_thanks);
	}
}
