package Positive;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.PropertyUtils;
import utils.baseTest;
import utils.extentReportManager;

import java.awt.*;
import java.io.IOException;

public class Contact extends baseTest{
    @BeforeMethod
    public void setUp() throws InterruptedException, IOException {

        loadUrl();
        webSteps.login();
        webSteps.waiting();
        webSteps.click("ClickContacts");
    }

    @Test(priority = 1)
    public void addContact() throws InterruptedException, AWTException {
        extentReportManager.startTest("Contacts Functionality", "<b>Add Customer</b>");
        extentReportManager.testSteps("<b><font color='blue'>Test Case : </font>TC01: Verify that the user can successfully add a customer</b>");
        extentReportManager.testSteps("<b><font color='blue'>Test Steps : </font></b>" +
                "<br>Step 1- Login to the System" +
                "<br>Step 2- Click Contacts " +
                "<br>Step 3- Click 'Add New Customer' Button" +
                "<br>Step 5- Fill Details" +
                "<br>Step 6- Click 'Save' Button"
        );
        webSteps.click("ClickAddNewCustomer");
        webSteps.type(webSteps.generateRandomCustomerName(),"Contacts_ContactName");
        webSteps.type(webSteps.generateRandomCustomerNumber(),"Contacts_ContactNo1");
        webSteps.type(webSteps.generateRandomCustomerNumber(),"Contacts_ContactNo2");
        webSteps.type(webSteps.generateRandomCustomerAddress(),"Contacts_ShippingAddress");
        webSteps.searchFromDropdown("City_Name","Contacts_City");
        webSteps.type(webSteps.generateRandomCustomerEmail(),"Contacts_Email");
        webSteps.click("SaveButton");
        Assert.assertEquals("Contact created successfully",webSteps.getText("ToastMessage"), "Passed");
    }


    @DataProvider(name = "contactSearchData")
    public Object[][] contactSearchData() {
        return new Object[][]{
                //Reference ID
                {"Name", PropertyUtils.getProperty("Customer_Name"), 2},
                {"Address", PropertyUtils.getProperty("Customer_Address"), 6},
                {"Contact Number",PropertyUtils.getProperty("Customer_ContactNo"), 4},
                {"Email",PropertyUtils.getProperty("Customer_Email"), 5}
        };
    }

    @Test(dataProvider = "contactSearchData", priority = 2)
    public void searchCustomer(String criteriaType,String inputValue, int columnIndex) throws InterruptedException, AWTException {
        extentReportManager.startTest("Contacts Functionality", "<b>Search User Using " + criteriaType + "</b>");
        extentReportManager.testSteps("<b><font color='blue'>Test Case : </font>Verify that the user can search by " + criteriaType.toLowerCase() + "</b>");
        extentReportManager.testSteps("<b><font color='blue'>Test Steps : </font></b>" +
                "<br>Step 1 - Login to the System" +
                "<br>Step 2 - Click Contacts" +
                "<br>Step 3 - Select '" + criteriaType + "' from 'Search By' dropdown" +
                "<br>Step 4 - Enter Search Input" +
                "<br>Step 5 - Click Search"
        );
        webSteps.passValue(criteriaType,"SearchBy_Dropdown");
        webSteps.type(inputValue,"SearchBy_SearchBar");
        webSteps.click("SearchBy_SearchButton");
        String actualResult = webSteps.getTableCellText(1, columnIndex);
        Assert.assertEquals(actualResult.trim(), inputValue.trim(), "Search result mismatch for criteria: " + criteriaType);
    }

    @Test(priority = 3)
    public void editCustomer() throws InterruptedException, AWTException {
        extentReportManager.startTest("Contacts Functionality", "<b>Edit Contact</b>");
        extentReportManager.testSteps("<b><font color='blue'>Test Case : </font>TC03: Verify that the user can successfully edit a customer</b>");
        extentReportManager.testSteps("<b><font color='blue'>Test Steps : </font></b>" +
                "<br>Step 1- Login to the System" +
                "<br>Step 2- Click Contacts " +
                "<br>Step 3- Select a Contact" +
                "<br>Step 4- Click Edit Action" +
                "<br>Step 5- Edit Details" +
                "<br>Step 6- Click 'Update' Button"
        );
        webSteps.passValue("Name","SearchBy_Dropdown");
        webSteps.type(PropertyUtils.getProperty("Customer_Name"),"SearchBy_SearchBar");
        webSteps.click("SearchBy_SearchButton");
        webSteps.click("Action1");
        webSteps.type(webSteps.generateRandomCustomerName(),"Contacts_ContactName");
        webSteps.type(webSteps.generateRandomCustomerNumber(),"Contacts_ContactNo1");
        webSteps.type(webSteps.generateRandomCustomerNumber(),"Contacts_ContactNo2");
        webSteps.type(webSteps.generateRandomCustomerAddress(),"Contacts_ShippingAddress");
        webSteps.searchFromDropdown("City_Name","Contacts_City");
        webSteps.type(webSteps.generateRandomCustomerEmail(),"Contacts_Email");
        webSteps.click("UpdateButton");
        Assert.assertEquals("Contact updated successfully",webSteps.getText("ToastMessage"), "Passed");
    }
}
