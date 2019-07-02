package com.JumHuang.xbzy.Activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.JumHuang.xbzy.R;
import com.JumHuang.xbzy.Setting.BackgroundChooseActivity;

public class SettingActivity extends Activity
{
	private LinearLayout setting_clearhistory;
	private LinearLayout setting_feedback;
	private LinearLayout setting_backgroundchoose;
	private LinearLayout setting_contentme;
	private LinearLayout setting_joinqqgroup;
	private LinearLayout setting_about;
	private LinearLayout setting_gitee;
	private LinearLayout setting_share;
	private WebView xbzy_webview;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_setting);

		setting_clearhistory = (LinearLayout)findViewById(R.id.setting_clearhistory);
		setting_feedback = (LinearLayout)findViewById(R.id.setting_feedback);
		setting_backgroundchoose = (LinearLayout)findViewById(R.id.setting_backgroundchoose);
		setting_contentme = (LinearLayout)findViewById(R.id.setting_contentme);
		setting_joinqqgroup = (LinearLayout)findViewById(R.id.setting_joinqqgroup);
		setting_about = (LinearLayout)findViewById(R.id.setting_about);
		setting_gitee = (LinearLayout)findViewById(R.id.setting_gitee);
		setting_share = (LinearLayout)findViewById(R.id.setting_share);
		xbzy_webview = (WebView)findViewById(R.id.xbzy_webview);

		setting_clearhistory.setOnClickListener(new OnClickListener()
			{
				@Override
				public void onClick(View p1)
				{
					xbzy_webview.clearCache(true);
					xbzy_webview.clearHistory();
				}
			});

		setting_feedback.setOnClickListener(new OnClickListener()
			{
				@Override
				public void onClick(View p1)
				{
					startActivity(new Intent(SettingActivity.this, FeedbackActivity.class));
				}
			});

		setting_backgroundchoose.setOnClickListener(new OnClickListener()
			{
				@Override
				public void onClick(View p1)
				{
					//Toast.makeText(SettingActivity.this, "嗯，有漏洞，此功能暂停开发！", Toast.LENGTH_SHORT).show();
					startActivity(new Intent(SettingActivity.this, BackgroundChooseActivity.class));
				}
			});

		setting_contentme.setOnClickListener(new OnClickListener()
			{
				@Override
				public void onClick(View p1)
				{
					startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("mqqwpa://im/chat?chat_type=wpa&uin=3116075662")));
				}
			});

		setting_joinqqgroup.setOnClickListener(new OnClickListener()
			{
				@Override
				public void onClick(View p1)
				{
					joinQQGroup("EKxejpWxZYY-IYW6xeJrS1F3DwydxaaJ");
				}
				public boolean joinQQGroup(String key)
				{
					Intent intent = new Intent();
					intent.setData(Uri.parse("mqqopensdkapi://bizAgent/qm/qr?url=http%3A%2F%2Fqm.qq.com%2Fcgi-bin%2Fqm%2Fqr%3Ffrom%3Dapp%26p%3Dandroid%26k%3D" + key));
					try
					{
						startActivity(intent);
						Toast.makeText(SettingActivity.this, "跳转中...", Toast.LENGTH_SHORT).show();
						return true;
					}
					catch (Exception e)
					{
						Toast.makeText(SettingActivity.this, "手机未安装QQ或版本过低！", Toast.LENGTH_SHORT).show();
						return false;
					}
				}
			});

		setting_about.setOnClickListener(new OnClickListener()
			{
				@Override
				public void onClick(View p1)
				{
					AlertDialog.Builder a=new AlertDialog.Builder(SettingActivity.this);
					a.setCancelable(true);
					a.setTitle("关于");
					a.setMessage("没啥好关于的");
					a.setPositiveButton("关闭", new DialogInterface.OnClickListener()
						{
							@Override
							public void onClick(DialogInterface p1, int p2)
							{

							}
						});
					a.show();
				}
			});

		setting_gitee.setOnClickListener(new OnClickListener()
			{
				@Override
				public void onClick(View p1)
				{
					startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://gitee.com/codehuang/")));
				}
			});

		setting_share.setOnClickListener(new OnClickListener()
			{
				@Override
				public void onClick(View p1)
				{
					Intent shareIntent = new Intent();
					shareIntent.setAction(Intent.ACTION_SEND);
					shareIntent.putExtra(Intent.EXTRA_TEXT, "Hi，我正在使用小白资源,给大家分享一下,下载链接：https://xbzy.tianyan.hk");
					shareIntent.setType("text/plain");
					startActivity(Intent.createChooser(shareIntent, "分享"));
				}
			});
	}
}
