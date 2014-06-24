package com.andrewraysykes.punchtest;

import hirondelle.date4j.DateTime;

public class PayTime {
	
	private int id;
	private int payPeriodId;
	private DateTime punchIn;
	private DateTime punchOut;
	
	public PayTime(){}
	
	public PayTime(int payPeriodId, DateTime punchIn, DateTime punchOut) {
		super();
		this.payPeriodId = payPeriodId;
		this.punchIn = punchIn;
		this.punchOut = punchOut;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getPayPeriodId() {
		return payPeriodId;
	}
	
	public void setPayPeriodId(int payPeriodId) {
		this.payPeriodId = payPeriodId;
	}
	
	public DateTime getPunchIn() {
		return punchIn;
	}
	
	public void setPunchIn(DateTime punchIn) {
		this.punchIn = punchIn;
	}
	
	public DateTime getPunchOut() {
		return punchOut;
	}
	
	public void setPunchOut(DateTime punchOut) {
		this.punchOut = punchOut;
	}
	
	@Override
	public String toString() {
		return "PayTime [id=" + id + 
				", payPeriodId=" + payPeriodId + 
				", punchIn=" + punchIn.format(PunchConstants.FORMAT_FOR_SQL) +
				", punchOut=" + punchIn.format(PunchConstants.FORMAT_FOR_SQL) +
				"]";
	}

}
