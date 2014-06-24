package com.andrewraysykes.punchtest;

import hirondelle.date4j.DateTime;

public class PayPeriod {
	
	private int id;
	private DateTime startDateTime;
	private DateTime endDateTime;
	
	public PayPeriod(){}
	
	public PayPeriod(DateTime startDateTime, DateTime endDateTime) {
		super();
		this.startDateTime = startDateTime;
		this.endDateTime = endDateTime;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public DateTime getStartDateTime() {
		return startDateTime;
	}
	
	public String getStartDateTimeForSql() {
		return startDateTime.format(PunchConstants.FORMAT_FOR_SQL);
	}
	
	public void setStartDateTime(DateTime startDateTime) {
		this.startDateTime = startDateTime;
	}
	
	public DateTime getEndDateTime() {
		return endDateTime;
	}
	
	public String getEndDateTimeForSql() {
		return endDateTime.format(PunchConstants.FORMAT_FOR_SQL);
	}
	
	public void setEndDateTime(DateTime endDateTime) {
		this.endDateTime = endDateTime;
	}
	
	@Override
	public String toString() {
		return "PayPeriod [id=" + id + 
				", startDateTime=" + startDateTime.format(PunchConstants.FORMAT_FOR_SQL)  +
				", endDateTime=" + endDateTime.format(PunchConstants.FORMAT_FOR_SQL) +
				"]";
	}

}
