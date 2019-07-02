package com.JumHuang.xbzy.Config;

/**
 * @author ThinkPad
 *	功能描述：常量工具类
 */
import com.JumHuang.xbzy.Activity.FunctionActivity;
import com.JumHuang.xbzy.Activity.OneselfActivity;
import com.JumHuang.xbzy.Activity.XbzyActivity;
import com.JumHuang.xbzy.R;

public class Constant
	{
		public static final class ConValue
			{
				/**
				 * Tab选项卡的图标
				 */
				public static int mImageViewArray[] = {
						R.drawable.tab_icon1,
						R.drawable.tab_icon2,
						R.drawable.tab_icon3
					};

				/**
				 * Tab选项卡的文字
				 */
				public static String mTextViewArray[] = {"主页", "功能", "个人"};

				/** 
				 * 每一个Tab界面 
				 */ 
				public static Class<?> mTabClassArray[] = {
						XbzyActivity.class,
						FunctionActivity.class,
						OneselfActivity.class
					};
			}
	}
