package com.JumHuang.xbzy.Function;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.JumHuang.xbzy.R;

public class FunctionMyy520 extends Activity
{
	private EditText ed;
	private Button bt;
	private WebView wv;
	private String Url="http://c.tianhezulin.com/cx/145/?name=";
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.function_myy520);
		ed = (EditText) findViewById(R.id.myy520EditText1);
		bt = (Button) findViewById(R.id.myy520Button1);
		wv = (WebView) findViewById(R.id.myy520WebView);

		bt.setOnClickListener(new OnClickListener()
			{
				@Override
				public void onClick(View p1)
				{
					String nr=ed.getText().toString();
					if (nr.equals(""))
					{
						Toast.makeText(FunctionMyy520.this, "请输入名字！", 1000).show();
					}
					else
					{
						wv.loadUrl(Url + nr);

						// 覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
						wv.setWebViewClient(new WebViewClient()
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

