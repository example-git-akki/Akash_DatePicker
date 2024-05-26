package TestDatePicker;

import de.redsix.pdfcompare.PdfComparator;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class DownloadFile {
    WebDriver driver;


    String location = System.getProperty("user.dir") + "\\Downloads";


    @BeforeMethod
    public void cleanFolder() throws IOException {
        File folder = new File(location);
        FileUtils.cleanDirectory(folder);
    }


    @Test(priority = 0)
    public void downloadFileLocation() throws IOException, InterruptedException {


        WebDriverManager.chromedriver().setup();


        ChromeOptions options = new ChromeOptions();

//        SET DOWNLOAD PATH

        Map<String, Object> prefs = new HashMap<>();
        prefs.put("download.default_directory", location);
        options.setExperimentalOption("prefs", prefs);


        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.get("https://samplelib.com/sample-jpeg.html");

        WebElement docs = driver.findElement(By.xpath("//ul//li//a[text()='docs']"));
        docs.click();


        driver.findElement(By.xpath("//a[text()='Excel']")).click();

        Thread.sleep(5000);

        driver.findElement(By.xpath("(//a[@class='btn btn-outline-primary btn-sm'])[1]")).click();

        Thread.sleep(5000);


//        driver.get("https://filesamples.com/formats/pdf");
//
//        WebElement btnDownload = driver.findElement(By.xpath("(//*[text()='Download'])[1]"));
//        JavascriptExecutor js = (JavascriptExecutor) driver;
//        js.executeScript("arguments[0].scrollIntoView(true)", btnDownload);
//        js.executeScript("arguments[0].style.border='3px solid red'", btnDownload);
//        js.executeScript("arguments[0].click()", btnDownload);
//        Thread.sleep(5000);


//        TO RENAME THE FILE NAME
        File originalFile = new File("./Downloads/sample-empty.xls");
        File renamedFile = new File("./Downloads/RequiredName.xls");
        FileUtils.moveFile(originalFile, renamedFile);

//      TO COMPARE THE TWO FILES
        File fileA = new File("./Excepted.pdf");
        File fileB = new File("./Excepted.pdf");
        String resultFile = "C:\\Users\\2129926\\Downloads\\NewPdfResults\\Results.pdf";

        new PdfComparator(fileA, fileB).compare().writeTo(resultFile);


        driver.quit();

    }

}




