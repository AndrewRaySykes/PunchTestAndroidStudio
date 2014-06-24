package com.andrewraysykes.punchtest;

import hirondelle.date4j.DateTime;

import java.util.TimeZone;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
	
	public static final String TAG = MainActivity.class.getSimpleName();
	
	public static final String CURRENT_PAY_PERIOD_ID = "currentPayPeriodId";
	
	protected PayPeriod mCurrentPayPeriod = new PayPeriod();
	protected int mCurrentPayPeriodId = 1;
	protected Button mEnterTimesButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	
		setContentView(R.layout.activity_main);
		
		PayPeriodSQLiteHelper db = new PayPeriodSQLiteHelper(this);
		
		DateTime testdt1 = DateTime.now(TimeZone.getDefault());
		DateTime testdt2 = DateTime.now(TimeZone.getDefault());
		
		db.addPayPeriod(new PayPeriod(testdt1, testdt2));
						
		mCurrentPayPeriod = db.getPayPeriod(mCurrentPayPeriodId);
		
		Log.i(TAG, mCurrentPayPeriod.toString());
		
		mEnterTimesButton = (Button)findViewById(R.id.main_btn_time_entry);
		
		mEnterTimesButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, TimeEntryActivity.class);
				startActivity(intent);
			}
		});
		
		

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	

}
