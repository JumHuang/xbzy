package com.JumHuang.xbzy.Config;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.JumHuang.xbzy.R;
import java.lang.reflect.Field;

public class MyFragment<T> extends Fragment
	{
		T data;
		int imgResource;

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
			{
				// 第一个参数是这个Fragment将要显示的界面布局,第二个参数是这个Fragment所属的Activity,第三个参数是决定此fragment是否附属于Activity
				View view=inflater.inflate(R.layout.ads_fragment, container, false);
				TextView txtView = (TextView) view.findViewById(R.id.adsimg);
				txtView.setBackgroundResource(imgResource);
				System.out.println("FragmentOne onCreateView");
				return view;
			}

		@Override
		public void onCreate(Bundle savedInstanceState)
			{
				super.onCreate(savedInstanceState);
				if (getArguments() != null)
					{
						data = (T) getArguments().getSerializable("data");
						try
							{
								Field file = data.getClass().getDeclaredField("imgResource");
								file.setAccessible(true);
								imgResource = file.getInt(data);
							}
						catch (NoSuchFieldException e)
							{
								e.printStackTrace();
							}
						catch (IllegalArgumentException e)
							{
								e.printStackTrace();
							}
						catch (IllegalAccessException e)
							{
								e.printStackTrace();
							}
					}

				System.out.println("FragmentOne onCreate");
			}

		public void onResume()
			{
				super.onResume();
				System.out.println("FragmentOne onResume");
			}

		@Override
		public void onPause()
			{
				super.onPause();
				System.out.println("FragmentOne onPause");
			}

		@Override
		public void onStop()
			{
				super.onStop();
				System.out.println("FragmentOne onStop");
			}

		public T getData()
			{
				return data;
			}

		public void setData(T data)
			{
				this.data = data;
			}

	}
