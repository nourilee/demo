package com.selenium;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import com.selenium.utils.TestDataGenerator;

import io.qameta.allure.Description;

public class CreateContactTest extends BaseTest {

    private static final String ADMIN_APP_XPATH = "//span[normalize-space()='Admin App(form based)']";
    private static final String NAME_FIELD_XPATH = "//input[@id=':rm:' and @name='name']";
    private static final String ADDRESS_FIELD_XPATH = "//input[@id=':r11:' and @name='address']";
    private static final String COUNTRY_FIELD_XPATH = "//input[@id=':r13:' and @name='country_of_residence']";
    private static final String EMAIL_FIELD_XPATH = "//input[@id=':r15:' and @name='email']";
    private static final String PHONE_FIELD_XPATH = "//input[@id=':rv:' and @name='phone_number']";
    private static final String GENDER_FIELD_XPATH = "//div[@id=':ra:' and @role='combobox']";
    private static final String GENDER_LIST_OPTION_XPATH = "//ul[@id=':rb:']/li[@data-value='%s']";
    private static final String ADD_BUTTON_XPATH = "//button[@id=':r16:' and text()='Add']";
    private static final String DYNAMIC_CONTACT_NAME_LOCATOR = "//div[contains(text(),'%s')]";

    @Test
    @Description("Test to create a new contact with generated data")
    public void testCreateContact() {

        // Navigate to Admin App (form based) Page
        WebElement adminAppButton = driver.findElement(By.xpath(ADMIN_APP_XPATH));
        adminAppButton.click();

        // Generate new contact data
        String name = TestDataGenerator.generateName();
        String address = TestDataGenerator.generateAddress();
        String email = TestDataGenerator.generateEmail();
        String phone = TestDataGenerator.generatePhoneNumber();

        // Fill out the add new contact form
        driver.findElement(By.xpath(NAME_FIELD_XPATH)).sendKeys(name);
        driver.findElement(By.xpath(ADDRESS_FIELD_XPATH)).sendKeys(address);
        driver.findElement(By.xpath(COUNTRY_FIELD_XPATH)).sendKeys("USA");
        driver.findElement(By.xpath(EMAIL_FIELD_XPATH)).sendKeys(email);
        driver.findElement(By.xpath(PHONE_FIELD_XPATH)).sendKeys(phone);
        driver.findElement(By.xpath(GENDER_FIELD_XPATH)).click();
        driver.findElement(By.xpath(String.format(GENDER_LIST_OPTION_XPATH, "Other"))).click();

        // Submit the form
        driver.findElement(By.xpath(ADD_BUTTON_XPATH)).click();

        // Verify the contact creation
        By contactLocator = By.xpath(String.format(DYNAMIC_CONTACT_NAME_LOCATOR, name));

        try {
            WebElement contactElement = driver.findElement(contactLocator);
            assertTrue(contactElement.isDisplayed());
        } catch (NoSuchElementException e) {
            fail("Contact: '" + name + "' was not created successfully");
        }
    }
}