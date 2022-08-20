package api_learning.testng;

import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

public class TestNGHooks01 extends BaseTestNG {

    @BeforeClass
    public void beforeClass(){
        System.out.println("\t\t\t--->" + this.getClass().getSimpleName() + "|Before Class");
    }

    @BeforeMethod
    public void beforeMethod(){
        System.out.println("\t\t\t\t--->" + this.getClass().getSimpleName() + "|Before Method");
    }

    @Test(priority = 1, dependsOnMethods = {"testSth01"})
    public void testSth02() {
        System.out.println(this.getClass().getSimpleName() + "|Test Method 02");
    }

    @Test(priority = 2)
    public void testSth01() {
        throw new RuntimeException("FAILED");
//        System.out.println(this.getClass().getSimpleName() + "|Test Method 01");
    }

    @Test
    public void testSth03(){
        System.out.println("Test Method 03");
        String actualResult = "a";
        String expectedResult = "b";
//        Verifier.verifyEquals(actualResult, expectedResult);

        // Hard Assertion:
        Assert.assertEquals(actualResult, expectedResult);
        Assert.assertEquals(actualResult, expectedResult, "[ERR] Sth wrong here!");
        System.out.println("Something else!!!");

    }

    @Test
    public void testSth04(){
        System.out.println("Test Method 04");
        String actualResult = "a";
        String expectedResult = "b";

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualResult, expectedResult, "[ERR] Wrong message 01");
        softAssert.assertEquals(actualResult, expectedResult, "[ERR] Wrong message 02");
        softAssert.assertAll();
    }

    @AfterMethod
    public void afterMethod(){
        System.out.println("\t\t\t\t--->" + this.getClass().getSimpleName() + "|After Method");
    }
}
