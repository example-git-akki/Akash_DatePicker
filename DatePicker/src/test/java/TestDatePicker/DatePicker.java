package TestDatePicker;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.Duration;
import java.util.List;

public class DatePicker {
    WebDriver driver;
    //    String url = "file:///C:/Users/2129926/Downloads/India_Payslip_July_2023.pdf";
    String url = "file:///C:/Users/2129926/OneDrive%20-%20Cognizant/Documents/API%20CASE%20STUDIES/ak/DatePicker/2023-02-23T13_21_27.727Z_transactiondetails.pdf";

    @BeforeMethod
    public void beforePart() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }


    @Test(priority = 0)
    public void DatePickerDemo() {
        driver.get("https://jqueryui.com/datepicker/");
        driver.switchTo().frame(0);
        driver.findElement(By.xpath("//input[@id='datepicker']")).click();

        String Month = "May";
        String Year = "2023";
        String Date = "20";

        while (true) {
            WebElement month = driver.findElement(By.xpath("//*[@class='ui-datepicker-month']"));
            WebElement year = driver.findElement(By.xpath("//*[@class='ui-datepicker-year']"));
            if (month.getText().equals(Month) && year.getText().equals(Year)) {
                break;
            }
            driver.findElement(By.xpath("//*[@class='ui-icon ui-icon-circle-triangle-w']")).click();
        }

        List<WebElement> date = driver.findElements(By.xpath("//*[@class='ui-datepicker-calendar']//td"));
        for (WebElement dateItem : date) {
            if (dateItem.getText().equals(Date)) {
                dateItem.click();
                break;
            }
        }

    }

    @Test(priority = 1)
    public void demoDatePicker() throws InterruptedException {
        driver.get("https://bookonwardticket.com/dummy-ticket/?gclid=Cj0KCQiAgK2qBhCHARIsAGACuzmJzLbwuh3Ldfi6HmmZemVxQcnKiAA2L25AlGawuFJzPFpj4avgNoEaAuReEALw_wcB");

        Thread.sleep(2000);


        WebElement datefield = driver.findElement(By.xpath("//input[@name='dummy-departure-date']"));
        Thread.sleep(2000);
        datefield.click();

        Thread.sleep(2000);
        Select sc1 = new Select(driver.findElement(By.xpath("//select[@class='ui-datepicker-month']")));
        sc1.selectByVisibleText("Dec");
        Thread.sleep(2000);
        Select sc = new Select(driver.findElement(By.xpath("//select[@class='ui-datepicker-year']")));
        sc.selectByVisibleText("2024");

        Thread.sleep(2000);
        String myDate = "20";
        List<WebElement> dateCalendar = driver.findElements(By.xpath("//table[@class='ui-datepicker-calendar']//td"));
        for (WebElement date : dateCalendar) {
            if (date.getText().equals(myDate)) {
                date.click();
            }
        }
        Thread.sleep(2000);


    }

    @Test(priority = 2)
    public void sspDatePicker() throws InterruptedException {

        driver.get("https://dc-ssp-ui-qa.orientalbank.com/");

        Thread.sleep(2000);
        JavascriptExecutor js = (JavascriptExecutor) driver;


        WebElement calendarIcon = driver.findElement(By.xpath("//*[@id='Icon']"));
        js.executeScript("window.scrollBy(0,250)", "");
        calendarIcon.click();


        String Year = "2000";
        String Date = "20";
        String Month = "May";


        List<WebElement> selectYear = driver.findElements(By.xpath("//div[@class='MuiYearPicker-root css-f09ynp']//button"));
        System.out.println("Year Size is:" + selectYear.size());
        for (WebElement selectYearList : selectYear) {
            System.out.println(selectYearList.getText());
            if (selectYearList.getText().equals(Year)) {
                selectYearList.click();
                break;
            }

        }


        driver.findElement(By.xpath("//button[text()='" + Month + "']")).click();
        WebElement selectDate = driver.findElement(By.xpath("//*[@role='rowgroup']//div//button[text()='" + Date + "']"));
        selectDate.click();


//        List<WebElement> date = driver.findElements(By.xpath("//div[@class='MuiDayPicker-monthContainer css-i6bazn']//button"));
//        System.out.println("Date Size is :" + date.size());
//        for (WebElement dateList : date) {
//            System.out.println(dateList.getText());
//            if (dateList.getText().equals(Date)) {
//
//                dateList.click();
//            }
//        }


    }

    @Test(priority = 3)
    public void googleWebsite() throws InterruptedException {

        driver.get("https://www.google.com/");
        driver.findElement(By.xpath("//a[@class='gb_d']")).click();

        WebElement iframe = driver.findElement(By.xpath("//iframe[@name='app']"));
        driver.switchTo().frame(iframe);
        WebElement btnPlayStore = driver.findElement(By.xpath("//*[@data-text='Play']"));
        btnPlayStore.click();


//        List<WebElement> google = driver.findElements(By.xpath("//ul[@jsname='k77Iif']//li"));
//        System.out.println(google.size());
//        for (WebElement googleOption : google) {
//            if (googleOption.getText().equals("Play")) {
//                googleOption.click();
//            }
//        }

    }

    @Test(priority = 4)
    public void pdfReader() throws IOException, InterruptedException {

        driver.get(url);

//        Create an object of url and pass the url in it
        URL pdfurl = new URL(url);
        Thread.sleep(2000);

//        connection to the url
        InputStream ips = pdfurl.openStream();
        BufferedInputStream bfs = new BufferedInputStream(ips);
        PDDocument pdDocument = Loader.loadPDF(bfs.readAllBytes());
        Thread.sleep(2000);
        int totalPages = pdDocument.getNumberOfPages();
//        System.out.println(totalPages);

        PDFTextStripper pdfTextStripper = new PDFTextStripper();
        String textInPdf = pdfTextStripper.getText(pdDocument);
        System.out.println(textInPdf);
        if (textInPdf.contains("Transaction Details") && (textInPdf.contains("Loan")) && textInPdf.contains("Line of Credit")
                && textInPdf.contains("Transaction amount")) {
            System.out.println("Pdf validation Passed");
        }

    }

    @Test(priority = 5)
    public void rightClickDemo() throws IOException {

        driver.get("https://www.browserstack.com/guide/wait-commands-in-selenium-webdriver");
        Actions actions = new Actions(driver);
        WebElement element = driver.findElement(By.xpath("//h1"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true)", element);

//        actions.contextClick(element).build().perform();
//
//        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
//        Thread.sleep(20000);
        actions.doubleClick(element).build().perform();
        File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(source, new File("./target/newScreenshot.Png"));
    }
}

