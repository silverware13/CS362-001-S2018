/** A JUnit test class to test the class CalDay. */

package calendar;

import org.junit.Test;
import static org.junit.Assert.*;
import calendar.Appt;
import calendar.CalDay;
import java.util.*;


public class CalDayTest{

  //invalid calday
  @Test(timeout = 4000)
  public void test00()  throws Throwable  {
      CalDay day0 = new CalDay();
      assertFalse(day0.isValid());
  }

  //valid calday
  @Test(timeout = 4000)
  public void test01()  throws Throwable  {
      GregorianCalendar someday = new GregorianCalendar(2018, 4, 4);
      CalDay day0 = new CalDay(someday);
      assertTrue(day0.isValid());
  }

  //toString - valid
  @Test(timeout = 4000)
  public void test02()  throws Throwable  {
      GregorianCalendar someday = new GregorianCalendar(2018, 5, 10);
      CalDay day0 = new CalDay(someday);
      String string0 = day0.toString();
      String string1 = "\t --- 7/10/2018 --- \n --- -------- Appointments ------------ --- \n\n";
      assertTrue(string0.equals(string1));
  }

  //toString - valid - with two appointments
  @Test(timeout = 4000)
  public void test03()  throws Throwable  {
      GregorianCalendar someday = new GregorianCalendar(2018, 5, 10);
      CalDay day0 = new CalDay(someday);
      Appt appt0 = new Appt(8, 5, 7, 6, 2018, "Meeting", "This is a meeting", "work@gmail.com");
      Appt appt1 = new Appt(10, 5, 7, 6, 2018, "Meeting2", "This is a meeting", "work@gmail.com");
      day0.addAppt(appt0);
      day0.addAppt(appt1);
      String string0 = day0.toString();
      assertEquals("\t --- 7/10/2018 --- \n --- -------- Appointments ------------ --- \n" + "\t6/7/2018 at 8:5am ,Meeting, This is a meeting \n" + "\t6/7/2018 at 10:5am ,Meeting2, This is a meeting\n \n", string0);
  }

  //toString - valid - with two appointments (wrong order)
  @Test(timeout = 4000)
  public void test04()  throws Throwable  {
      GregorianCalendar someday = new GregorianCalendar(2018, 5, 10);
      CalDay day0 = new CalDay(someday);
      Appt appt0 = new Appt(10, 5, 7, 6, 2018, "Meeting2", "This is a meeting", "work@gmail.com");
      Appt appt1 = new Appt(8, 5, 7, 6, 2018, "Meeting", "This is a meeting", "work@gmail.com");
      day0.addAppt(appt0);
      day0.addAppt(appt1);
      String string0 = day0.toString();
      assertEquals("\t --- 7/10/2018 --- \n --- -------- Appointments ------------ --- \n" + "\t6/7/2018 at 8:5am ,Meeting, This is a meeting \n" + "\t6/7/2018 at 10:5am ,Meeting2, This is a meeting\n \n", string0);
  }

  //toString - invalid
  @Test(timeout = 4000)
  public void test05()  throws Throwable  {
      CalDay day0 = new CalDay();
      String string0 = day0.toString();
      String string1 = "\t --- 7/10/2018 --- \n --- -------- Appointments ------------ --- \n\n";
      assertFalse(string0.equals(string1));
  }

  //toString - valid - with one invalid appointment
  @Test(timeout = 4000)
  public void test06()  throws Throwable  {
      GregorianCalendar someday = new GregorianCalendar(2018, 5, 10);
      CalDay day0 = new CalDay(someday);
      Appt appt0 = new Appt(8, 5, -100, 6, 2018, "Meeting", "This is a meeting", "work@gmail.com");
      appt0.setValid();
      day0.addAppt(appt0);
      String string0 = day0.toString();
      assertEquals("\t --- 7/10/2018 --- \n --- -------- Appointments ------------ --- \n\n", string0);
  }

  //get size of appointments
  @Test(timeout = 4000)
  public void test07()  throws Throwable  {
      GregorianCalendar someday = new GregorianCalendar(2018, 5, 10);
      CalDay day0 = new CalDay(someday);
      Appt appt0 = new Appt(8, 5, 7, 6, 2018, "Meeting", "This is a meeting", "work@gmail.com");
      Appt appt1 = new Appt(10, 5, 7, 6, 2018, "Meeting2", "This is a meeting", "work@gmail.com");
      day0.addAppt(appt0);
      day0.addAppt(appt1);
      assertEquals(day0.getSizeAppts(), 2);
  }

  //get all appointment info
  @Test(timeout = 4000)
  public void test08()  throws Throwable  {
      GregorianCalendar someday = new GregorianCalendar(2018, 5, 10);
      CalDay day0 = new CalDay(someday);
      Appt appt0 = new Appt(7, 6, 2018, "Meeting No Time", "This is a meeting with no set time.", "work@gmail.com");
      Appt appt1 = new Appt(0, 5, 7, 6, 2018, "Meeting Early Morning", "This is a meeting.", "work@gmail.com");
      Appt appt2 = new Appt(20, 10, 7, 6, 2018, "Meeting Late At Night", "This is a meeting.", "work@gmail.com");
      day0.addAppt(appt0);
      day0.addAppt(appt1);
      day0.addAppt(appt2);
      String string0 = day0.getFullInfomrationApp(day0);
      assertEquals("6-10-2018 \n\tMeeting No Time This is a meeting with no set time. \n\t12:05AM Meeting Early Morning This is a meeting. \n\t8:10PM Meeting Late At Night This is a meeting. ", string0);
  }
}
