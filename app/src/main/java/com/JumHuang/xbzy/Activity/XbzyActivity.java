package com.JumHuang.xbzy.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.JumHuang.xbzy.Config.Const;
import com.JumHuang.xbzy.Menu.ExpandableItem;
import com.JumHuang.xbzy.Menu.ExpandableSelector;
import com.JumHuang.xbzy.Menu.OnExpandableItemClickListener;
import com.JumHuang.xbzy.R;
import com.JumHuang.xbzy.Util.URLUtil;
import java.util.ArrayList;
import java.util.List;

public class XbzyActivity extends Activity
{
	private WebView xbzy_webview;
	private ExpandableSelector round_menu;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_xbzy);

		xbzy_webview = (WebView)findViewById(R.id.xbzy_webview);
		round_menu = (ExpandableSelector)findViewById(R.id.round_menu);

		initializeSizesExpandableSelector();

		xbzy_webview.loadUrl("http://xbwz.tianyan.hk");//载入网站地址
		xbzy_webview.setScrollBarStyle(0);
		xbzy_webview.getSettings().setJavaScriptEnabled(true); //允许网页加载JS文件
		xbzy_webview.getSettings().setSupportZoom(true); //允许缩放
		xbzy_webview.getSettings().setDefaultTextEncodingName("utf-8"); //utf-8网页编码
		xbzy_webview.getSettings().setBuiltInZoomControls(true);  //原网页基础上缩放
		xbzy_webview.getSettings().setUseWideViewPort(true); //任意比例缩放
		xbzy_webview.getSettings().setAllowFileAccess(true); //可访问文件
		xbzy_webview.getSettings().setJavaScriptCanOpenWindowsAutomatically(true); //支持js弹窗

		xbzy_webview.setOnTouchListener(new OnTouchListener()
			{
				@Override
				public boolean onTouch(View p1, MotionEvent p2)
				{
					switch (p2.getAction())
					{
						case MotionEvent.ACTION_DOWN:
						case MotionEvent.ACTION_UP:
							if (!p1.hasFocus())
							{
								p1.requestFocus();
							}
							break;
					}
					return false;
				}
			});

		xbzy_webview.setWebChromeClient(new WebChromeClient());

		xbzy_webview.setWebViewClient(new WebViewClient()
			{
				@Override
				public boolean shouldOverrideUrlLoading(WebView view, String url)
				{
					return false;
				}
			});
	}

	private void initializeSizesExpandableSelector()
	{
		List<ExpandableItem> expandableItems = new ArrayList<>();
        expandableItems.add(new ExpandableItem("主页", Const.TYPE_XBZY));
        expandableItems.add(new ExpandableItem("百度", Const.TYPE_BAIDU));
		expandableItems.add(new ExpandableItem("软件", Const.TYPE_SD));
		expandableItems.add(new ExpandableItem("源码", Const.TYPE_MASTER));
        round_menu.showExpandableItems(expandableItems);
        round_menu.setOnExpandableItemClickListener(new OnExpandableItemClickListener() {
				@Override
				public void onExpandableItemClickListener(int p1, View p2)
				{
					final ExpandableItem item = round_menu.getExpandableItem(p1);
					swipeFirstItem(p1, item);
					if (p1 != 0)
					{
						new Handler().postDelayed(new Runnable() {//延时100ms执行，否则会卡顿
								@Override
								public void run()
								{
									getNews(item.getType(), false);
								}
							}, 100);
					}
					round_menu.collapse();
				}

				private void swipeFirstItem(int position, ExpandableItem clickedItem)
				{
					ExpandableItem firstItem = round_menu.getExpandableItem(0);
					round_menu.updateExpandableItem(0, clickedItem);
					round_menu.updateExpandableItem(position, firstItem);
				}
			});
	}

	private void getNews(final int infoType, final boolean isLoadMore)
	{
		xbzy_webview.loadUrl(URLUtil.getUrl(infoType));
	}
}
