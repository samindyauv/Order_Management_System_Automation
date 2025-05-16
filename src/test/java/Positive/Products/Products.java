package Positive.Products;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.PropertyUtils;
import utils.baseTest;
import utils.extentReportManager;

import java.awt.*;
import java.io.IOException;

public class Products extends baseTest {
    String productName;

    @BeforeMethod
    public void setUp() throws InterruptedException, IOException {

        loadUrl();
        webSteps.login();
        webSteps.waiting();
        webSteps.click("ClickProducts");
        webSteps.click("ClickProductList");
    }

    @Test(priority = 1)
    public void addProduct() throws InterruptedException, AWTException {
        extentReportManager.startTest("Products Functionality", "<b>Add Product</b>");
        extentReportManager.testSteps("<b><font color='blue'>Test Case : </font>TC01: Verify that the user can successfully add a product</b>");
        extentReportManager.testSteps("<b><font color='blue'>Test Steps : </font></b>" +
                "<br>Step 1- Login to the System" +
                "<br>Step 2- Click Products " +
                "<br>Step 3- Click Product List " +
                "<br>Step 3- Click 'Add New Product' Button" +
                "<br>Step 5- Fill Details" +
                "<br>Step 6- Click 'Save' Button"
        );
        webSteps.click("ClickAddNewProduct");
        webSteps.type(webSteps.generateRandomProductName(),"Product_Name");
        webSteps.searchFromDropdown("Unit_Name","Product_Unit");
        webSteps.searchFromDropdown("Brand_Name","Product_Brand");
        webSteps.searchFromDropdown("Category_Name","Product_Category");
        webSteps.type("1000","Product_SellingPrice");
        webSteps.type("Testing_Product Description","Product_Description");
        webSteps.click("SaveButton");
        webSteps.implicitWait("ToastMessage");
        Assert.assertEquals("Product created successfully",webSteps.getText("ToastMessage"), "Passed");
    }
}
