package com.JumHuang.xbzy.Setting;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import com.JumHuang.xbzy.R;
import java.util.ArrayList;
import java.util.HashMap;

public class BackgroundChooseActivity extends Activity
{
	private String texts[]=null;
	private int images[]=null;
	private GridView gridview;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.setting_backgroundchoose);

		images = new int[]{R.drawable.black,R.drawable.gold,R.drawable.green,R.drawable.lightblue,R.drawable.orange,R.drawable.pinck,R.drawable.red,R.drawable.blue,R.drawable.white};
		texts = new String[]{"黑色","金色","绿色","浅蓝","橙色","粉色","红色","蓝色","白色"};

		GridView gridview=(GridView)findViewById(R.id.setting_bc_gridview);
		ArrayList<HashMap<String,Object>>lstImageItem=new ArrayList<HashMap<String,Object>>();
		for (int i=0;i < texts.length;i++)
		{
			HashMap<String,Object>map=new HashMap<String,Object>();
			map.put("itemImage", images[i]);
			map.put("itemText", texts[i]);
			lstImageItem.add(map);
		}
		SimpleAdapter saImageItems=new SimpleAdapter(this, lstImageItem, R.layout.gridview_item, new String[]{"itemImage","itemText"}, new int[]{R.id.gridview_item_image,R.id.gridview_item_text});
		gridview.setAdapter(saImageItems);
		gridview.setOnItemClickListener(new ItemClickListener());

	}

	class ItemClickListener implements OnItemClickListener
	{
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long rowid)
		{
			HashMap<String,Object>item=(HashMap<String,Object>)parent.getItemAtPosition(position);
			String itemText=(String)item.get("itemText");
			Object object=item.get("itemImage");
			switch (images[position])
			{
				case R.drawable.black:
					break;
				case R.drawable.gold:
					break;
				case R.drawable.green:
					break;
				case R.drawable.lightblue:
					break;
				case R.drawable.orange:
					break;
				case R.drawable.pinck:
					break;
				case R.drawable.red:
					break;
				case R.drawable.blue:
					break;
				case R.drawable.white:
					break;
			}
		}
	}
}
