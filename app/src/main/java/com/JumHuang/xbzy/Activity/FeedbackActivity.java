package com.JumHuang.xbzy.Activity;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.JumHuang.xbzy.R;
import javax.toshiba.Email;

public class FeedbackActivity extends Activity
{
	private EditText feedback_msg;
	private EditText feedback_lxqq;
	private Button feedback_send;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_feedback);

		feedback_msg = (EditText)findViewById(R.id.feedback_msg);
		feedback_lxqq = (EditText)findViewById(R.id.feedback_lxqq);
		feedback_send = (Button)findViewById(R.id.feedback_send);

		feedback_send.setOnClickListener(new OnClickListener()
			{
				@Override
				public void onClick(View p1)
				{
					//需要异步任务(安卓对子线程与如何更新UI的便捷式封装类)发送邮箱，安卓不允许在主线程执行联网操作！！！
					new AsyncTask<Void,Void,Boolean>()
					{
						//后台耗时操作(如复制文件或联网)
						@Override
						protected Boolean doInBackground(Void[] p1)
						{
							//邮箱
							String yourEmail="2176202447@qq.com";
							//邮箱密码，以上两个通常不对外，可写死(可不从输入框获取)
							String yourEmailPassword="hj20030827";
							//要接收的邮箱，可以是自己也可以是其它邮箱(代码支持可发多个邮箱，用逗号分割)
							String toEmail="3116075662@qq.com";
							//邮件标题
							String title="小白资源用户反馈";
							//邮件内容
							String content=feedback_msg.getText().toString() + "\n\n" + feedback_lxqq.getText().toString();
							//邮箱发送的核心代码(已封装好，所以很简单)
							//返回是否成功(Email.TYPE_QQ_EMAIL这个参数可选其它邮箱类型如Email.TYPE_139_EMAIL等)
							boolean isok=new Email(yourEmail, yourEmailPassword, Email.TYPE_QQ_EMAIL).send(toEmail, title, content);//发送
							return isok;//返回结果
						}				
						//回调结果(主线程)
						@Override
						protected void onPostExecute(Boolean result)
						{
							Toast.makeText(getApplicationContext(), result ?"发送成功": "发送失败", Toast.LENGTH_LONG).show();				
						}
					}.execute();//启动任务
				}
			});
	}
}
