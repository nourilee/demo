# Selenium Test Automation - About this Demo Project

## Overview

This repository contains Selenium test automation for demo purposes. This project contains automated tests for basic CRUD (Create, Read, Update, Delete) operations using Selenium WebDriver, Java, JUnit 5, and Allure for reporting.

## Prerequisites

- **Java JDK 11**: Ensure Java JDK 11 is installed.
- **Maven**: Apache Maven for dependency management and build automation.

## Setup

1. **Clone the Repository**:
   ```bash
   git clone <repository-url>
   cd <repository-directory>
   ```
2. **Build the Project**:
   ```bash
   mvn clean install
   ```

## Running Tests

1. **Execute Tests**:
   ```bash
   mvn test
   ```
2. **Generate Allure Report**:
   ```bash
   mvn allure:report
   ```
3. **Serve Allure Report**:
   ```bash
   mvn allure:serve
   ```
   This command opens the Allure report in your web browser.
