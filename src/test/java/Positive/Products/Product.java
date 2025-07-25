package Positive.Products;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.PropertyUtils;
import utils.baseTest;
import utils.extentReportManager;
import java.awt.*;
import java.io.IOException;

public class Product extends baseTest {
    String productName;
    String productPrice;

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
        extentReportManager.startTest("Product Functionality", "<b>Add Product</b>");
        extentReportManager.testSteps("<b><font color='blue'>Test Case : </font>Verify that the user can successfully add a product</b>");
        extentReportManager.testSteps("<b><font color='blue'>Test Steps : </font></b>" +
                "<br>Step 1- Login to the System" +
                "<br>Step 2- Click Products " +
                "<br>Step 3- Click Product List " +
                "<br>Step 4- Click 'Add New Product' Button" +
                "<br>Step 5- Fill Details" +
                "<br>Step 6- Click 'Save' Button"
        );
        webSteps.click("ClickAddNewProduct");
        productName = webSteps.generateRandomProductName();
        webSteps.type(productName,"Product_Name");
        webSteps.searchFromDropdown("Unit_Name","Product_Unit");
        webSteps.searchFromDropdown("Brand_Name","Product_Brand");
        webSteps.searchFromDropdown("Category_Name","Product_Category");
        productPrice = webSteps.generateRandomProductSellingPrice();
        webSteps.type(productPrice,"Product_SellingPrice");
        webSteps.type("Testing_Product ","Product_Description");
        webSteps.click("SaveButton");
        webSteps.implicitWait("ToastMessage");
        Assert.assertEquals("Product created successfully",webSteps.getText("ToastMessage"));
        PropertyUtils.setProperty("Product_Name",productName);
        PropertyUtils.setProperty("Product_SellingPrice",productPrice);
    }

    @Test(priority = 2)
    public void viewProduct() throws InterruptedException, AWTException {
        extentReportManager.startTest("Product Functionality", "<b>View Product</b>");
        extentReportManager.testSteps("<b><font color='blue'>Test Case : </font>Verify that the user can successfully view a product's details</b>");
        extentReportManager.testSteps("<b><font color='blue'>Test Steps : </font></b>" +
                "<br>Step 1- Login to the System" +
                "<br>Step 2- Click Products" +
                "<br>Step 3- Click Product List" +
                "<br>Step 4- Search and select a Product" +
                "<br>Step 5- Click View Action" +
                "<br>Step 6- Verify displayed details match expected values"
        );

        webSteps.passValue("Product Name", "SearchBy_Dropdown");
        webSteps.type(PropertyUtils.getProperty("Product_Name"), "SearchBy_SearchBar");
        webSteps.click("SearchBy_SearchButton");
        webSteps.click("Action2");

        Assert.assertEquals(webSteps.getValue("view_productName"), PropertyUtils.getProperty("Product_Name"), "Product Name mismatch");
        Assert.assertEquals(webSteps.getValue("view_productUnit"), PropertyUtils.getProperty("Unit_Name"), "Product Name mismatch");
        Assert.assertEquals(webSteps.getValue("view_productBrand"), PropertyUtils.getProperty("Brand_Name"), "Product Name mismatch");
        Assert.assertEquals(webSteps.getValue("view_productCategory"), PropertyUtils.getProperty("Category_Name"), "Product Name mismatch");
        Assert.assertEquals(webSteps.getValue("view_productSellingPrice"), PropertyUtils.getProperty("Product_SellingPrice")+".00", "Product Name mismatch");
        Assert.assertEquals(webSteps.getText("view_productDescription"), PropertyUtils.getProperty("Product_Description"), "Product Name mismatch");
        PropertyUtils.setProperty("SKU",webSteps.getValue("view_productSKU"));
    }

    @DataProvider (name = "productSearchData")
    public Object[][] productSearchData() {
        return new Object[][]{
                {"Product Name", PropertyUtils.getProperty("Product_Name"), 2},
                {"SKU", PropertyUtils.getProperty("SKU"), 1},
                {"Brand", PropertyUtils.getProperty("Brand_Name"), 3},
                {"Category", PropertyUtils.getProperty("Category_Name"), 4},
        };
    }

    @Test(dataProvider = "productSearchData", priority = 3)
    public void searchProduct(String criteriaType,String inputValue, int columnIndex) throws InterruptedException, AWTException {
        extentReportManager.startTest("Product Functionality", "<b>Search Product Using " + criteriaType + "</b>");
        extentReportManager.testSteps("<b><font color='blue'>Test Case : </font>Verify that the product can search by " + criteriaType.toLowerCase() + "</b>");
        extentReportManager.testSteps("<b><font color='blue'>Test Steps : </font></b>" +
                "<br>Step 1- Login to the System" +
                "<br>Step 2- Click Products" +
                "<br>Step 3- Click Product List" +
                "<br>Step 4 - Select '" + criteriaType + "' from 'Search By' dropdown" +
                "<br>Step 5 - Enter Search Input" +
                "<br>Step 6 - Click Search"
        );
        webSteps.passValue(criteriaType,"SearchBy_Dropdown");
        webSteps.type(inputValue,"SearchBy_SearchBar");
        webSteps.click("SearchBy_SearchButton");
        String actualResult = webSteps.getTableCellText(1, columnIndex);
        Assert.assertEquals(actualResult.trim(), inputValue.trim(), "Search result mismatch for criteria: " + criteriaType);
    }

    @Test(priority = 4)
    public void editProduct() throws InterruptedException, AWTException {
        extentReportManager.startTest("Product Functionality", "<b>Edit Product</b>");
        extentReportManager.testSteps("<b><font color='blue'>Test Case : </font>Verify that the user can successfully edit a product</b>");
        extentReportManager.testSteps("<b><font color='blue'>Test Steps : </font></b>" +
                "<br>Step 1- Login to the System" +
                "<br>Step 2- Click Products " +
                "<br>Step 3- Click Product List " +
                "<br>Step 4 - Search the product name that needs to be edited" +
                "<br>Step 5 - Click Edit Action" +
                "<br>Step 6 - Make the necessary changes" +
                "<br>Step 7 - Click Update"
        );
        webSteps.passValue("Product Name","SearchBy_Dropdown");
        webSteps.type(PropertyUtils.getProperty("Product_Name"),"SearchBy_SearchBar");
        webSteps.click("SearchBy_SearchButton");
        webSteps.click("Action1");
        productName = webSteps.generateRandomProductName();
        webSteps.type(productName,"Product_Name");
        productPrice = webSteps.generateRandomProductSellingPrice();
        webSteps.type(productPrice,"Product_SellingPrice");
        webSteps.type("Description","Product_Description");
        webSteps.click("SaveButton");
        Assert.assertEquals("Product updated successfully",webSteps.getText("ToastMessage"));
        PropertyUtils.setProperty("Product_Name",productName);
        PropertyUtils.setProperty("Product_SellingPrice",productPrice);
    }
}
