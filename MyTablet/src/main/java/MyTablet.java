package com.example.tests;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class MyTablet {
    private WebDriver driver;
    private String baseUrl;
    private String firstElement;
    private String titleElement;
    private int sizeList;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

    @Before
    public void setUp() throws Exception {
        System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        baseUrl = "https://yandex.ru/";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void testMyTest2() throws Exception {
        driver.get(baseUrl);
        driver.findElement(By.linkText("Маркет")).click();
        driver.findElement(By.linkText("Компьютеры")).click();
        driver.findElement(By.xpath("(//a[contains(text(),'Планшеты')])[2]")).click();
        driver.findElement(By.linkText("Перейти ко всем фильтрам")).click();
        driver.findElement(By.id("glf-pricefrom-var")).clear();
        driver.findElement(By.id("glf-pricefrom-var")).sendKeys("20000");
        driver.findElement(By.id("glf-priceto-var")).clear();
        driver.findElement(By.id("glf-priceto-var")).sendKeys("25000");
        driver.findElement(By.xpath("//div[@class='main']//button[.='Ещё']")).click();
        driver.findElement(By.xpath("//label[@for='glf-7893318-267101']")).click();
        driver.findElement(By.xpath("//div[@class='main']/div[4]/div/div[1]/div[1]/div[2]/div[2]/div/span/span/input")).clear();
        driver.findElement(By.xpath("//div[@class='main']/div[4]/div/div[1]/div[1]/div[2]/div[2]/div/span/span/input")).sendKeys("dell");
        TimeUnit.SECONDS.sleep(1);
        driver.findElement(By.xpath("//label[@for='glf-7893318-153080']")).click();
        TimeUnit.SECONDS.sleep(1);
        driver.findElement(By.linkText("Показать подходящие")).click();
        sizeList = driver.findElements(By.className("snippet-card__header-link")).size();
        if (sizeList == 10) {
            System.out.println("кол-во элементов на странице равно 10-ти");
        }
        else {
            System.out.println("Кол-во элементов:");
            System.out.println(sizeList);
        }
        firstElement = driver.findElements(By.className("snippet-card__header-link")).get(0).getText();
        driver.findElement(By.id("header-search")).clear();
        driver.findElement(By.id("header-search")).sendKeys(firstElement);
        driver.findElement(By.xpath("//div[@class='header2__left']//button[@type='submit']")).click();
        TimeUnit.SECONDS.sleep(1);
        titleElement = driver.findElement(By.className("n-title__text")).findElement(By.className("title")).getText();
        if (titleElement.equals(firstElement)){
            System.out.println("Названия совпадают");
        }
        else {
            System.out.println("Названия НЕ совпадают");
        }
        System.out.println(titleElement +" и "+ firstElement);

    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    private String closeAlertAndGetItsText() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }
}
