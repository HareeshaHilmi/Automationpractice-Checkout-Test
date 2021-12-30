package com.automationpractice;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class checkOut {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("http://automationpractice.com/index.php/");
        driver.manage().window().maximize();
    }

    @Test
    public void signinUser() throws InterruptedException {
        driver.findElement(By.linkText("Sign in")).click();
        driver.findElement(By.id("email")).sendKeys("hareeh@gmail.com");
        driver.findElement(By.id("passwd")).sendKeys("Test@123");
        driver.findElement(By.xpath("//span[normalize-space()='Sign in']")).click();
        Thread.sleep(3000);

        //Order
        driver.findElement(By.xpath("//a[@title='Women']")).click();
        driver.findElement(By.xpath("//a[normalize-space()='Faded Short Sleeve T-shirts']")).click();
        driver.findElement(By.xpath("//span[normalize-space()='Add to cart']")).click();
        Thread.sleep(3000);
        driver.findElement(By.linkText("Proceed to checkout")).click();
        Thread.sleep(3000);
        driver.findElement(By.linkText("Proceed to checkout")).click();
        Thread.sleep(3000);
        driver.findElement(By.cssSelector("button[name='processAddress'] span")).click();
        Thread.sleep(3000);
        driver.findElement(By.id("cgv")).click();
        driver.findElement(By.cssSelector("button[name='processCarrier'] span")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//a[@title='Pay by bank wire']")).click();
        driver.findElement(By.xpath("//span[normalize-space()='I confirm my order']")).click();

        // Verify

        String actialMessage = driver.findElement(By.xpath("//*[contains(text(),\"Your order on My Store is complete.\")]")).getText();
        Assert.assertEquals(actialMessage, "Your order on My Store is complete.", "Order is not completed.");
    }
}
