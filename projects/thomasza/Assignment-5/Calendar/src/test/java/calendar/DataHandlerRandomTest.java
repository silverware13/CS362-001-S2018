package calendar;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import java.util.Calendar;
import java.util.Random;
import calendar.Appt;
import calendar.CalDay;
import java.util.*;
import java.io.*;
import java.lang.reflect.*;

import java.util.Random;

import org.junit.Test;


import static org.junit.Assert.*;



/**
 * Random Test Generator  for DataHandler class.
 */

public class DataHandlerRandomTest {

    /**
     * Generate Random Tests that tests DataHandler Class.
     */

	 private static final long TestTimeout = 60 * 500 * 1;

	 //clean up file before test
     @Before
     public void setUp() {
         File testfile = new File("calendar_test.xml");
         testfile.delete();
     }

     //clean up file after test
     @After
     public void tearDown() {
         File testfile = new File("calendar_test.xml");
         testfile.delete();
     }

	 @Test
	  public void randomtest()  throws Throwable  {
		  long startTime = Calendar.getInstance().getTimeInMillis();
		  long elapsed = Calendar.getInstance().getTimeInMillis() - startTime;

		  System.out.println("Start testing...");

		  for (int iteration = 0; elapsed < TestTimeout; iteration++) {
			  try{

				 long randomseed =System.currentTimeMillis(); //10
 				 Random random = new Random(randomseed);

				 //random autosave setting
				 boolean autosaveSet=ValuesGenerator.getBoolean(.50f, random);

				 //random start
 				 int startDay=ValuesGenerator.getRandomIntBetween(random, 0, 32);
 				 int startMonth=ValuesGenerator.getRandomIntBetween(random, 0, 13);
 				 int startYear=ValuesGenerator.getRandomIntBetween(random, 2018, 2019);

				 //random end
 				 int endDay=ValuesGenerator.getRandomIntBetween(random, 0, 32);
 				 int endMonth=ValuesGenerator.getRandomIntBetween(random, 0, 13);
 				 int endYear=ValuesGenerator.getRandomIntBetween(random, 2019, 2020);

				 //random number of appointments
				 int numAppts=ValuesGenerator.getRandomIntBetween(random, 0, 10);

				 //setup the random firstday and lastday
				 DataHandler data0 = new DataHandler("calendar_test.xml", autosaveSet);
				 GregorianCalendar firstday = new GregorianCalendar(startYear, startMonth, startDay);
				 GregorianCalendar lastday = new GregorianCalendar(endYear, endMonth, endDay);

				 //one in ten data handlers are not valid
				 if(ValuesGenerator.getBoolean(.10f, random)) {
				 	Field f1 = data0.getClass().getDeclaredField("valid");
		         	f1.setAccessible(true);
		         	f1.set(data0, false);
			 	 }

				 //random number of appointments with random recurrence
				 while(numAppts > 0){
					//each appointment is at a random time
					int apptHour=ValuesGenerator.getRandomIntBetween(random, -1, 24);
					int apptMinute=ValuesGenerator.getRandomIntBetween(random, -1, 60);
					int apptDay=ValuesGenerator.getRandomIntBetween(random, 0, 32);
					int apptMonth=ValuesGenerator.getRandomIntBetween(random, 0, 13);
					int apptYear=ValuesGenerator.getRandomIntBetween(random, 2018, 2020);

					Appt appt0 = new Appt(apptHour, apptMinute, apptDay, apptMonth, apptYear, "Event", "This is an event.", "home@yahoo.com");

					//recurrence
					int sizeArray=ValuesGenerator.getRandomIntBetween(random, 0, 8);
					int[] recurDays=ValuesGenerator.generateRandomArray(random, sizeArray);
					int recur=ApptRandomTest.RandomSelectRecur(random);
					int recurIncrement = ValuesGenerator.RandInt(random);
					int recurNumber=ApptRandomTest.RandomSelectRecurForEverNever(random);
					appt0.setRecurrence(recurDays, recur, recurIncrement, recurNumber);

				 	appt0.setValid();

					//only save half of the appointments
					if(ValuesGenerator.getBoolean(.50f, random)) {
				 		data0.saveAppt(appt0);
					}

					//delete one out of every ten appointments
					if(ValuesGenerator.getBoolean(.10f, random)) {
						data0.deleteAppt(appt0);
					}

					numAppts--;
			 	 }

				 //getApptRange test
				 LinkedList<CalDay> calDays = new LinkedList<CalDay>();
				 calDays = (LinkedList<CalDay>) data0.getApptRange(firstday, lastday);

				 //show elapsed time
				 elapsed = (Calendar.getInstance().getTimeInMillis() - startTime);
				 if(iteration!=0)
					 System.out.println("elapsed time: "+ elapsed + " of "+TestTimeout);

	 			}catch(DateOutOfRangeException e){
	 			}
			 }

			 System.out.println("Done testing...");
		 }
}
