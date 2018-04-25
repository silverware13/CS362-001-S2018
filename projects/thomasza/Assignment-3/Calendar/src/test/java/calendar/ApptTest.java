/** A JUnit test class to test the class ApptTest. */

package calendar;

import org.junit.Test;
import java.util.Arrays;
import static org.junit.Assert.*;
import calendar.Appt;
import calendar.CalendarUtil;


public class ApptTest  {

  //to string - valid
  @Test(timeout = 4000)
  public void test00()  throws Throwable  {
      Appt appt0 = new Appt(15, 30, 12, 14, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
      String string0 = appt0.toString();
      assertEquals("\t14/12/2018 at 3:30pm ,Birthday Party, This is my birthday party\n", string0);
  }

  //valid date with no minute or hour
  @Test(timeout = 4000)
  public void test01()  throws Throwable  {
      Appt appt0 = new Appt(12, 14, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
      String string0 = appt0.toString();
      assertEquals("\t14/12/2018 at -1:-1am ,Birthday Party, This is my birthday party\n", string0);
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
      Appt appt0 = new Appt(5, -1, 5, 5, 5, "Meeting", "This is a meeting", "work@gmail.com");
      appt0.setValid();
      assertFalse(appt0.getValid());
  }

  //invalid hour - low
  @Test(timeout = 4000)
  public void test05()  throws Throwable  {
      Appt appt0 = new Appt(-1, 5, 5, 5, 100, "Meeting", "This is a meeting", "work@gmail.com");
      appt0.setValid();
      assertFalse(appt0.getValid());
  }

  //invalid day - low
  @Test(timeout = 4000)
  public void test06()  throws Throwable  {
      Appt appt0 = new Appt(5, 5, 0, 2, 1600, "Meeting", "This is a meeting", "work@gmail.com");
      appt0.setValid();
      assertFalse(appt0.getValid());
  }

  //invalid month - low
  @Test(timeout = 4000)
  public void test07()  throws Throwable  {
      Appt appt0 = new Appt(5, 5, 5, 0, 1600, "Meeting", "This is a meeting", "work@gmail.com");
      appt0.setValid();
      assertFalse(appt0.getValid());
  }

  //invalid year - low
  @Test(timeout = 4000)
  public void test08()  throws Throwable  {
      Appt appt0 = new Appt(5, 5, 5, 5, 0, "Meeting", "This is a meeting", "work@gmail.com");
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
      Appt appt0 = new Appt(5, 5, (CalendarUtil.NumDaysInMonth(5, 5-1)+1), 5, 5, "Meeting", "This is a meeting", "work@gmail.com");
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
      assertEquals(null, appt0.getXmlElement());
  }

  //valid email
  @Test(timeout = 4000)
  public void test14()  throws Throwable  {
      Appt appt0 = new Appt(5, 5, 5, 13, 5, "Meeting", "This is a meeting", "work@gmail.com");
      String string0 = appt0.getEmailAddress();
      assertEquals("work@gmail.com", string0);
  }

  //no email
  @Test(timeout = 4000)
  public void test15()  throws Throwable  {
      Appt appt0 = new Appt(5, 5, 5, 13, 5, "Meeting", "This is a meeting", null);
      String string0 = appt0.getEmailAddress();
      assertEquals("", string0);
  }

  //no description
  @Test(timeout = 4000)
  public void test16()  throws Throwable  {
      Appt appt0 = new Appt(15, 30, 12, 14, 2018, "Meeting", null, "work@gmail.com");
      String string0 = appt0.getDescription();
      assertEquals("", string0);
  }

  //no title
  @Test(timeout = 4000)
  public void test17()  throws Throwable  {
      Appt appt0 = new Appt(15, 30, 12, 14, 2018, null, "This is a meeting", "work@gmail.com");
      String string0 = appt0.getTitle();
      assertEquals("", string0);
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

  //is recurring - recur forever each week
  @Test(timeout = 4000)
  public void test25()  throws Throwable  {
      Appt appt0 = new Appt(10, 15, 2020, "Meeting", "This is a meeting", "work@gmail.com");
      int[] recurDaysArr = {2, 3, 5};
      appt0.setRecurrence(recurDaysArr, Appt.RECUR_BY_WEEKLY, 2, Appt.RECUR_NUMBER_FOREVER);
      assertTrue(appt0.isRecurring());
  }

  //is recurring - recur forever each week, no set days
  @Test(timeout = 4000)
  public void test26()  throws Throwable  {
      Appt appt0 = new Appt(10, 15, 2020, "Meeting", "This is a meeting", "work@gmail.com");
      int[] recurDaysArr = null;
      appt0.setRecurrence(recurDaysArr, Appt.RECUR_BY_WEEKLY, 2, Appt.RECUR_NUMBER_FOREVER);
      assertTrue(appt0.isRecurring());
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

  //valid min - low
  @Test(timeout = 4000)
  public void test30()  throws Throwable  {
      Appt appt0 = new Appt(5, 0, 5, 5, 5, "Meeting", "This is a meeting", "work@gmail.com");
      appt0.setValid();
      assertTrue(appt0.getValid());
  }

  //valid hour - low
  @Test(timeout = 4000)
  public void test31()  throws Throwable  {
      Appt appt0 = new Appt(0, 5, 5, 5, 100, "Meeting", "This is a meeting", "work@gmail.com");
      appt0.setValid();
      assertTrue(appt0.getValid());
  }

  //valid day - low
  @Test(timeout = 4000)
  public void test32()  throws Throwable  {
      Appt appt0 = new Appt(5, 5, 1, 2, 1600, "Meeting", "This is a meeting", "work@gmail.com");
      appt0.setValid();
      assertTrue(appt0.getValid());
  }

  //valid month - low
  @Test(timeout = 4000)
  public void test33()  throws Throwable  {
      Appt appt0 = new Appt(5, 5, 5, 1, 1600, "Meeting", "This is a meeting", "work@gmail.com");
      appt0.setValid();
      assertTrue(appt0.getValid());
  }

  //valid year - low
  @Test(timeout = 4000)
  public void test34()  throws Throwable  {
      Appt appt0 = new Appt(5, 5, 5, 5, 1, "Meeting", "This is a meeting", "work@gmail.com");
      appt0.setValid();
      assertTrue(appt0.getValid());
  }

  //valid min - high
  @Test(timeout = 4000)
  public void test35()  throws Throwable  {
      Appt appt0 = new Appt(5, 59, 5, 5, 5, "Meeting", "This is a meeting", "work@gmail.com");
      appt0.setValid();
      assertTrue(appt0.getValid());
  }

  //valid hour - high
  @Test(timeout = 4000)
  public void test36()  throws Throwable  {
      Appt appt0 = new Appt(23, 5, 5, 5, 5, "Meeting", "This is a meeting", "work@gmail.com");
      appt0.setValid();
      assertTrue(appt0.getValid());
  }

  //valid day - high
  @Test(timeout = 4000)
  public void test37()  throws Throwable  {
      Appt appt0 = new Appt(5, 5, CalendarUtil.NumDaysInMonth(5, 5-1), 5, 5, "Meeting", "This is a meeting", "work@gmail.com");
      appt0.setValid();
      assertTrue(appt0.getValid());
  }

  //valid month - high
  @Test(timeout = 4000)
  public void test38()  throws Throwable  {
      Appt appt0 = new Appt(5, 5, 5, 12, 5, "Meeting", "This is a meeting", "work@gmail.com");
      appt0.setValid();
      assertTrue(appt0.getValid());
  }
}
