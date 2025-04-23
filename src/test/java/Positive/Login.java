package Positive;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.baseTest;
import utils.extentReportManager;

import java.io.IOException;

public class Login extends baseTest {
    @BeforeMethod
    public void setUp() throws InterruptedException, IOException {

        loadUrl();
        extentReportManager.startTest("Login Functionality", "<b>Login with Valid Credentials</b>");
        extentReportManager.testSteps("<b><font color='blue'>Test Case : </font>TC01: Verify user can log in with valid credentials</b>");
        extentReportManager.testSteps("<b><font color='blue'>Test Steps : </font></b>" +
                "<br>Step 1- Opened the application URL" +
                "<br>Step 2- Enter valid credentials " +
                "<br>Step 3- Click login button");
        webSteps.login();
        webSteps.waiting();
    }
    @Test
    public void loginWithValidCredentials() throws InterruptedException {
        boolean urlVerification = driver.getCurrentUrl().contains("https://app.oms3.transexpress.parallaxtec.dev/storemate");
        Assert.assertTrue(urlVerification, "Expecting login success but not navigated to dashboard");
    }

}
