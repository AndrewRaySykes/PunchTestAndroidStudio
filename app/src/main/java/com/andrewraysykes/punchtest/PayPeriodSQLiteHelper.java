package com.andrewraysykes.punchtest;

import hirondelle.date4j.DateTime;

import java.util.LinkedList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class PayPeriodSQLiteHelper extends SQLiteOpenHelper {
	
	// DB Version
	
	private static final int DATABASE_VERSION = 1;
	
	// DB Name
	
	private static final String DATABASE_NAME = "PayPeriodDB";
	
	// Table Names
	
	private static final String TABLE_PAY_PERIODS = "payPeriods";
		
	// Column Names
		
	private static final String KEY_ID = "id";
	private static final String KEY_START_DATETIME = "startDateTime";
	private static final String KEY_END_DATETIME = "endDateTime";
	
	private static final String[] COLUMNS = {KEY_ID, KEY_START_DATETIME, KEY_END_DATETIME};
	
	public PayPeriodSQLiteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		
		// SQL FORMATTED STRING MOCKUP:
		// CREATE TABLE tablename (id INTEGER PRIMARY KEY AUTOINCREMENT, startdate TEXT, enddate TEXT)
		
		String CREATE_PAY_PERIOD_TABLE = "CREATE TABLE " + 
				TABLE_PAY_PERIODS + " ( " + 
				KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + 
				KEY_START_DATETIME + " TEXT, " +
				KEY_END_DATETIME + " TEXT )";
		
		db.execSQL(CREATE_PAY_PERIOD_TABLE);
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
		db.execSQL("DROP TABLE IF EXISTS payPeriods");
		
		this.onCreate(db);
	}
	
	public void addPayPeriod(PayPeriod payPeriod) {
		
		Log.d("addPayPeriod", payPeriod.toString());
		
		// 1. get reference to writable DB
		SQLiteDatabase db = this.getWritableDatabase();
		
		// 2. create ContentValues to add key "column"/value
		ContentValues values = new ContentValues();
		
		// get SQL-formatted DateTimes for the start/end times
		values.put(KEY_START_DATETIME, payPeriod.getStartDateTimeForSql());
		values.put(KEY_END_DATETIME, payPeriod.getEndDateTimeForSql());
		
		// 3. insert
		db.insert(TABLE_PAY_PERIODS, 
				null, //nullColumnHack
				values); // keys = column names / values = column values
		
		// 4. close
		db.close();
	}
	
	public PayPeriod getPayPeriod(int id) {
		
		// 1. get reference to readable DB
		SQLiteDatabase db = this.getReadableDatabase();
		
		// 2. build query
		Cursor cursor = db.query(TABLE_PAY_PERIODS, COLUMNS, " " + KEY_ID + " = ?", new String[] {String.valueOf(id)}, null, null, null, null);
		
		// 3. if we got multiple results get the first one
		if (cursor != null) {
			cursor.moveToFirst();
		}
		
		// 4. build PayPeriod object
		PayPeriod payPeriod = new PayPeriod();
		payPeriod.setId(Integer.parseInt(cursor.getString(0)));
		
		DateTime tempStart = new DateTime(cursor.getString(1));
		payPeriod.setStartDateTime(tempStart);
		
		DateTime tempEnd = new DateTime(cursor.getString(2));
		payPeriod.setEndDateTime(tempEnd);
		
		// 5. return PayPeriod
		return payPeriod;
	}
	
	public List<PayPeriod> getAllPayPeriods() {
		List<PayPeriod> payPeriods = new LinkedList<PayPeriod>();
		
		// 1. build the query
		String query = "SELECT  * FROM " + TABLE_PAY_PERIODS;
		
		// 2. get reference to writable DB
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(query, null);
		
		// 3. go over each row, build PayPeriod and add it to list
		PayPeriod payPeriod = null;
		if (cursor.moveToFirst()) {
			do {
				payPeriod = new PayPeriod();
				
				payPeriod.setId(Integer.parseInt(cursor.getString(0)));
				
				DateTime tempStart = new DateTime(cursor.getString(1));
				payPeriod.setStartDateTime(tempStart);
				
				DateTime tempEnd = new DateTime(cursor.getString(2));
				payPeriod.setEndDateTime(tempEnd);
				
				payPeriods.add(payPeriod);
			} while (cursor.moveToNext());
		}
		
		Log.d("getAllPayPeriods()", payPeriods.toString());
		
		// 4. return payPeriods
		return payPeriods;
	}
	
	public int updatePayPeriod(PayPeriod payPeriod) {
		
		// 1. get reference to writable DB
		SQLiteDatabase db = this.getWritableDatabase();
		
		// 2. create ContentValues to add key "column"/value
		ContentValues values = new ContentValues();
		values.put(KEY_START_DATETIME, payPeriod.getStartDateTimeForSql());
		values.put(KEY_END_DATETIME, payPeriod.getEndDateTimeForSql());
		
		// 3. update the row
		int i = db.update(TABLE_PAY_PERIODS, 
				values, 
				KEY_ID + " = ?", //where clause 
				new String[] { String.valueOf(payPeriod.getId()) });  //where args
		
		// 4. close
		db.close();
		
		return i;
	}
	
	public void deletePayPeriod(PayPeriod payPeriod) {
		
		// 1. get reference to writable DB
		SQLiteDatabase db = this.getWritableDatabase();
		
		// 2. delete
		db.delete(TABLE_PAY_PERIODS, 
				KEY_ID + " = ?", 
				new String[] { String.valueOf(payPeriod.getId()) });
		
		// 3. close
		db.close();
		
		Log.d("deletePayPeriod", payPeriod.toString());
	}

}
