package finalprojectB;

import java.util.Random;
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

   //test valid ftp address
   public void testManualTest_02()
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

   //test valid address with port
   public void testManualTest_04()
   {
       UrlValidator urlVal = new UrlValidator(null);
       String url = "https://12.45.33.44:80";
       String assertMessage = String.format("This url should be valid: %s", url);
       assertTrue(assertMessage, urlVal.isValid(url));
   }

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

   //test invalid port
   public void testManualTestInvalid_04()
   {
       UrlValidator urlVal = new UrlValidator(null);
       String url = "http://www.website.com:-5000";
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

   //test that splits urls into valid or invalid sections
   public void testIsValid()
   {
      String url;
      Random rand = new Random();
      UrlValidator urlVal = new UrlValidator(null);

      //valid and invalid schemes
      String[] schemes = {"http://", "ftp://", "https://"};
      String[] invalidSchemes = {"httpss://", "http//", "http:\\"};

      //valid and invalid authorities
      String[] authoritys = {"www.Website.com", "12.50.43.11", "255.255.255.255"};
      String[] invalidAuthoritys = {"300.55.12.33", "website", ""};

      //valid and invalid ports
      String[] ports = {"", ":12345", ":25"};
      String[] invalidPorts = {":ABC", ":999999", ":-5000"};

      //valid and invalid paths
      String[] paths = {"", "/some/path", "/even/more/path/"};
      String[] invalidPaths = {"/..", "/../", "/this//is//a//bad///path"};

      //test these custom urls against the method isValid
      for(int i = 1; i < 1000; i++){

          //half the tests will be valid and half with be invalid
          int badSection = (int )(rand.nextInt(2));
          if(badSection > 0){
              //find what sections will have bad input
              badSection = (int )(rand.nextInt(15) + 1);
          }

          //test a random value for each input
          int schemeVal = (int )(rand.nextInt(3));
          int authorityVal = (int )(rand.nextInt(3));
          int portVal = (int )(rand.nextInt(3));
          int pathVal = (int )(rand.nextInt(3));

          String urlSch = schemes[schemeVal];
          String urlAut = authoritys[authorityVal];
          String urlPor = ports[portVal];
          String urlPat = paths[pathVal];

          String urlSchB = invalidSchemes[schemeVal];
          String urlAutB = invalidAuthoritys[authorityVal];
          String urlPorB = invalidPorts[portVal];
          String urlPatB = invalidPaths[pathVal];

          //get the correct sections and put them together
          switch(badSection){
              case 1: url = urlSchB + urlAut + urlPor + urlPat; break;
              case 2: url = urlSch + urlAutB + urlPor + urlPat; break;
              case 3: url = urlSch + urlAut + urlPorB + urlPat; break;
              case 4: url = urlSch + urlAut + urlPor + urlPatB; break;
              case 5: url = urlSchB + urlAutB + urlPor + urlPat; break;
              case 6: url = urlSchB + urlAut + urlPorB + urlPat; break;
              case 7: url = urlSchB + urlAut + urlPor + urlPatB; break;
              case 8: url = urlSchB + urlAutB + urlPor + urlPatB; break;
              case 9: url = urlSchB + urlAut + urlPorB + urlPatB; break;
              case 10: url = urlSch + urlAutB + urlPorB + urlPat; break;
              case 11: url = urlSch + urlAutB + urlPor + urlPatB; break;
              case 12: url = urlSch + urlAutB + urlPorB + urlPatB; break;
              case 13: url = urlSch + urlAut + urlPorB + urlPatB; break;
              case 14: url = urlSch + urlAutB + urlPorB + urlPatB; break;
              case 15: url = urlSchB + urlAutB + urlPorB + urlPatB; break;
              default: url = urlSch + urlAut + urlPor + urlPat; break;
          }

          //check the validity of the url in the isValid method
          if(badSection > 0){
              String assertMessage = String.format("This url should NOT be valid: %s", url);
              assertFalse(assertMessage, urlVal.isValid(url));
              System.out.printf("Invalid: %s\n", url);
          } else {
              String assertMessage = String.format("This url should be valid: %s", url);
              assertTrue(assertMessage, urlVal.isValid(url));
              System.out.printf("Valid: %s\n", url);
          }
      }
   }
}
