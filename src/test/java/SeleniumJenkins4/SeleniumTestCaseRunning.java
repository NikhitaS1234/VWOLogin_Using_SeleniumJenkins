package SeleniumJenkins4;

import jdk.jfr.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class SeleniumTestCaseRunning {

    ChromeOptions options;
    WebDriver driver;

    @BeforeSuite
    public void setUp()
    {
        System.setProperty("webdriver.chrome.driver", "C:/Users/Dell/eclipse/java-2022-12/chromedriver-win64/chromedriver.exe");

        options=new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver=new ChromeDriver(options);
      //  driver=new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    @Description("#TC1-Verify with Invalid Username and Valid password, Login is not successful !!")
    public void testInvalidLogin () throws InterruptedException {
        driver.get("https://app.vwo.com/#/login");
        driver.findElement(By.name("username")).sendKeys("nikhitasarole1@gmail.co");
        driver.findElement(By.name("password")).sendKeys("Nik1.dee2");
        driver.findElement(By.xpath("//button[@id='js-login-btn']")).click();

        Thread.sleep(2000);
        String errorString=driver.findElement(By.className("notification-box-description")).getText();
        Assert.assertEquals(errorString,"Your email, password, IP address or location did not match");

        //  System.out.println(errorMsz);
        //   Thread.sleep(2000);
    }
    @Test(groups={"stage"})
    @Description("#TC1-Verify with Valid Username and Valid password, Login is successful !!")
    public void testValidLogin () throws InterruptedException {
        driver.get("https://app.vwo.com/#/login");
        driver.findElement(By.name("username")).sendKeys("nikhitasarole1@gmail.com");
        driver.findElement(By.name("password")).sendKeys("Nik1.dee2");
        driver.findElement(By.xpath("//button[@id='js-login-btn']")).click();

        String SuccessMsz=driver.getTitle();

        Assert.assertEquals(SuccessMsz, "Login - VWO");
        Thread.sleep(2000);

    }

    @AfterSuite
    public void tearDown ()
    {
        driver.quit();
    }

}


