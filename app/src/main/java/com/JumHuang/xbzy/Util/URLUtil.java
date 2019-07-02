package com.JumHuang.xbzy.Util;

import com.JumHuang.xbzy.Config.Const;

public class URLUtil
{
	public static String getUrl(int newsType)
	{
		String urlStr = "";
		switch (newsType)
		{
			case Const.TYPE_XBZY:
				urlStr = Const.URL_XBZY;
				break;
			case Const.TYPE_BAIDU:
				urlStr = Const.URL_BAIDU;
				break;
			case Const.TYPE_SD:
				urlStr = Const.URL_SD;
				break;
			case Const.TYPE_MASTER:
				urlStr = Const.URL_MASTER;
				break;
		}
		return urlStr;
	}
}
