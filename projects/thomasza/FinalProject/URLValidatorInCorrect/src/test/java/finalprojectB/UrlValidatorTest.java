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

   public void testManualTest_01()
   {
       UrlValidator urlVal = new UrlValidator(null, null, 1 << 0);
       String url = "http://www.google.com";
       String assertMessage = String.format("This url should be valid: %s", url);
       assertTrue(assertMessage, urlVal.isValid(url));
   }

   public void testManualTest_02()
   {
       UrlValidator urlVal = new UrlValidator(null, null,  1 << 0);
       String url = "ftp://www.google.com";
       String assertMessage = String.format("This url should be valid: %s", url);
       assertTrue(assertMessage, urlVal.isValid(url));
   }

   public void testManualTest_03()
   {
       UrlValidator urlVal = new UrlValidator(null, null,  1 << 0);
       String url = "5pw://www.google.com";
       String assertMessage = String.format("This url should NOT be valid: %s", url);
       assertFalse(assertMessage, urlVal.isValid(url));
   }

   public void testYourFirstPartition()
   {
	 //You can use this function to implement your First Partition testing

   }

   public void testYourSecondPartition(){
		 //You can use this function to implement your Second Partition testing

   }
   //You need to create more test cases for your Partitions if you need to

   public void testIsValid()
   {
	   //You can use this function for programming based testing

   }
}
