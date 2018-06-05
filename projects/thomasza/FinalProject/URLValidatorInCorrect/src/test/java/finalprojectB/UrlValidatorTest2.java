
package finalprojectB;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.Arrays;
import static org.junit.Assert.*;
import java.io.*;

import junit.framework.TestCase;

//You can use this as a skeleton for your 3 different test approach
//It is an optional to use this file, you can generate your own test file(s) to test the target function!
// Again, it is up to you to use this file or not!

public class UrlValidatorTest2 extends TestCase {


   public UrlValidatorTest2(String testName) {
      super(testName);
   }

   @Test(timeout = 4000)
   public void testManualTest_01()
   {
       UrlValidator urlVal = new UrlValidator(null, null, 1 << 0);
       String url = "http://www.google.com";
       assertTrue("This url should be valid", urlVal.isValid(url));
   }

   @Test(timeout = 4000)
   public void testManualTest_02()
   {
       UrlValidator urlVal = new UrlValidator(null, null, 1 << 0);
       String url = "ftp://www.google.com";
       assertTrue("This url should be valid", urlVal.isValid(url));
   }

   @Test(timeout = 4000)
   public void testManualTest_03()
   {
       UrlValidator urlVal = new UrlValidator(null, null, 1 << 0);
       String url = "httpw://www.google.com";
       assertFalse("This url should NOT be valid", urlVal.isValid(url));
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
