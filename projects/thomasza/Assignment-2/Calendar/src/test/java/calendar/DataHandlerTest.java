
/** A JUnit test class to test the class DataHandler. */


package calendar;

import org.junit.Test;
import static org.junit.Assert.*;
import calendar.Appt;
import calendar.CalDay;
import calendar.DataHandler;

import java.util.GregorianCalendar;
import java.util.LinkedList;


public class DataHandlerTest{

  //save
  @Test(timeout = 4000)
  public void test00()  throws Throwable  {
      DataHandler data0 = new DataHandler();
      assertTrue(data0.save());
  }

  //save appointments - autosave on
  @Test(timeout = 4000)
  public void test01()  throws Throwable  {
      GregorianCalendar someday = new GregorianCalendar(2018, 5, 10);
      CalDay day0 = new CalDay(someday);
      Appt appt0 = new Appt(8, 5, 7, 6, 2018, "Meeting", "This is a meeting", "work@gmail.com");
      day0.addAppt(appt0);
      DataHandler data0 = new DataHandler("calendar_named.xml");
      assertTrue(data0.saveAppt(appt0));
  }

  //save appointments - autosave off
  @Test(timeout = 4000)
  public void test02()  throws Throwable  {
      GregorianCalendar someday = new GregorianCalendar(2018, 5, 10);
      CalDay day0 = new CalDay(someday);
      Appt appt0 = new Appt(8, 5, 7, 6, 2018, "Meeting", "This is a meeting", "work@gmail.com");
      day0.addAppt(appt0);
      DataHandler data0 = new DataHandler("calendar_named.xml", false);
      assertTrue(data0.saveAppt(appt0));
  }

  //save appointments - recurring
  @Test(timeout = 4000)
  public void test03()  throws Throwable  {
      GregorianCalendar someday = new GregorianCalendar(2018, 5, 10);
      CalDay day0 = new CalDay(someday);
      Appt appt0 = new Appt(8, 5, 7, 6, 2018, "Meeting", "This is a meeting", "work@gmail.com");
      int[] recurDaysArr = {2, 3, 5};
      appt0.setRecurrence(recurDaysArr, Appt.RECUR_BY_WEEKLY, 2, Appt.RECUR_NUMBER_FOREVER);
      day0.addAppt(appt0);
      DataHandler data0 = new DataHandler("calendar_named.xml");
      assertTrue(data0.saveAppt(appt0));
  }

  //save appointments - invalid appointment
  @Test(timeout = 4000)
  public void test04()  throws Throwable  {
      GregorianCalendar someday = new GregorianCalendar(2018, 5, 10);
      CalDay day0 = new CalDay(someday);
      Appt appt0 = new Appt(-100, 5, 7, 6, 2018, "Meeting", "This is a meeting", "work@gmail.com");
      appt0.setValid();
      day0.addAppt(appt0);
      DataHandler data0 = new DataHandler("calendar_named.xml");
      assertFalse(data0.saveAppt(appt0));
  }

    //delete appointments - autosave on
    @Test(timeout = 4000)
    public void test05()  throws Throwable  {
        GregorianCalendar someday = new GregorianCalendar(2018, 5, 10);
        CalDay day0 = new CalDay(someday);
        Appt appt0 = new Appt(8, 5, 7, 6, 2018, "Meeting", "This is a meeting", "work@gmail.com");
        day0.addAppt(appt0);
        DataHandler data0 = new DataHandler("calendar_named.xml");
        data0.saveAppt(appt0);
        assertTrue(data0.deleteAppt(appt0));
    }

    //delete appointments - autosave off
    @Test(timeout = 4000)
    public void test06()  throws Throwable  {
        GregorianCalendar someday = new GregorianCalendar(2018, 5, 10);
        CalDay day0 = new CalDay(someday);
        Appt appt0 = new Appt(8, 5, 7, 6, 2018, "Meeting", "This is a meeting", "work@gmail.com");
        day0.addAppt(appt0);
        DataHandler data0 = new DataHandler("calendar_named.xml", false);
        data0.saveAppt(appt0);
        assertTrue(data0.deleteAppt(appt0));
    }

    //delete appointments - appointment wasn't saved
    @Test(timeout = 4000)
    public void test07()  throws Throwable  {
        GregorianCalendar someday = new GregorianCalendar(2018, 5, 10);
        CalDay day0 = new CalDay(someday);
        Appt appt0 = new Appt(8, 5, 7, 6, 2018, "Meeting", "This is a meeting", "work@gmail.com");
        day0.addAppt(appt0);
        DataHandler data0 = new DataHandler("calendar_named.xml", false);
        assertFalse(data0.deleteAppt(appt0));
    }

