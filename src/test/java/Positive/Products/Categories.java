package Positive.Products;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.baseTest;
import utils.extentReportManager;

import java.awt.*;
import java.io.IOException;

public class Categories extends baseTest {
    @BeforeMethod
    public void setUp() throws InterruptedException, IOException {

        loadUrl();
        webSteps.login();
        webSteps.waiting();
        webSteps.click("ClickProducts");
        webSteps.click("ClickCategories");
    }

    @Test(priority = 1)
    public void addCategory() throws InterruptedException, AWTException {
        extentReportManager.startTest("Categories Functionality", "<b>Add Category</b>");
        extentReportManager.testSteps("<b><font color='blue'>Test Case : </font>TC01: Verify that the user can successfully add a category</b>");
        extentReportManager.testSteps("<b><font color='blue'>Test Steps : </font></b>" +
                "<br>Step 1- Login to the System" +
                "<br>Step 2- Click Products " +
                "<br>Step 3- Click Product List " +
                "<br>Step 3- Click 'Add New Category' Button" +
                "<br>Step 5- Fill Details" +
                "<br>Step 6- Click 'Save' Button"
        );
        webSteps.click("ClickAddNewCategory");
        webSteps.type("Testing_Category","Category_Name");
        webSteps.type("Testing_Category Description","Category_Remark");
        webSteps.click("Category_SaveButton");
        Assert.assertEquals("Category created successfully",webSteps.getText("ToastMessage"), "Passed");
    }

}
