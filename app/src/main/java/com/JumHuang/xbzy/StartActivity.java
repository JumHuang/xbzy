package com.JumHuang.xbzy;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import com.JumHuang.xbzy.Config.PrefUtils;

public class StartActivity extends Activity
{
	private TextView tv;
	private Button btn;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_start);

		tv = (TextView)findViewById(R.id.start_time_tv);
		btn = (Button)findViewById(R.id.start_time_btn);

		// 判断之前有没有显示过新手引导
		final boolean userGuide = PrefUtils.getBoolean(getApplicationContext(), "is_user_guide_showed", false);
		enterHome(userGuide);

		btn.setOnClickListener(new OnClickListener()
			{
				@Override
				public void onClick(View v)
				{
					startActivity(new Intent(StartActivity.this, MainActivity.class));
					finish();
				}
			});

		PackageManager pm=getPackageManager();
		try
		{
			PackageInfo pi=pm.getPackageInfo("com.JumHuang.xbzy", 0);
			TextView versiontext=(TextView)findViewById(R.id.start_versiontext);
			versiontext.setText("Version: " + pi.versionName);
		}
		catch (PackageManager.NameNotFoundException e)
		{
			e.printStackTrace();
		}

		//倒计时
		CountDownTimer timer=new CountDownTimer(5000, 10)
		{
			public void onTick(long millisUntilFinished)
			{
				tv.setText(millisUntilFinished / 1000 + "秒");
			}
			public void onFinish()
			{
				tv.setText("0秒");
				startActivity(new Intent(StartActivity.this, MainActivity.class));
				finish();
			}
		};
		timer.start();
	}

	//判断是否第一次进入软件
	private void enterHome(boolean userGuide) 
	{
		if (!userGuide)
		{
			// 跳转到新手引导页
			startActivity(new Intent(getApplicationContext(), GuideActivity.class));
		}
		else
		{
		}
	};

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		//在欢迎界面屏蔽BACK键
		if (keyCode == KeyEvent.KEYCODE_BACK)
		{
			return false;
		}
		return false;
	}
}
