package com.andrewraysykes.punchtest;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    public static final String TAG = MainActivity.class.getSimpleName();


    protected PayPeriod mCurrentPayPeriod = new PayPeriod();
    protected int mCurrentPayPeriodId = 0;
    protected Button mEnterTimesButton;
    protected SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mSharedPreferences = getSharedPreferences(PunchConstants.PREFS_NAME, MODE_PRIVATE);
        mSharedPreferences.getInt(PunchConstants.CURRENT_PAY_PERIOD_ID, mCurrentPayPeriodId);

        if (mCurrentPayPeriodId < 1) {
            Intent intent = new Intent(MainActivity.this, PayPeriodEntryActivity.class);
            startActivity(intent);
        } else {
            // TODO: DB should already be active to pull the current pay period for this activity.
        }


        setContentView(R.layout.activity_main);


        mEnterTimesButton = (Button) findViewById(R.id.main_btn_time_entry);

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
