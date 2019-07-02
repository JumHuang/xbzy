package com.JumHuang.xbzy.Function;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.JumHuang.xbzy.R;

public class FunctionPlayer extends Activity
{
	private EditText ed;
	private Button bt;
	private WebView qiyWeb;
	private String Url="http://yun.mt2t.com/yun?url=";
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.function_player);

		ed = (EditText) findViewById(R.id.qiyed);
		bt = (Button) findViewById(R.id.qiybt);
		qiyWeb = (WebView) findViewById(R.id.qiyWeb);
		qiyWeb.setBackgroundColor(0);

		bt.setOnClickListener(new OnClickListener()
			{
				@Override
				public void onClick(View p1)
				{
					String s=ed.getText().toString();
					if (s.equals(""))
					{
						Toast.makeText(FunctionPlayer.this, "请输入播放地址！", Toast.LENGTH_LONG).show();
					}
					else
					{
						// WebView加载web资源
						qiyWeb.loadUrl(Url + s);

						//  启用支持javascript
						WebSettings settings = qiyWeb.getSettings();
						settings.setJavaScriptEnabled(true);
						settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);

						// 覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
						qiyWeb.setWebViewClient(new WebViewClient()
							{
								@Override
								public boolean shouldOverrideUrlLoading(WebView view, String url)
								{
									//  返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
									view.loadUrl(url);
									return true;
								}
							});
					}
				}
			});
	}
}
