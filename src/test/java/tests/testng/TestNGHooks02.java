package tests.testng;

import org.testng.annotations.*;

public class TestNGHooks01 {

    // TestNG HOOK
    @BeforeSuite
    public void beforeSuite(){
        System.out.println("\t--->" + this.getClass().getSimpleName() + "|Before Suite");
    }

    @BeforeTest
    public void beforeTest(){
        System.out.println("\t\t--->" + this.getClass().getSimpleName() + "|Before Test");
    }

    @BeforeClass
    public void beforeClass(){
        System.out.println("\t\t\t--->" + this.getClass().getSimpleName() + "|Before Class");
    }

    @BeforeMethod
    public void beforeMethod(){
        System.out.println("\t\t\t\t--->" + this.getClass().getSimpleName() + "|Before Method");
    }

    @Test
    public void testSth01() {
        System.out.println(this.getClass().getSimpleName() + "|Test Method 01");
    }

    @Test
    public void testSth02() {
        System.out.println(this.getClass().getSimpleName() + "|Test Method 02");
    }

    @AfterMethod
    public void afterMethod(){
        System.out.println("\t\t\t\t--->" + this.getClass().getSimpleName() + "|After Method");
    }
}
