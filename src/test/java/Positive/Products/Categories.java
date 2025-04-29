package Positive.Products;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.baseTest;
import utils.extentReportManager;

import java.awt.*;
import java.io.IOException;

public class Categories extends baseTest {

    String categoryName;

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
                "<br>Step 3- Click Categories " +
                "<br>Step 3- Click 'Add New Category' Button" +
                "<br>Step 5- Fill Details" +
                "<br>Step 6- Click 'Save' Button"
        );
        webSteps.click("ClickAddNewCategory");
        this.categoryName = webSteps.generateRandomCategoryName();
        webSteps.type(categoryName,"Category_Name");
        webSteps.type("Testing_Category Description","Category_Remark");
        webSteps.click("SaveButton");
        webSteps.implicitWait("ToastMessage");
        Assert.assertEquals("Category created successfully",webSteps.getText("ToastMessage"), "Passed");
    }

    @Test(priority = 2)
    public void searchCategory() throws InterruptedException, AWTException {
        extentReportManager.startTest("Categories Functionality", "<b>Search Category</b>");
        extentReportManager.testSteps("<b><font color='blue'>Test Case : </font>TC01: Verify that the user can successfully search a category</b>");
        extentReportManager.testSteps("<b><font color='blue'>Test Steps : </font></b>" +
                "<br>Step 1- Login to the System" +
                "<br>Step 2- Click Products " +
                "<br>Step 3- Click Categories " +
                "<br>Step 4 - Select Category Name from 'Search By' dropdown" +
                "<br>Step 4 - Enter Search Input" +
                "<br>Step 5 - Click Search"
        );
        webSteps.select("SearchBy_Dropdown",1,0);
        webSteps.type(categoryName,"SearchBy_SearchBar");
        webSteps.click("SearchBy_SearchButton");
        String actualResult = webSteps.getTableCellText(1, 1);
        Assert.assertEquals(actualResult, categoryName, "Search result does not match input value.");
    }



}
