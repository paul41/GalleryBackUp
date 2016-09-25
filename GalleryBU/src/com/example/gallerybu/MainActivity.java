package com.example.gallerybu;



import java.net.URI;


import java.util.ArrayList;



import android.net.Uri;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.provider.MediaStore;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	ImageView imV;
	public String imgD;
	final int RESULT_GALLERY=1;
	private DBhelper DbHelper;
	public String xt;
	EditText tv;
	public static final String EMP_ID = "id";
	public static final String EMP_NAME = "name";
	public static final String EMP_AGE = "age";
	public static final String EMP_PHOTO = "photo";
	public ArrayList<Employee> employeeList = new ArrayList<Employee>();
	//private SQLiteDatabase mDb;
	
	 GridView gridView ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gridview_layout);
		Button btm=(Button)findViewById(R.id.button1);
		
		imV=(ImageView)findViewById(R.id.imageView1);
		//Employee e=new Employee(b, n, k)
		
		DbHelper = new DBhelper(this);
		gridView = (GridView) findViewById(R.id.grid_view);
		//Employee employee_One = new Employee(BitmapFactory.decodeResource(
			//	getResources(), R.drawable.employeeone), "Surya", 25);
		btm.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				  BitmapDrawable draw=(BitmapDrawable)imV.getDrawable();
				Bitmap b=draw.getBitmap();
				DbHelper.open();
				DbHelper.Reset();
				tv=(EditText)findViewById(R.id.editText1);
				xt=tv.getText().toString();
					Employee employee_Two = new Employee(b, xt, 23);
					
					
					DbHelper.insertEmpDetails(employee_Two);
				employeeList = DbHelper.retriveallEmpDetails();
				DbHelper.close();
				gridView.setAdapter(new ImageAdapter(MainActivity.this, employeeList));
				
			}
			
		});
		
		
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		
		switch(item.getItemId())
		{
		case R.id.bt:
			Intent i=new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
			startActivityForResult(i, RESULT_GALLERY);
			
			
		}
		return super.onOptionsItemSelected(item);
	}
	@Override
	protected void onActivityResult(int reqC,int resC,Intent data){
		
		super.onActivityResult(reqC, resC, data);
		try{
			if(reqC==RESULT_GALLERY && resC==RESULT_OK && null != data){
			
				Uri u=data.getData();
				String[] FILE={MediaStore.Images.Media.DATA};
				
				Cursor c=getContentResolver().query(u, FILE, null, null, null);
				c.moveToFirst();
				int colI=c.getColumnIndex(FILE[0]);
				String ImageD=c.getString(colI);
				imV.setImageBitmap(BitmapFactory.decodeFile(ImageD));
				c.close();
				imV.setImageBitmap(BitmapFactory.decodeFile(ImageD));
			}
			}catch(Exception e){
				Toast.makeText(this, "TRY AGAIN..!!!", Toast.LENGTH_LONG).show();
			}
		}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
		
	}

}
