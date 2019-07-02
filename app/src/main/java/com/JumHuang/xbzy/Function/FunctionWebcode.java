package com.JumHuang.xbzy.Function;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.JumHuang.xbzy.R;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class FunctionWebcode extends Activity
{
	public TextView message;
	private Button open;
	private EditText url;
	private TextView tvMsg;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.function_webcode);
		tvMsg = (TextView) findViewById(R.id.message);
		message = (TextView) findViewById(R.id.message);
		url = (EditText) findViewById(R.id.url);

		url.setText("http://wap.baidu.com");
		open = (Button) findViewById(R.id.open);
		open.setOnClickListener(new View.OnClickListener() 
			{
				public void onClick(View arg0)
				{
					connect();
				}
			});
	}

	private void connect()
	{
		PageTask task = new PageTask(this);
		task.execute(url.getText().toString());
	}

	class PageTask extends AsyncTask<String,Integer,String>
	{
		ProgressDialog pdialog;

		public PageTask(Context context)
		{
			pdialog = new ProgressDialog(context, 0);
			pdialog.setButton("正在获取中，请稍等一会再来查看!内容多的网页需要30秒到1分钟！", new DialogInterface.OnClickListener()
				{
					public void onClick(DialogInterface dialog, int which)
					{

					}
				});

			pdialog.setOnCancelListener(new DialogInterface.OnCancelListener()
				{
					@Override
					public void onCancel(DialogInterface dialog)
					{
						dialog.cancel();
					}
				});

			pdialog.setCancelable(true);
			pdialog.setMax(100);
			pdialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
			pdialog.show();
		}

		@Override
		protected String doInBackground(String... params)
		{
			try
			{
				HttpClient client = new DefaultHttpClient();
				HttpGet get = new HttpGet(params[0]);
				HttpResponse response = client.execute(get);
				HttpEntity entity = response.getEntity();
				long length = entity.getContentLength();
				InputStream is = entity.getContent();
				String s = null;
				if (is != null)
				{
					ByteArrayOutputStream baos = new ByteArrayOutputStream();
					byte[] buf = new byte[128];
					int ch = -1;
					int count = 0;
					while ((ch = is.read(buf)) != -1)
					{
						baos.write(buf, 0, ch);
						count += ch;
						if (length > 0)
						{
							publishProgress((int)((count / (float)length) * 100));
						}
						Thread.sleep(100);
					}
					s = new String(baos.toByteArray());
					return s;
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onCancelled()
		{
			super.onCancelled();
		}

		@Override
		protected void onPostExecute(String result)
		{
			message.setText(result);
			pdialog.dismiss();
		}

		@Override
		protected void onPreExecute()
		{
			message.setText("获取源码中，请稍等!");
		}

		@Override
		protected void onProgressUpdate(Integer... values)
		{
			System.out.println("" + values[0]);
			message.setText("" + values[0]);
			pdialog.setProgress(values[0]);
		}
	}
}