    //delete appointments - appointment is not valid
    @Test(timeout = 4000)
    public void test08()  throws Throwable  {
        GregorianCalendar someday = new GregorianCalendar(2018, 5, 10);
        CalDay day0 = new CalDay(someday);
        Appt appt0 = new Appt(-100, 5, 7, 6, 2018, "Meeting", "This is a meeting", "work@gmail.com");
        appt0.setValid();
        day0.addAppt(appt0);
        DataHandler data0 = new DataHandler("calendar_named.xml", false);
        data0.saveAppt(appt0);
        assertFalse(data0.deleteAppt(appt0));
    }

    //get appointment range - first day is after last day as input
    @Test(expected = DateOutOfRangeException.class) 
    public void test9()  throws Throwable  {
        DataHandler data0 = new DataHandler("calendar_range.xml");
        GregorianCalendar firstday = new GregorianCalendar(2018, 5, 5);
        GregorianCalendar lastday = new GregorianCalendar(2018, 5, 10);
        LinkedList<CalDay> calDays = new LinkedList<CalDay>();
        calDays = (LinkedList<CalDay>) data0.getApptRange(lastday, firstday);
    }

    //get appointment range - first day is after last day
    @Test(timeout = 4000)
    public void test10()  throws Throwable  {
        DataHandler data0 = new DataHandler("calendar_range.xml");
        GregorianCalendar firstday = new GregorianCalendar(2018, 1, 1);
        GregorianCalendar lastday = new GregorianCalendar(2018, 10, 20);
        Appt appt0 = new Appt(8, 10, 1, 5, 2050, "Meeting", "This is a meeting", "work@gmail.com");
        int[] recurDaysArr = {2,3,4};
        appt0.setRecurrence(recurDaysArr, Appt.RECUR_BY_YEARLY, 2, Appt.RECUR_NUMBER_FOREVER);
        appt0.setValid();
        data0.saveAppt(appt0);
        LinkedList<CalDay> calDays = new LinkedList<CalDay>();
        calDays = (LinkedList<CalDay>) data0.getApptRange(firstday, lastday);
        assertNotNull(calDays);
    }

    //get appointment range - first day is before start
    @Test(timeout = 4000)
    public void test11()  throws Throwable  {
        DataHandler data0 = new DataHandler("calendar_range.xml");
        GregorianCalendar firstday = new GregorianCalendar(2018, 1, 1);
        GregorianCalendar lastday = new GregorianCalendar(2050, 10, 20);
        Appt appt0 = new Appt(8, 10, 1, 5, 2000, "Meeting", "This is a meeting", "work@gmail.com");
        int[] recurDaysArr = {2,3,4};
        appt0.setRecurrence(recurDaysArr, Appt.RECUR_BY_YEARLY, 2, Appt.RECUR_NUMBER_FOREVER);
        appt0.setValid();
        data0.saveAppt(appt0);
        LinkedList<CalDay> calDays = new LinkedList<CalDay>();
        calDays = (LinkedList<CalDay>) data0.getApptRange(firstday, lastday);
        assertNotNull(calDays);
    }

    //get appointment range - we don't define the reccurDays
    @Test(timeout = 4000)
    public void test12()  throws Throwable  {
        DataHandler data0 = new DataHandler("calendar_range.xml");
        GregorianCalendar firstday = new GregorianCalendar(2018, 1, 1);
        GregorianCalendar lastday = new GregorianCalendar(2018, 10, 20);
        Appt appt0 = new Appt(8, 10, 1, 5, 2000, "Meeting", "This is a meeting", "work@gmail.com");
        int[] recurDaysArr = {};
        appt0.setRecurrence(recurDaysArr, Appt.RECUR_BY_MONTHLY, 2, Appt.RECUR_NUMBER_FOREVER);
        appt0.setValid();
        data0.saveAppt(appt0);
        LinkedList<CalDay> calDays = new LinkedList<CalDay>();
        calDays = (LinkedList<CalDay>) data0.getApptRange(firstday, lastday);
        assertNotNull(calDays);
    }

    //get appointment range - reccurDays are outside the valid range
    @Test(timeout = 4000)
    public void test13()  throws Throwable  {
        DataHandler data0 = new DataHandler("calendar_range.xml");
        GregorianCalendar firstday = new GregorianCalendar(2018, 1, 1);
        GregorianCalendar lastday = new GregorianCalendar(2018, 10, 20);
        Appt appt0 = new Appt(8, 10, 1, 5, 2000, "Meeting", "This is a meeting", "work@gmail.com");
        int[] recurDaysArr = {100, 200, 300};
        appt0.setRecurrence(recurDaysArr, Appt.RECUR_BY_MONTHLY, 2, Appt.RECUR_NUMBER_FOREVER);
        appt0.setValid();
        data0.saveAppt(appt0);
        LinkedList<CalDay> calDays = new LinkedList<CalDay>();
        calDays = (LinkedList<CalDay>) data0.getApptRange(firstday, lastday);
        assertNotNull(calDays);
    }
}
