package finalprojectB;

import java.util.Arrays;
import static org.junit.Assert.*;

import junit.framework.TestCase;

//You can use this as a skeleton for your 3 different test approach
//It is an optional to use this file, you can generate your own test file(s) to test the target function!
// Again, it is up to you to use this file or not!

public class UrlValidatorTest extends TestCase {


   public UrlValidatorTest(String testName) {
   	super(testName);
   }

   //test valid http address
   public void testManualTest_01()
   {
       UrlValidator urlVal = new UrlValidator(null);
       String url = "http://www.website.com";
       String assertMessage = String.format("This url should be valid: %s", url);
       assertTrue(assertMessage, urlVal.isValid(url));
   }
/*
   //test valid ftp address
   public void testManualTest_02() //FINDS BUGS (EXCEPTION)
   {
       UrlValidator urlVal = new UrlValidator(null);
       String url = "ftp://www.website.com";
       String assertMessage = String.format("This url should be valid: %s", url);
       assertTrue(assertMessage, urlVal.isValid(url));
   }

   //test valid ip address
   public void testManualTest_03()
   {
       UrlValidator urlVal = new UrlValidator(null);
       String url = "https://12.45.33.44";
       String assertMessage = String.format("This url should be valid: %s", url);
       assertTrue(assertMessage, urlVal.isValid(url));
   }
*/
   //test invalid empty address
   public void testManualTestInvalid_01()
   {
       UrlValidator urlVal = new UrlValidator(null);
       String url = "";
       String assertMessage = String.format("This url should NOT be valid: %s", url);
       assertFalse(assertMessage, urlVal.isValid(url));
   }

   //test invalid too long ip address
   public void testManualTestInvalid_02()
   {
       UrlValidator urlVal = new UrlValidator(null);
       String url = "http://12.45.33.44.13";
       String assertMessage = String.format("This url should NOT be valid: %s", url);
       assertFalse(assertMessage, urlVal.isValid(url));
   }

   //test invalid http address that is missing ":"
   public void testManualTestInvalid_03()
   {
       UrlValidator urlVal = new UrlValidator(null);
       String url = "http//www.website.com";
       String assertMessage = String.format("This url should NOT be valid: %s", url);
       assertFalse(assertMessage, urlVal.isValid(url));
   }

   //partition test scheme
   public void testYourFirstPartition()
   {
       UrlValidator urlVal = new UrlValidator(null);
       String url = "http://www.website.com"; // medium length valid scheme
       String assertMessage = String.format("This url should be valid: %s", url);
       assertTrue(assertMessage, urlVal.isValid(url));
       url = "https://www.website.com"; // longest valid scheme
       assertMessage = String.format("This url should be valid: %s", url);
       assertTrue(assertMessage, urlVal.isValid(url));
       url = "ftp://www.website.com"; // shortest valid scheme
       assertMessage = String.format("This url should be valid: %s", url);
       assertTrue(assertMessage, urlVal.isValid(url));
       url = "httpss://www.website.com"; // just too long invalid scheme
       assertMessage = String.format("This url should NOT be valid: %s", url);
       assertFalse(assertMessage, urlVal.isValid(url));
       url = "ft://www.website.com"; // just too short invalid scheme
       assertMessage = String.format("This url should NOT be valid: %s", url);
       assertFalse(assertMessage, urlVal.isValid(url));
   }

   //partition test authority
   public void testYourSecondPartition()
   {
      UrlValidator urlVal = new UrlValidator(null);
      String url = "http://127.90.53.34"; // medium valid authority
      String assertMessage = String.format("This url should be valid: %s", url);
      assertTrue(assertMessage, urlVal.isValid(url));
      url = "https://255.255.255.255"; // largest valid authority
      assertMessage = String.format("This url should be valid: %s", url);
      assertTrue(assertMessage, urlVal.isValid(url));
      url = "https://0.0.0.0"; // smallest valid authority
      assertMessage = String.format("This url should be valid: %s", url);
      assertTrue(assertMessage, urlVal.isValid(url));
      url = "https://255.255.255.256"; // just too large invalid authority
      assertMessage = String.format("This url should NOT be valid: %s", url);
      assertFalse(assertMessage, urlVal.isValid(url));
      url = "https://0.0.0.-1"; // just too small invalid authority
      assertMessage = String.format("This url should NOT be valid: %s", url);
      assertFalse(assertMessage, urlVal.isValid(url));
   }

   public void testIsValid()
   {
	   //You can use this function for programming based testing

   }
}
