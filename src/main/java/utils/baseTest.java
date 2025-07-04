package utils;

import com.formdev.flatlaf.FlatLightLaf;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import javax.swing.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

// Base class for setting up browser, loading URL, and managing test reports.
public class baseTest {
    protected static String browser = null;
    protected WebDriver driver;
    protected String baseUrl;
    protected webSteps webSteps;

    @BeforeSuite
    public void setupReport() throws IOException, InterruptedException {
        extentReportManager.initReport();
    }

    @AfterMethod
    public void tearDownBrowser(ITestResult result) {
        configureTestReport(result);
    }

    @AfterSuite
    public void finalizeReport() {
        extentReportManager.flushReport();
        extentReportManager.openReport();
    }

    public void setUpBrowser() {
        if (browser == null) {
            browser = getUserBrowserInput();
        }

        switch (browser.toLowerCase()) {
            case "chrome":
                driver = new ChromeDriver();
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
            case "edge":
                driver = new EdgeDriver();
                break;
            default:
                throw new RuntimeException("Browser is not supported: " + browser);
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    public void loadUrl() throws InterruptedException, IOException {
        Properties properties = new Properties();
        FileInputStream fis = new FileInputStream("src/main/resources/config.properties");
        properties.load(fis);
        baseUrl = properties.getProperty("baseUrl");

        setUpBrowser();
        webSteps = new webSteps(driver);

        //driver.manage().window().maximize();
        driver.get(baseUrl);
        webSteps.waiting();
    }

    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    private String getUserBrowserInput() {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception e) {
            e.printStackTrace();
        }

        Object[] options = {"Chrome", "Firefox", "Edge"};

        int choice = JOptionPane.showOptionDialog(null,
                "Select a browser:",
                "Browser Selection",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]); // Default to Chrome

        if (choice == -1) {
            throw new RuntimeException("No browser selected. Test aborted!");
        }

        return options[choice].toString().toLowerCase();
    }


    // common method for extent report configuration
    protected void configureTestReport(@NotNull ITestResult result){
        if (result.getStatus() == ITestResult.FAILURE) {
            extentReportManager
                    .logFail("❌ <b><font color='red'> FAILED : </font></b>" + result.getThrowable().getMessage());
        } else {
            extentReportManager.logPass("✅ <b><font color='green'> PASSED </font></b>");
        }

        extentReportManager.captureScreenshot(driver, result);
        tearDown();
    }

    public int getPageSizeIndex(int size) {
        switch (size) {
            case 10: return 0;
            case 25: return 1;
            case 50: return 2;
            case 100: return 3;
            case 500: return 4;
            case 1000: return 5;
            default: throw new IllegalArgumentException("Unsupported page size: " + size);
        }
    }

}
