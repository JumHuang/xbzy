package com.JumHuang.xbzy.Config;

import java.io.Serializable;

public class PictureInfo implements Serializable
	{
		private static final long serialVersionUID = 13333333333L;
		private String url;
		private int imgResource;

		public String getUrl()
			{
				return url;
			}

		public void setUrl(String url)
			{
				this.url = url;
			}

		public int getImgResource()
			{
				return imgResource;
			}

		public void setImgResource(int imgResource)
			{
				this.imgResource = imgResource;
			}

	}
