package tests.testng;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

public class BaseTestNG {

    // TestNG HOOK
    @BeforeSuite
    public void beforeSuite(){
        System.out.println("\t--->" + this.getClass().getSimpleName() + "|Before Suite");
    }

    @BeforeTest
    public void beforeTest(){
        System.out.println("\t\t--->" + this.getClass().getSimpleName() + "|Before Test");
    }
}
