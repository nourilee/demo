package com.selenium;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import io.qameta.allure.Description;

public class DeleteContactTest extends BaseTest {

    private static final String ADMIN_APP_XPATH = "//span[normalize-space()='Admin App(form based)']";
    private static final String ROWS_CSS_SELECTOR = ".MuiDataGrid-row";
    private static final String EMAIL_CELL_CSS_SELECTOR = "[data-field='email']";
    private static final String DELETE_BUTTON_XPATH = "//button[@id=':r2:' and text()='Delete user']";
    private static final String CONTACT_EMAIL_LOCATOR_TEMPLATE = "//div[contains(text(),'%s')]";

    @Test
    @Description("Test to delete an existing contact")
    public void testDeleteContact() {
        // Navigate to Admin App (form based) Page
        WebElement adminAppButton = driver.findElement(By.xpath(ADMIN_APP_XPATH));
        adminAppButton.click();

        // Locate all rows in the grid
        List<WebElement> rows = driver.findElements(By.cssSelector(ROWS_CSS_SELECTOR));

        // Ensure there are rows available
        if (rows.isEmpty()) {
            fail("No rows found in the grid.");
        }

        // Select a random row
        Random random = new Random();
        int randomIndex = random.nextInt(rows.size());
        WebElement randomRow = rows.get(randomIndex);

        // Extract the email value from the selected row
        WebElement emailCell = randomRow.findElement(By.cssSelector(EMAIL_CELL_CSS_SELECTOR));
        String emailValue = emailCell.getText();

        // Click the selected row
        randomRow.click();

        // Click the Delete button
        driver.findElement(By.xpath(DELETE_BUTTON_XPATH)).click();
        driver.navigate().refresh();

        // Verify the contact deletion
        By contactLocator = By.xpath(String.format(CONTACT_EMAIL_LOCATOR_TEMPLATE, emailValue));
        try {
            driver.findElement(contactLocator);
            fail("Contact: '" + emailValue + "' was not deleted successfully.");
        } catch (NoSuchElementException e) {
            // If no element is found, then the contact has been deleted
            System.out.println("Contact: '" + emailValue + "' was deleted successfully.");
        }
    }
}