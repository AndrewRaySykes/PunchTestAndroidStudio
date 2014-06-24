package com.andrewraysykes.punchtest;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import hirondelle.date4j.DateTime;

public class PayPeriodEntryActivity extends Activity {

    protected DatePicker mDatePicker;
    protected EditText mEditTextDays;
    protected TextView mTextPreview;
    protected Button mButtonSave;
    protected SharedPreferences mSharedPreferences;

    protected DateTime mPayPeriodStart;
    protected DateTime mPayPeriodEnd;
    protected int mCurrentPayPeriodId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_period_entry);

        mDatePicker = (DatePicker) findViewById(R.id.payperiod_datepicker_first_day);
        mEditTextDays = (EditText) findViewById(R.id.payperiod_edit_days_in_period);
        mTextPreview = (TextView) findViewById(R.id.payperiod_text_preview_pay_period);
        mButtonSave = (Button) findViewById(R.id.payperiod_btn_save_pay_period);

        mSharedPreferences = getSharedPreferences(PunchConstants.PREFS_NAME, MODE_PRIVATE);

        CalculateDate();

        mTextPreview.setText("Start:" + mPayPeriodStart + " End: " + mPayPeriodEnd);


    }

    private void CalculateDate() {
        mPayPeriodStart = DateTime.forDateOnly(mDatePicker.getYear(), mDatePicker.getMonth(), mDatePicker.getDayOfMonth());
        mPayPeriodEnd = mPayPeriodStart.plusDays(Integer.getInteger(mEditTextDays.getText().toString()));

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.pay_period_entry, menu);
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
