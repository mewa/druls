package com.sample;

import java.util.Date;
import java.util.GregorianCalendar;

public class ReservationReturn {
	public Client client;
	public GregorianCalendar returnDate;
	public Float distancePriceTotal;
	public Integer distance;
	public int docsMissing = 0;
	public int capsMissing = 0;

	public ReservationReturn(Client client, int distance, Date retDate) {
		this.client = client;

		this.distance = distance;
		
		this.returnDate = new GregorianCalendar();
		this.returnDate.setTime(retDate);
	}

}
