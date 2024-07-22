package com.selenium;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import com.selenium.utils.TestDataGenerator;

import io.qameta.allure.Description;

public class UpdateContactTest extends BaseTest {

    private static final String ADMIN_APP_XPATH = "//span[normalize-space()='Admin App(form based)']";
    private static final String LAST_ROW_XPATH = "//div[@role='rowgroup']//div[@role='row'][last()]";
    private static final String ADDRESS_FIELD_XPATH = "//input[@id=':rf:' and @name='address']";
    private static final String UPDATE_BUTTON_XPATH = "//button[@id=':rk:' and text()='Update']";
    private static final String REFRESH_BUTTON_XPATH = "//button[@id=':r1:' and text()='Refresh']";
    private static final String ADDRESS_DIV_XPATH = "//div[@role='rowgroup']//div[@role='row'][last()]//div[@data-field='address']";

    @Test
    @Description("Test to update an existing contact with new address data")
    public void testUpdateContact() {

        // Navigate to Admin App (form based) Page
        WebElement adminAppButton = driver.findElement(By.xpath(ADMIN_APP_XPATH));
        adminAppButton.click();

        // Locate and click on the last row of the customer list assuming its not empty 
        WebElement lastRow = driver.findElement(By.xpath(LAST_ROW_XPATH));
        assertNotNull(lastRow, "The list is empty");
        lastRow.click();

        // Generate new test data
        String newAddress = TestDataGenerator.generateAddress();

        // Update address field
        WebElement addressField = driver.findElement(By.xpath(ADDRESS_FIELD_XPATH));
        addressField.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.BACK_SPACE, newAddress);

        // Click Update and Refresh buttons
        driver.findElement(By.xpath(UPDATE_BUTTON_XPATH)).click();
        driver.findElement(By.xpath(REFRESH_BUTTON_XPATH)).click();

        // Refresh the page and verify the update
        driver.navigate().refresh();
        WebElement addressDiv = driver.findElement(By.xpath(ADDRESS_DIV_XPATH));
        String updatedAddress = addressDiv.getText();
        assertEquals(newAddress, updatedAddress, "The address field did not update as expected");
    }
}