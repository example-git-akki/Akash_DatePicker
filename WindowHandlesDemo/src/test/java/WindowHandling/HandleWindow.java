package WindowHandling;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

public class HandleWindow {
    WebDriver driver;
    @Test
    public void handleWindowDemo(){

        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();
        driver.manage().window().maximize();

        driver.get("https://www.amazon.in/");

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));



        driver.findElement(By.xpath("//*[@id='nav-xshop']/a[text()='Mobiles']")).click();

        Actions actions= new Actions(driver);
        actions.moveToElement(driver.findElement(By.xpath("//*[@id='nav-subnav']/a[2]/span[1]"))).build().perform();

        driver.findElement(By.xpath("(//a[contains(text(),'Mi')])[1]")).click();

        driver.findElement(By.xpath("//*[contains(text(),'Mi QC 3.0 (9V) Charger|18W Wall Charger|Fast Charging|Certified Qualcomm Quick Charge 3.0 + BIS Certified|Compatible for Mobile, Headphones, TWS, Game Console, Power Banks')]")).click();


        Set<String> windowHandles = driver.getWindowHandles();
        Iterator<String> it = windowHandles.iterator();
        String parentWin = (String) it.next();
        String childWin = (String) it.next();
        driver.switchTo().window(childWin);

        driver.findElement(By.xpath("//input[@id='add-to-cart-button']")).click();



        driver.switchTo().window(parentWin);

        driver.quit();

    }
}

