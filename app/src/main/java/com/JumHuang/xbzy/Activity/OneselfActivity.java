package com.JumHuang.xbzy.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.LinearLayout;
import com.JumHuang.xbzy.R;

public class OneselfActivity extends Activity
{
	private LinearLayout oneself_setting;
	private LinearLayout oneself_thanks;
	private LinearLayout oneself_exit;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_oneself);

		oneself_setting = (LinearLayout)findViewById(R.id.oneself_setting);
		oneself_thanks = (LinearLayout)findViewById(R.id.oneself_thanks);
		oneself_exit = (LinearLayout)findViewById(R.id.oneself_exit);

		oneself_setting.setOnClickListener(new OnClickListener()
			{
				@Override
				public void onClick(View p1)
				{
					startActivity(new Intent(OneselfActivity.this, SettingActivity.class));
				}
			});

		oneself_thanks.setOnClickListener(new OnClickListener()
			{
				@Override
				public void onClick(View p1)
				{
					startActivity(new Intent(OneselfActivity.this, ThanksActivity.class));
				}
			});

		oneself_exit.setOnClickListener(new OnClickListener()
			{
				@Override
				public void onClick(View p1)
				{

				}
			});
	}
}
