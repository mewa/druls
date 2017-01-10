package com.sample;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.sample.Car;

public class Reservation {
	public Car.Klass carClass;
	public Car car;
	public Client client;
	public GregorianCalendar startDate;
	public GregorianCalendar endDate;
	public Long length;
	public Float price;
	public Float eqPrice;
	public Boolean discount;
	public int numEq = 0;
	public Float distancePrice;
	public boolean gone = false;

	public Reservation(Client client,
			Date startDate, Date endDate) {
		this.client = client;
		
		this.startDate = new GregorianCalendar();
		this.startDate.setTime(startDate);
		
		this.endDate = new GregorianCalendar();
		this.endDate.setTime(endDate);
	}
}
