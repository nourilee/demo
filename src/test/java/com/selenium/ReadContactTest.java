package com.selenium;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import io.qameta.allure.Description;

public class ReadContactTest extends BaseTest {

    private static final String ADMIN_APP_XPATH = "//span[normalize-space()='Admin App(form based)']";
    private static final String FIRST_ROW_XPATH = "//div[@role='rowgroup']//div[@role='row'][1]";
    private static final String CELL_XPATH = ".//div[@role='gridcell']";
    private static final Set<String> REQUIRED_FIELDS = new HashSet<>(Arrays.asList(
            "name", "email", "phone_number", "address", "account_creation_date", "country_of_residence"
    ));

    @Test
    @Description("Test to read data from the list")
    public void testReadContact() {

        // Navigate to Admin App (form based) Page
        WebElement adminAppButton = driver.findElement(By.xpath(ADMIN_APP_XPATH));
        adminAppButton.click();

        // Locate and verify the presence of the first row
        WebElement firstRow = driver.findElement(By.xpath(FIRST_ROW_XPATH));
        assertNotNull(firstRow, "The first row should be present");

        // Iterate over each cell and verify non-empty text for required fields
        List<WebElement> cells = firstRow.findElements(By.xpath(CELL_XPATH));
        for (WebElement cell : cells) {
            String cellField = cell.getAttribute("data-field");
            String cellText = cell.getText();

            if (REQUIRED_FIELDS.contains(cellField)) {
                assertFalse(cellText.isEmpty(), "Cell text for field '" + cellField + "' should not be empty");
            }
        }
    }
}
