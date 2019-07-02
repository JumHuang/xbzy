package com.JumHuang.xbzy;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.TabActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;
import android.widget.Toast;
import com.JumHuang.xbzy.Activity.FeedbackActivity;
import com.JumHuang.xbzy.Activity.FloatWindowActivity;
import com.JumHuang.xbzy.Config.Constant.ConValue;

public class MainActivity extends TabActivity
{
	private TabHost mTabHost;
	private LayoutInflater mInflater;
	private LinearLayout leftlayoutfloatwindow;
	private LinearLayout leftlayoutlx;
	private LinearLayout leftlayoutfeedback;
	private LinearLayout leftlayoutshare;
	private LinearLayout leftlayoutpact;
	private LinearLayout leftlayoutupdate;
	private LinearLayout leftlayoutmore;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);

		initView();
		initother();
		initleftmenu();
	}

	private void initother()
	{
		/*检测用户是否为内测人员
		 TelephonyManager imeia= (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
		 String imeib=imeia.getDeviceId();
		 if(imeib.equals(""))
		 {
		 Toast.makeText(MainActivity.this,"欢迎内测人员！",Toast.LENGTH_SHORT).show();
		 }
		 else
		 {
		 Toast.makeText(MainActivity.this,"你没有内测资格！",Toast.LENGTH_SHORT).show();
		 finish();
		 }*/

		//检测软件签名信息是否被修改，不同则本软件被别人修改
		try
		{
			PackageInfo a = getPackageManager().getPackageInfo("com.JD.JumHuang.xbzy", PackageManager.GET_SIGNATURES);
			Signature[] b = a.signatures;
			Signature c = b[0];
			int d = c.hashCode();
			//Toast.makeText(MainActivity.this,"ID:"+d,Toast.LENGTH_LONG).show();//获取软件签名并用Toast显示出来
			if (d != -2078635626)
			{
				AlertDialog.Builder signerror=new AlertDialog.Builder(this);
				signerror.setCancelable(false);
				signerror.setTitle("软件错误！");
				signerror.setMessage("如果你看到此弹窗，那么就证明您所使用的本软件已被第三者修改，并不是官方原版！\n\n请安装官方版，以保证您的手机和财产安全！");
				signerror.setPositiveButton("退出", new DialogInterface.OnClickListener()
					{
						@Override
						public void onClick(DialogInterface p1, int p2)
						{
							finish();
						}
					});
				signerror.setNegativeButton("下载原版", new DialogInterface.OnClickListener()
					{
						@Override
						public void onClick(DialogInterface p1, int p2)
						{
							startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://xbwz.tianyan.hk")));
							finish();
						}
					});
				signerror.show();
			}
			else
			{

			}
		}
		catch (PackageManager.NameNotFoundException e)
		{}
	}

	//左划布局点击事件
	private void initleftmenu()
	{
		leftlayoutfloatwindow = (LinearLayout)findViewById(R.id.leftlayoutfloatwindow);
		leftlayoutlx = (LinearLayout)findViewById(R.id.leftlayoutlx);
		leftlayoutfeedback = (LinearLayout)findViewById(R.id.leftlayoutfeedback);
		leftlayoutshare = (LinearLayout)findViewById(R.id.leftlayoutshare);
		leftlayoutpact = (LinearLayout)findViewById(R.id.leftlayoutpact);
		leftlayoutupdate = (LinearLayout)findViewById(R.id.leftlayoutupdate);
		leftlayoutmore = (LinearLayout)findViewById(R.id.leftlayoutmore);

		leftlayoutfloatwindow.setOnClickListener(new OnClickListener()
			{
				@Override
				public void onClick(View p1)
				{
					startActivity(new Intent(MainActivity.this, FloatWindowActivity.class));
				}
			});

		leftlayoutlx.setOnClickListener(new OnClickListener()
			{
				@Override
				public void onClick(View p1)
				{
					startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("mqqapi://card/show_pslcard?src_type=internal&source=sharecard&version=1&uin=3116075662")));
				}
			});

		leftlayoutfeedback.setOnClickListener(new OnClickListener()
			{
				@Override
				public void onClick(View p1)
				{
					startActivity(new Intent(MainActivity.this, FeedbackActivity.class));
				}
			});

		leftlayoutshare.setOnClickListener(new OnClickListener()
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

		leftlayoutpact.setOnClickListener(new OnClickListener()
			{
				@Override
				public void onClick(View p1)
				{
					LayoutInflater pactinflaterDl = LayoutInflater.from(MainActivity.this);
					LinearLayout pactdialogLayout = (LinearLayout)pactinflaterDl.inflate(R.layout.pact_webview, null);
					//对话框
					final Dialog pactdialog = new AlertDialog.Builder(MainActivity.this).create();
					pactdialog.show();
					pactdialog.getWindow().setContentView(pactdialogLayout);
					WebView pactwv=(WebView) pactdialogLayout.findViewById(R.id.pact_webview);
					pactwv.loadUrl("file:///android_asset/pact.html");
					pactwv.setScrollBarStyle(0);
					pactwv.getSettings().setAllowFileAccess(true);
					pactwv.getSettings().setDefaultTextEncodingName("utf-8");
					pactwv.getSettings().setJavaScriptEnabled(true);
					pactwv.setWebViewClient(new WebViewClient()
						{ 
							public boolean shouldOverrideUrlLoading(WebView view, String url) 
							{ 
								view.loadUrl(url); 
								return true; 
							} 
						}); 
				}
			});

		leftlayoutupdate.setOnClickListener(new OnClickListener()
			{
				@Override
				public void onClick(View p1)
				{
					Toast.makeText(MainActivity.this, "暂未开发，详情请看『计划』", Toast.LENGTH_SHORT).show();
					//startActivity(new Intent(MainActivity.this, LibraryActivity.class));
				}
			});

		leftlayoutmore.setOnClickListener(new OnClickListener()
			{
				@Override
				public void onClick(View p1)
				{
					LayoutInflater moreinflaterDl = LayoutInflater.from(MainActivity.this);
					LinearLayout moredialogLayout = (LinearLayout)moreinflaterDl.inflate(R.layout.pact_webview, null);
					//对话框
					final Dialog moredialog = new AlertDialog.Builder(MainActivity.this).create();
					moredialog.show();
					moredialog.getWindow().setContentView(moredialogLayout);
					WebView morewv=(WebView) moredialogLayout.findViewById(R.id.pact_webview);
					morewv.loadUrl("file:///android_asset/plant.html");
					morewv.setScrollBarStyle(0);
					morewv.getSettings().setAllowFileAccess(true);
					morewv.getSettings().setDefaultTextEncodingName("utf-8");
					morewv.getSettings().setJavaScriptEnabled(true);
					morewv.setWebViewClient(new WebViewClient()
						{ 
							public boolean shouldOverrideUrlLoading(WebView view, String url) 
							{ 
								view.loadUrl(url); 
								return true; 
							} 
						}); 
				}
			});
	}

	/**
	 * 初始化组件
	 */
	private void initView()
	{
		// 实例化TabHost对象，得到TabHost
		mTabHost = getTabHost();

		// 实例化布局对象
		mInflater = LayoutInflater.from(this);

		// 得到Activity的个数
		int count = ConValue.mTabClassArray.length;

		for (int i = 0; i < count; i++)
		{
			// 为每一个Tab按钮设置图标、文字和内容
			TabSpec tabSpec = mTabHost.newTabSpec(ConValue.mTextViewArray[i])
				.setIndicator(getTabItemView(i))
				.setContent(getTabItemIntent(i));
			// 将Tab按钮添加进Tab选项卡中
			mTabHost.addTab(tabSpec);
			// 设置Tab按钮的背景
			mTabHost.getTabWidget().getChildAt(i)
				.setBackgroundResource(R.drawable.tab_background_selector);
		}
	}

	/**
	 * 给Tab按钮设置图标和文字
	 */
	private View getTabItemView(int index)
	{
		View view = mInflater.inflate(R.layout.item_tab_view, null);
		ImageView imageView = (ImageView) view.findViewById(R.id.image_icon);
		if (imageView != null)
		{
			imageView.setImageResource(ConValue.mImageViewArray[index]);
		}
		TextView textView = (TextView) view.findViewById(R.id.text_name);
		textView.setText(ConValue.mTextViewArray[index]);
		return view;
	}

	/**
	 * 给Tab选项卡设置内容（每个内容是一个Activity）
	 */
	private Intent getTabItemIntent(int index)
	{
		Intent intent = new Intent(this, ConValue.mTabClassArray[index]);
		return intent;
	}
}

