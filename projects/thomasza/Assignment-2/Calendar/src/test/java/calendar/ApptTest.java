/** A JUnit test class to test the class ApptTest. */

package calendar;

import org.junit.Test;
import java.util.Arrays;
import static org.junit.Assert.*;
import calendar.Appt;
import calendar.CalendarUtil;


public class ApptTest  {

  //valid full date and time
  @Test(timeout = 4000)
  public void test00()  throws Throwable  {
      Appt appt0 = new Appt(15, 30, 12, 14, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
      String string0 = appt0.toString();
      assertEquals(2, appt0.getRecurBy());
      assertFalse(appt0.isRecurring());
      assertEquals("\t14/12/2018 at 3:30pm ,Birthday Party, This is my birthday party\n", string0);
      assertEquals(0, appt0.getRecurIncrement());
      appt0.setValid();
      assertTrue(appt0.getValid());
  }

  //valid date with no minute or hour
  @Test(timeout = 4000)
  public void test01()  throws Throwable  {
      Appt appt0 = new Appt(12, 14, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
      String string0 = appt0.toString();
      assertEquals(2, appt0.getRecurBy());
      assertFalse(appt0.isRecurring());
      assertEquals("\t14/12/2018 at -1:-1am ,Birthday Party, This is my birthday party\n", string0);
      assertEquals(0, appt0.getRecurIncrement());
      appt0.setValid();
      assertTrue(appt0.getValid());
  }

  //valid date
  @Test(timeout = 4000)
  public void test02()  throws Throwable  {
      Appt appt0 = new Appt(5, 5, 10, 12, 2018, "Meeting", "This is a meeting", "work@gmail.com");
      appt0.setValid();
      assertTrue(appt0.getValid());
  }

  //valid date 2
  @Test(timeout = 4000)
  public void test03()  throws Throwable  {
      Appt appt0 = new Appt(10, 10, 5, 6, 2020, "Meeting", "This is a meeting", "work@gmail.com");
      appt0.setValid();
      assertTrue(appt0.getValid());
  }

  //invalid min - low
  @Test(timeout = 4000)
  public void test04()  throws Throwable  {
      Appt appt0 = new Appt(5, -5, 5, 5, 5, "Meeting", "This is a meeting", "work@gmail.com");
      appt0.setValid();
      assertFalse(appt0.getValid());
  }

  //invalid hour - low
  @Test(timeout = 4000)
  public void test05()  throws Throwable  {
      Appt appt0 = new Appt(-5, 5, 5, 5, 5, "Meeting", "This is a meeting", "work@gmail.com");
      appt0.setValid();
      assertFalse(appt0.getValid());
  }

  //invalid day - low
  @Test(timeout = 4000)
  public void test06()  throws Throwable  {
      Appt appt0 = new Appt(5, 5, -5, 5, 5, "Meeting", "This is a meeting", "work@gmail.com");
      appt0.setValid();
      assertFalse(appt0.getValid());
  }

  //invalid month - low
  @Test(timeout = 4000)
  public void test07()  throws Throwable  {
      Appt appt0 = new Appt(5, 5, 5, -5, 5, "Meeting", "This is a meeting", "work@gmail.com");
      appt0.setValid();
      assertFalse(appt0.getValid());
  }

  //invalid year - low
  @Test(timeout = 4000)
  public void test08()  throws Throwable  {
      Appt appt0 = new Appt(5, 5, 5, 5, -5, "Meeting", "This is a meeting", "work@gmail.com");
      appt0.setValid();
      assertFalse(appt0.getValid());
  }

  //invalid min - high
  @Test(timeout = 4000)
  public void test09()  throws Throwable  {
      Appt appt0 = new Appt(5, 60, 5, 5, 5, "Meeting", "This is a meeting", "work@gmail.com");
      appt0.setValid();
      assertFalse(appt0.getValid());
  }

  //invalid hour - high
  @Test(timeout = 4000)
  public void test10()  throws Throwable  {
      Appt appt0 = new Appt(24, 5, 5, 5, 5, "Meeting", "This is a meeting", "work@gmail.com");
      appt0.setValid();
      assertFalse(appt0.getValid());
  }

  //invalid day - high
  @Test(timeout = 4000)
  public void test11()  throws Throwable  {
      Appt appt0 = new Appt(5, 5, 32, 5, 5, "Meeting", "This is a meeting", "work@gmail.com");
      appt0.setValid();
      assertFalse(appt0.getValid());
  }

  //invalid month - high
  @Test(timeout = 4000)
  public void test12()  throws Throwable  {
      Appt appt0 = new Appt(5, 5, 5, 13, 5, "Meeting", "This is a meeting", "work@gmail.com");
      appt0.setValid();
      assertFalse(appt0.getValid());
  }

  //xml return
  @Test(timeout = 4000)
  public void test13()  throws Throwable  {
      Appt appt0 = new Appt(5, 5, 5, 13, 5, "Meeting", "This is a meeting", "work@gmail.com");
      appt0.getXmlElement();
  }

  //valid email
  @Test(timeout = 4000)
  public void test14()  throws Throwable  {
      Appt appt0 = new Appt(5, 5, 5, 13, 5, "Meeting", "This is a meeting", "work@gmail.com");
      String string0 = appt0.getEmailAddress();
      assertEquals(2, appt0.getRecurBy());
      assertFalse(appt0.isRecurring());
      assertEquals("work@gmail.com", string0);
      assertEquals(0, appt0.getRecurIncrement());
      appt0.setValid();
  }

  //no email
  @Test(timeout = 4000)
  public void test15()  throws Throwable  {
      Appt appt0 = new Appt(5, 5, 5, 13, 5, "Meeting", "This is a meeting", null);
      String string0 = appt0.getEmailAddress();
      assertEquals(2, appt0.getRecurBy());
      assertFalse(appt0.isRecurring());
      assertEquals("", string0);
      assertEquals(0, appt0.getRecurIncrement());
      appt0.setValid();
  }

  //no description
  @Test(timeout = 4000)
  public void test16()  throws Throwable  {
      Appt appt0 = new Appt(15, 30, 12, 14, 2018, "Meeting", null, "work@gmail.com");
      String string0 = appt0.getDescription();
      assertEquals(2, appt0.getRecurBy());
      assertFalse(appt0.isRecurring());
      assertEquals("", string0);
      assertEquals(0, appt0.getRecurIncrement());
      appt0.setValid();
  }

  //no title
  @Test(timeout = 4000)
  public void test17()  throws Throwable  {
      Appt appt0 = new Appt(15, 30, 12, 14, 2018, null, "This is a meeting", "work@gmail.com");
      String string0 = appt0.getTitle();
      assertEquals(2, appt0.getRecurBy());
      assertFalse(appt0.isRecurring());
      assertEquals("", string0);
      assertEquals(0, appt0.getRecurIncrement());
      appt0.setValid();
  }

  //on this day - all valid
  @Test(timeout = 4000)
  public void test18()  throws Throwable  {
      Appt appt0 = new Appt(10, 45, 10, 15, 2020, "Meeting", "This is a meeting", "work@gmail.com");
      assertTrue(appt0.isOn(10, 15, 2020));
  }

  //on this day - day invalid
  @Test(timeout = 4000)
  public void test19()  throws Throwable  {
      Appt appt0 = new Appt(10, 45, 10, 15, 2020, "Meeting", "This is a meeting", "work@gmail.com");
      assertFalse(appt0.isOn(5, 15, 2020));
  }

  //on this day - month invalid
  @Test(timeout = 4000)
  public void test20()  throws Throwable  {
      Appt appt0 = new Appt(10, 45, 10, 15, 2020, "Meeting", "This is a meeting", "work@gmail.com");
      assertFalse(appt0.isOn(10, 5, 2020));
  }

  //on this day - year invalid
  @Test(timeout = 4000)
  public void test21()  throws Throwable  {
      Appt appt0 = new Appt(10, 45, 10, 15, 2020, "Meeting", "This is a meeting", "work@gmail.com");
      assertFalse(appt0.isOn(10, 15, 2010));
  }

  //on this day - day + month invalid
  @Test(timeout = 4000)
  public void test22()  throws Throwable  {
      Appt appt0 = new Appt(10, 45, 10, 15, 2020, "Meeting", "This is a meeting", "work@gmail.com");
      assertFalse(appt0.isOn(5, 5, 2020));
  }

  //time is set
  @Test(timeout = 4000)
  public void test23()  throws Throwable  {
      Appt appt0 = new Appt(10, 45, 10, 15, 2020, "Meeting", "This is a meeting", "work@gmail.com");
      assertTrue(appt0.hasTimeSet());
  }

  //time is not set
  @Test(timeout = 4000)
  public void test24()  throws Throwable  {
      Appt appt0 = new Appt(10, 15, 2020, "Meeting", "This is a meeting", "work@gmail.com");
      assertFalse(appt0.hasTimeSet());
  }

  //is recurring - recur forver each week
  @Test(timeout = 4000)
  public void test25()  throws Throwable  {
      Appt appt0 = new Appt(10, 15, 2020, "Meeting", "This is a meeting", "work@gmail.com");
      int[] recurDaysArr = {2, 3, 5};
      appt0.setRecurrence(recurDaysArr, 1, 2, 1000);
      assertTrue(appt0.isRecurring());
      int[] recurDaysArr2 = appt0.getRecurDays();
  }

  //is recurring - recur forver each week, no set days
  @Test(timeout = 4000)
  public void test26()  throws Throwable  {
      Appt appt0 = new Appt(10, 15, 2020, "Meeting", "This is a meeting", "work@gmail.com");
      int[] recurDaysArr = null;
      appt0.setRecurrence(recurDaysArr, 1, 2, 1000);
      assertTrue(appt0.isRecurring());
      int[] recurDaysArr2 = appt0.getRecurDays();
  }

  //is not recurring
  @Test(timeout = 4000)
  public void test27()  throws Throwable  {
      Appt appt0 = new Appt(10, 15, 2020, "Meeting", "This is a meeting", "work@gmail.com");
      assertFalse(appt0.isRecurring());
  }

  //tostring check valid - valid date
  @Test(timeout = 4000)
  public void test28()  throws Throwable  {
      Appt appt0 = new Appt(15, 30, 10, 8, 2018, "Party", "Some party", "party@gmail.com");
      appt0.setValid();
      String string0 = appt0.toString();
      assertTrue(appt0.getValid());
  }

  //tostring check valid - invalid date
  @Test(timeout = 4000)
  public void test29()  throws Throwable  {
      Appt appt0 = new Appt(0, 0, 10, -100, 2018, "Party", "Some party", "party@gmail.com");
      appt0.setValid();
      String string0 = appt0.toString();
      assertFalse(appt0.getValid());
  }

}
