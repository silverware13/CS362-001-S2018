package calendar;

import java.util.Calendar;
import java.util.Random;
import static org.junit.Assert.*;
import calendar.Appt;
import calendar.CalDay;
import java.util.*;

import org.junit.Test;


import static org.junit.Assert.*;



/**
 * Random Test Generator  for CalDay class.
 */

public class CalDayRandomTest {
	/**
	 * Generate Random Tests that tests CalDay Class.
	 */

	private static final long TestTimeout = 15 * 500 * 1;

	@Test
	  public void randomtest()  throws Throwable  {
		 long startTime = Calendar.getInstance().getTimeInMillis();
 		 long elapsed = Calendar.getInstance().getTimeInMillis() - startTime;

 		 System.out.println("Start testing...");

 		try{
 			for (int iteration = 0; elapsed < TestTimeout; iteration++) {
		  		long randomseed = System.currentTimeMillis(); //10
		  		Random random = new Random(randomseed);

		  		int startHour=ValuesGenerator.getRandomIntBetween(random, -1, 24);
		  		int startMinute=ValuesGenerator.getRandomIntBetween(random, -1, 60);
		  		int startDay=ValuesGenerator.getRandomIntBetween(random, 0, 32);
		  		int startMonth=ValuesGenerator.getRandomIntBetween(random, 0, 13);
		  		int startYear=ValuesGenerator.getRandomIntBetween(random, -100, 2018);

		  		GregorianCalendar someday = new GregorianCalendar(startYear, startMonth, startDay);
	      		CalDay day0 = new CalDay(someday);
	      		Appt appt0 = new Appt(startHour, startMinute, startDay, startMonth, startYear, "Meeting", "This is a meeting", "work@gmail.com");
		  		startHour=ValuesGenerator.getRandomIntBetween(random, -1, 24);
		  		startMinute=ValuesGenerator.getRandomIntBetween(random, -1, 60);
		  		Appt appt1 = new Appt(startHour, startMinute, startDay, startMonth, startYear, "Meeting", "This is a meeting", "work@gmail.com");
		  		startHour=ValuesGenerator.getRandomIntBetween(random, -1, 24);
		  		startMinute=ValuesGenerator.getRandomIntBetween(random, -1, 60);
		  		Appt appt2 = new Appt(startHour, startMinute, startDay, startMonth, startYear, "Meeting", "This is a meeting", "work@gmail.com");
		  		appt0.setValid();
		  		appt1.setValid();
		  		appt2.setValid();
		  		day0.addAppt(appt0);
	      		day0.addAppt(appt1);
		  		day0.addAppt(appt2);

				elapsed = (Calendar.getInstance().getTimeInMillis() - startTime);
			   	if((iteration%10000)==0 && iteration!=0 )
					System.out.println("elapsed time: "+ elapsed + " of "+TestTimeout);
				}

	}catch(NullPointerException e){
	}

	System.out.println("Done testing...");
 	}
}
