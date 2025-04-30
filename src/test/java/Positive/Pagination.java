package Positive;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.baseTest;
import utils.extentReportManager;

import java.awt.*;

public class Pagination extends baseTest{
    @DataProvider(name = "pageSizes")
    public Object[][] pageSizes() {
        return new Object[][]{
                {10}, {25}, {50}, {100}, {500}, {1000}
        };
    }

    @Test(dataProvider = "pageSizes", priority = 5)
    public void verifyPageSize(int size) throws InterruptedException, AWTException {
        extentReportManager.startTest("Pagination", "Verify user page size option: " + size);
        extentReportManager.testSteps("<b><font color='blue'>Test Case : </font>Verify that the user can select page size " + size + "</b>");
        extentReportManager.testSteps("<b><font color='blue'>Test Steps : </font></b>" +
                "<br>Step 1- Login to the System" +
                "<br>Step 2- Click User " +
                "<br>Step 3- Click User List" +
                "<br>Step 4 - Open the Page Size dropdown" +
                "<br>Step 5 - Select page size " + size +
                "<br>Step 6 - Verify the number of rows matches the page size"
        );
        webSteps.click("ClickUser");
        webSteps.click("ClickUserList");

        int index = getPageSizeIndex(size);
        webSteps.select("UserPageSizeDropdown", index, 1);  // uses Robot to select

        int rowCount = driver.findElements(By.xpath("//table/tbody/tr")).size();
        Assert.assertTrue(rowCount <= size, "Row count exceeds selected page size: " + size);
    }
}
