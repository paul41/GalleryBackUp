package com.example.gallerybu;

import java.util.ArrayList;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ImageAdapter extends BaseAdapter {
	private Context mContext;
	ArrayList<Employee> employeeList = new ArrayList<Employee>();

	// Constructor
	public ImageAdapter(Context c, ArrayList<Employee> employeeList) {
		mContext = c;
		this.employeeList = employeeList;
	}

	@Override
	public int getCount() {
		return employeeList.size();

	}

	@Override
	public Object getItem(int position) {
		return employeeList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		
		View row=convertView ;
		RecordHolder holder = null;
		//convertView= inflater.inflate(layoutResourceId, parent, false);
		if (row == null) {
			LayoutInflater inf=((Activity) mContext).getLayoutInflater();
			row=inf.inflate(R.layout.activity_main,parent,false);
			holder = new RecordHolder();
			holder.txtTitle = (TextView) row.findViewById(R.id.item_text);
			holder.txtTitle1=(TextView) row.findViewById(R.id.item_text2);
			holder.imageItem = (ImageView) row.findViewById(R.id.item_image);
			row.setTag(holder);
		} else {
			holder = (RecordHolder) row.getTag();
		}
		
		//row=new View(mContext);
		//holder = new RecordHolder();
		
	/*	ImageView imageView = new ImageView(mContext);
		TextView tv=new TextView(mContext);
		tv.setText(employeeList.get(position).getName());
		imageView.setImageBitmap(employeeList.get(position).getBitmap());*/
		
		//imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
		//imageView.setLayoutParams(new GridView.LayoutParams(100, 100));
		//else{
			//row=(View)convertView;
		//}
		 //row.addView(imageView);
		//((ViewGroup) convertView).addView(tv);
		Employee item = employeeList.get(position);
		holder.txtTitle.setText(item.getName());
		holder.txtTitle1.setText(Integer.toString(item.getAge()));
		holder.imageItem.setImageBitmap(item.getBitmap());
		return row;
	}
	static class RecordHolder {
		TextView txtTitle,txtTitle1;
		ImageView imageItem;
		

	}

}
