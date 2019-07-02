package com.JumHuang.xbzy.Activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.ClipboardManager;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;
import com.JumHuang.xbzy.R;
import java.util.Timer;
import java.util.TimerTask;

public class FloatWindowActivity extends Activity 
{
	private static final int MSG_CODE = 1001;
	private Handler mHandler = new Handler()
	{
		@Override
		public void handleMessage(Message msg)
		{
			if (msg.what == MSG_CODE)
			{
				wy.loadUrl("");//线程更新ui
				mWindowManager.updateViewLayout(bahk, wmParams);
			}
		}
	};

	private ViewSwitcher mSwitch1;
	private LinearLayout bahk,layout1;
	private TextView ht,qj,sx,fz,gb;
	private WebView wy;
	private WindowManager.LayoutParams wmParams;
	private WindowManager mWindowManager;
	private View mFloatLayout;
	private float mTouchStartX;
	private float mTouchStartY;
	private float x;
	private float y;
	private static final String TAG = "FloatService";
	boolean initViewPlace = false;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		wmParams = new WindowManager.LayoutParams();
		mWindowManager = (WindowManager)getApplication().getSystemService(getApplication().WINDOW_SERVICE);
		wmParams.type = LayoutParams. TYPE_SYSTEM_ERROR;
		wmParams.flags = LayoutParams.FLAG_NOT_TOUCH_MODAL;
		wmParams.format = PixelFormat.RGBA_8888;
		wmParams.gravity = Gravity.LEFT | Gravity.TOP;
		wmParams.x = 0;
		wmParams.y = 0;
		wmParams.width = ((int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 170, getResources().getDisplayMetrics()));
		wmParams.height = ((int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 200, getResources().getDisplayMetrics()));
		LayoutInflater inflater = LayoutInflater.from(getApplication());
		mFloatLayout = inflater.inflate(R.layout.activity_floatwindow, null);
		mWindowManager.addView(mFloatLayout, wmParams);
		bahk = (LinearLayout)mFloatLayout.findViewById(R.id.bahk);
		ht = (TextView)mFloatLayout.findViewById(R.id.ht);
		qj = (TextView)mFloatLayout.findViewById(R.id.qj);
		sx = (TextView)mFloatLayout.findViewById(R.id.sx);
		fz = (TextView)mFloatLayout.findViewById(R.id.fz);
		gb = (TextView)mFloatLayout.findViewById(R.id.gb);
		wy = (WebView)mFloatLayout.findViewById(R.id.wy);
		layout1 = (LinearLayout)mFloatLayout. findViewById(R.id.nextlayout1);
		mSwitch1 = (ViewSwitcher)mFloatLayout. findViewById(R.id.viewswitch1);

		wy.setBackgroundColor(0);

		wy.getSettings().setJavaScriptEnabled(true);
		wy.setScrollBarStyle(0);
		wy.getSettings().setAllowFileAccess(true);
		wy.loadUrl("http://wap.baidu.com");
		wy.setWebViewClient(new WebViewClient()
			{   
				public boolean shouldOverrideUrlLoading(WebView view, String url)
				{       
					view.loadUrl(url) ;      
					return true ;
				}
			});

		Intent home = new Intent(Intent.ACTION_MAIN);
		home.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		home.addCategory(Intent.CATEGORY_HOME);
		startActivity(home);//退回桌面

		ht.setOnClickListener(new OnClickListener()
			{
				@Override
				public void onClick(View p1)
				{
					wy.goBack(); //后退
				}
			});

		qj.setOnClickListener(new OnClickListener()
			{
				@Override
				public void onClick(View p1)
				{
					wy.goForward();//前进
				}
			});

		sx.setOnClickListener(new OnClickListener()
			{
				@Override
				public void onClick(View p1)
				{
					wy.reload(); //刷新
				}
			});

		fz.setOnClickListener(new OnClickListener()
			{
				@Override
				public void onClick(View p1)
				{
					try
					{//复制网址
						ClipboardManager manager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
						manager.setText(wy.getUrl());
						Toast.makeText(FloatWindowActivity.this, "已复制网页地址！", Toast.LENGTH_SHORT).show();
					}
					catch (Exception e)
					{}
				}
			});

		gb.setOnClickListener(new OnClickListener()
			{
				@Override
				public void onClick(View p1)
				{
					try
					{
						mSwitch1.showNext();//退出动画
						Timer timer = new Timer();    
						timer.schedule(new TimerTask()    
							{    
								public void run()     
								{    
									System.exit(0);//结束整个程序
								}    
							}, 800);
					}
					catch (Exception e)
					{}
				}
			});

		bahk.setOnTouchListener(new OnTouchListener()
			{
				public boolean onTouch(View v, MotionEvent event)
				{
					try
					{
						switch (event.getAction())
						{
							case MotionEvent.ACTION_DOWN:
								if (!initViewPlace)
								{
									initViewPlace = true;
									//获取初始位置
									mTouchStartX = event.getRawX();
									mTouchStartY = event.getRawY();
									x = event.getRawX();
									y = event.getRawY(); 
									Log.i(TAG, "startX:" + mTouchStartX + "=>startY:" + mTouchStartY);
								}
								else
								{
									//根据上次手指离开的位置与此次点击的位置进行初始位置微调
									mTouchStartX += (event.getRawX() - x);
									mTouchStartY += (event.getRawY() - y);
								}
								break;
							case MotionEvent.ACTION_MOVE:
								// 获取相对屏幕的坐标，以屏幕左上角为原点
								x = event.getRawX();
								y = event.getRawY(); 
								updateViewPosition();
								break;
							case MotionEvent.ACTION_UP:
								break;
						}
					}
					catch (Exception e)
					{}
					return true;
				}
			});
	}

	private void updateViewPosition()
	{
		// 更新浮动窗口位置参数
		try
		{
			wmParams.x = (int) (x - mTouchStartX);
			wmParams.y = (int) (y - mTouchStartY);
			mWindowManager.updateViewLayout(bahk, wmParams);
		}
		catch (Exception e)
		{}
	}
}

