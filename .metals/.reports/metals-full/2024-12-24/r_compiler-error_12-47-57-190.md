file:///D:/Automation/cocomber_selenium_grivance/e-municipal-management/src/test/java/com/aadrika/e_grievance/e_municipal_management/GrievanceTest.java
### java.util.NoSuchElementException: next on empty iterator

occurred in the presentation compiler.

presentation compiler configuration:


action parameters:
offset: 1002
uri: file:///D:/Automation/cocomber_selenium_grivance/e-municipal-management/src/test/java/com/aadrika/e_grievance/e_municipal_management/GrievanceTest.java
text:
```scala
package com.aadrika.e_grievance.e_municipal_management;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class GrievanceTest {
    WebDriver driver;
    GrievancePage grievancePage;

    @BeforeMethod
    public void setUp() throws InterruptedException {
        // Set up WebDriver
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://jharkhandegovernance.com/grievance/main");

        
        grievancePage = new GrievancePage(driver);
    }

    @Test
    public void Dilog_Box_Reg_Grv_Wl() throws InterruptedException {
    	grievancePage.D_box();
        grievancePage.D_regester_grivance_wl();
        grievancePage.Grievance_Description(null@@);

    }

    @AfterMethod
    public void tearDown() {
        // Close the browser after each test
       // driver.quit();
    }
}

```



#### Error stacktrace:

```
scala.collection.Iterator$$anon$19.next(Iterator.scala:973)
	scala.collection.Iterator$$anon$19.next(Iterator.scala:971)
	scala.collection.mutable.MutationTracker$CheckedIterator.next(MutationTracker.scala:76)
	scala.collection.IterableOps.head(Iterable.scala:222)
	scala.collection.IterableOps.head$(Iterable.scala:222)
	scala.collection.AbstractIterable.head(Iterable.scala:935)
	dotty.tools.dotc.interactive.InteractiveDriver.run(InteractiveDriver.scala:164)
	dotty.tools.pc.MetalsDriver.run(MetalsDriver.scala:45)
	dotty.tools.pc.SignatureHelpProvider$.signatureHelp(SignatureHelpProvider.scala:32)
	dotty.tools.pc.ScalaPresentationCompiler.signatureHelp$$anonfun$1(ScalaPresentationCompiler.scala:422)
```
#### Short summary: 

java.util.NoSuchElementException: next on empty iterator