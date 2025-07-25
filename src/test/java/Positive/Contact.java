package Positive;

import org.apache.logging.log4j.core.net.Priority;
import org.openqa.selenium.By;
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
    String customerName;
    String contactNo1;
    String contactNo2;
    String address;
    String email;

    @BeforeMethod
    public void setUp() throws InterruptedException, IOException {

        loadUrl();
        webSteps.login();
        webSteps.waiting();
        webSteps.click("ClickContacts");
    }

    @Test(priority = 1)
    public void addCustomer() throws InterruptedException, AWTException {
        extentReportManager.startTest("Contact Functionality", "<b>Add Customer</b>");
        extentReportManager.testSteps("<b><font color='blue'>Test Case : </font>Verify that the user can successfully add a customer</b>");
        extentReportManager.testSteps("<b><font color='blue'>Test Steps : </font></b>" +
                "<br>Step 1- Login to the System" +
                "<br>Step 2- Click Contacts " +
                "<br>Step 3- Click 'Add New Customer' Button" +
                "<br>Step 5- Fill Details" +
                "<br>Step 6- Click 'Save' Button"
        );
        webSteps.click("ClickAddNewCustomer");
        customerName = webSteps.generateRandomCustomerName();
        webSteps.type(customerName,"Contacts_ContactName");
        contactNo1 = webSteps.generateRandomCustomerContactNo();
        webSteps.type(contactNo1,"Contacts_ContactNo1");
        contactNo2 = webSteps.generateRandomCustomerContactNo();
        webSteps.type(contactNo2,"Contacts_ContactNo2");
        address = webSteps.generateRandomCustomerAddress();
        webSteps.type(address,"Contacts_ShippingAddress");
        webSteps.searchFromDropdown("City_Name","Contacts_City");
        email = webSteps.generateRandomCustomerEmail();
        webSteps.type(email,"Contacts_Email");
        webSteps.click("SaveButton");
        webSteps.implicitWait("ToastMessage");
        Assert.assertEquals("Contact created successfully",webSteps.getText("ToastMessage"));
        PropertyUtils.setProperty("Contacts_ContactName",customerName);
        PropertyUtils.setProperty("Contacts_ContactNo1",contactNo1);
        PropertyUtils.setProperty("Contacts_ContactNo2",contactNo2);
        PropertyUtils.setProperty("Contacts_ShippingAddress",address);
        PropertyUtils.setProperty("Contacts_Email",email);
    }

    @Test(priority = 2)
    public void viewCustomer() throws InterruptedException {
        extentReportManager.startTest("Contact Functionality", "<b>View Customer</b>");
        extentReportManager.testSteps("<b><font color='blue'>Test Case : </font>Verify that the user can successfully view a customer's details</b>");
        extentReportManager.testSteps("<b><font color='blue'>Test Steps : </font></b>" +
                "<br>Step 1- Login to the System" +
                "<br>Step 2- Click Contacts" +
                "<br>Step 3- Search and select a Customer" +
                "<br>Step 4- Click View Action" +
                "<br>Step 5- Verify displayed details match expected values" +
                "<br>Step 6- Ensure fields are non-editable"
        );
        webSteps.passValue("Name", "SearchBy_Dropdown");
        webSteps.type(PropertyUtils.getProperty("Contacts_ContactName"), "SearchBy_SearchBar");
        webSteps.click("SearchBy_SearchButton");
        webSteps.click("Action2");

        Assert.assertEquals(webSteps.getValue("View_customerName"), PropertyUtils.getProperty("Contacts_ContactName"), "Contact Name mismatch");
        Assert.assertEquals(webSteps.getValue("View_customerContactNo1"), PropertyUtils.getProperty("Contacts_ContactNo1"), "Contact No 1 mismatch");
        Assert.assertEquals(webSteps.getValue("View_customerContactNo2"), PropertyUtils.getProperty("Contacts_ContactNo2"), "Contact No 2 mismatch");
        Assert.assertEquals(webSteps.getValue("View_customerAddress"), PropertyUtils.getProperty("Contacts_ShippingAddress"), "Shipping Address mismatch");
        Assert.assertEquals(webSteps.getValue("View_customerCity"), PropertyUtils.getProperty("City_Name"), "City mismatch");
        Assert.assertEquals(webSteps.getValue("View_customerEmail"), PropertyUtils.getProperty("Contacts_Email"), "Email mismatch");
        PropertyUtils.setProperty("ReferenceID",webSteps.getValue("View_RefID"));
    }


    @DataProvider(name = "contactSearchData")
    public Object[][] contactSearchData() {
        return new Object[][]{
                {"Reference ID", PropertyUtils.getProperty("ReferenceID"), 1},
                {"Name", PropertyUtils.getProperty("Contacts_ContactName"), 2},
                {"Address", PropertyUtils.getProperty("Contacts_ShippingAddress"), 6},
                {"Contact Number","+94 "+PropertyUtils.getProperty("Contacts_ContactNo1"), 3},
                {"Contact Number","+94 "+PropertyUtils.getProperty("Contacts_ContactNo2"), 4},
                {"Email",PropertyUtils.getProperty("Contacts_Email"), 5},
                {"City",PropertyUtils.getProperty("City_Name"), 7}
        };
    }

    @Test(dataProvider = "contactSearchData", priority = 3)
    public void searchCustomer(String criteriaType,String inputValue, int columnIndex) throws InterruptedException, AWTException {
        extentReportManager.startTest("Contact Functionality", "<b>Search Customer Using " + criteriaType + "</b>");
        extentReportManager.testSteps("<b><font color='blue'>Test Case : </font>Verify that the customer can search by " + criteriaType.toLowerCase() + "</b>");
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

    @Test(priority = 4)
    public void editCustomer() throws InterruptedException, AWTException {
        extentReportManager.startTest("Contact Functionality", "<b>Edit Contact</b>");
        extentReportManager.testSteps("<b><font color='blue'>Test Case : </font>Verify that the user can successfully edit a customer</b>");
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
        customerName = webSteps.generateRandomCustomerName();
        webSteps.type(customerName,"Contacts_ContactName");
        contactNo1 = webSteps.generateRandomCustomerContactNo();
        webSteps.type(contactNo1,"Contacts_ContactNo1");
        contactNo2 = webSteps.generateRandomCustomerContactNo();
        webSteps.type(contactNo2,"Contacts_ContactNo2");
        address = webSteps.generateRandomCustomerAddress();
        webSteps.type(address,"Contacts_ShippingAddress");
        //webSteps.searchFromDropdown("City_Name","Contacts_City");
        email = webSteps.generateRandomCustomerEmail();
        webSteps.type(email,"Contacts_Email");
        webSteps.click("UpdateButton");
        Assert.assertEquals("Contact updated successfully",webSteps.getText("ToastMessage"));
        PropertyUtils.setProperty("Contacts_ContactName",customerName);
        PropertyUtils.setProperty("Contacts_ContactNo1",contactNo1);
        PropertyUtils.setProperty("Contacts_ContactNo2",contactNo2);
        PropertyUtils.setProperty("Contacts_ShippingAddress",address);
        PropertyUtils.setProperty("Contacts_Email",email);
    }
}
