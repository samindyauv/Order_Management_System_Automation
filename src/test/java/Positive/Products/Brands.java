package Positive.Products;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.baseTest;
import utils.extentReportManager;

import java.awt.*;
import java.io.IOException;

public class Brands extends baseTest {

    String brandName;

    @BeforeMethod
    public void setUp() throws InterruptedException, IOException {

        loadUrl();
        webSteps.login();
        webSteps.waiting();
        webSteps.click("ClickProducts");
        webSteps.click("ClickBrands");
    }

    @Test(priority = 1)
    public void addBrand() throws InterruptedException, AWTException {
        extentReportManager.startTest("Brands Functionality", "<b>Add Brand</b>");
        extentReportManager.testSteps("<b><font color='blue'>Test Case : </font>TC01: Verify that the user can successfully add a brand</b>");
        extentReportManager.testSteps("<b><font color='blue'>Test Steps : </font></b>" +
                "<br>Step 1- Login to the System" +
                "<br>Step 2- Click Products " +
                "<br>Step 3- Click Brands " +
                "<br>Step 3- Click 'Add New Brand' Button" +
                "<br>Step 5- Fill Details" +
                "<br>Step 6- Click 'Save' Button"
        );
        webSteps.click("ClickAddNewBrand");
        this.brandName = webSteps.generateRandomBrandName();
        webSteps.type(brandName,"Brand_Name");
        webSteps.type("Testing_Brand Description","Brand_Remark");
        webSteps.click("SaveButton");
        webSteps.implicitWait("ToastMessage");
        Assert.assertEquals("Brand created successfully",webSteps.getText("ToastMessage"), "Passed");
    }
}
