# OrangeHRM Testing Automation Project

## Executive Summary
A professional, enterprise-grade test automation framework built for OrangeHRM - an open-source HR management system. This framework demonstrates industry best practices in test automation with Selenium WebDriver, TestNG, and Allure reporting.

---

## Table of Contents
1. [Project Overview](#project-overview)
2. [Architecture & Design](#architecture--design)
3. [Technology Stack](#technology-stack)
4. [Core Features](#core-features)
5. [Test Coverage](#test-coverage)
6. [Project Structure](#project-structure)
7. [Key Components Deep Dive](#key-components-deep-dive)
8. [Prerequisites](#prerequisites)
9. [Setup & Installation](#setup--installation)
10. [How to Run Tests](#how-to-run-tests)
11. [Reporting & Logging](#reporting--logging)
12. [Configuration](#configuration)
13. [Best Practices Implemented](#best-practices-implemented)

---

## Project Overview
This automation framework is designed to test the OrangeHRM demo application (https://opensource-demo.orangehrmlive.com/). It covers end-to-end scenarios across multiple HR modules including authentication, employee management, claims, and more.

**Key Objectives:**
- Provide reliable, maintainable, and scalable test automation
- Enable fast feedback on application quality
- Support regression testing
- Generate comprehensive test reports

---

## Architecture & Design

### Architecture Diagram
```
┌─────────────────────────────────────────────────────────────┐
│                        Test Layer (TestNG)                   │
│  ┌─────────────┐ ┌─────────────┐ ┌─────────────┐            │
│  │ LoginTest   │ │CreateEmpTest│ │AssignClaim  │            │
│  └─────────────┘ └─────────────┘ └─────────────┘            │
└─────────────────────────────────────────────────────────────┘
                              ↓
┌─────────────────────────────────────────────────────────────┐
│                      Page Object Layer                       │
│  ┌─────────────┐ ┌─────────────┐ ┌─────────────┐            │
│  │ LoginPage   │ │DashboardPage│ │CreateEmpPage│            │
│  └─────────────┘ └─────────────┘ └─────────────┘            │
└─────────────────────────────────────────────────────────────┘
                              ↓
┌─────────────────────────────────────────────────────────────┐
│                     Utility Layer                            │
│  ┌──────────┐ ┌──────────┐ ┌──────────┐ ┌──────────┐       │
│  │ Waits    │ │Elements  │ │  Logs    │ │ScreenShots│       │
│  └──────────┘ └──────────┘ └──────────┘ └──────────┘       │
└─────────────────────────────────────────────────────────────┘
                              ↓
┌─────────────────────────────────────────────────────────────┐
│                   Driver & Infrastructure                    │
│  ┌──────────────────────────────────────────────────────┐  │
│  │ ThreadLocal WebDriver (Chrome Browser)               │  │
│  └──────────────────────────────────────────────────────┘  │
└─────────────────────────────────────────────────────────────┘
```

### Design Patterns Implemented
1. **Page Object Model (POM)**
   - Separates UI interactions from test logic
   - Each page has its own class with locators and actions
   - Enables code reuse and easier maintenance

2. **Fluent Interface Pattern**
   - Method chaining for readable test scripts
   - Example: `page.enterUsername().enterPassword().clickLogin()`

3. **Singleton Pattern (via ThreadLocal)**
   - Thread-safe WebDriver management
   - One driver instance per test thread

4. **Utility/Helper Pattern**
   - Reusable utility classes for common operations
   - Waits, elements, logs, screenshots centralized

---

## Technology Stack

| Category | Technology | Version | Purpose |
|----------|------------|---------|---------|
| **Programming Language** | Java | 21 | Core development |
| **Browser Automation** | Selenium WebDriver | 4.44.0 | UI interaction |
| **Testing Framework** | TestNG | 7.12.0 | Test management & execution |
| **Logging** | Apache Log4j2 | 2.26.0 | Execution logging |
| **Reporting** | Allure Framework | 2.24.0 | Visual test reports |
| **Build Tool** | Maven | 3.x+ | Dependency & build management |
| **Aspect-Oriented** | AspectJ | 1.9.22 | Allure integration |
| **File Handling** | Apache Commons IO | 2.22.0 | Screenshot file operations |

---

## Core Features

### 1. Thread-Safe WebDriver Management
- Uses `ThreadLocal<ChromeDriver>` for parallel execution safety
- Automatic driver setup and teardown per test method
- Prevents race conditions in multi-threaded environments

### 2. Intelligent Element Interaction
- **Auto-waiting**: Automatic visibility and clickability waits before interactions
- **Smart Scrolling**: JavaScript-based scrolling to elements before click
- **Dropdown Handling**: Built-in Select class support
- **Data Entry**: Focus + sendKeys for reliable input
- **Element Retrieval**: Safe text extraction with waits

### 3. Comprehensive Waiting Strategies
- **Visibility Waits**: Wait for element to be visible (10s timeout)
- **Clickability Waits**: Wait for element to be clickable (15s timeout)
- **URL Waits**: Wait for URL to match or contain expected value
- **Demo Sleeps**: Configurable sleep for demonstration purposes

### 4. Advanced Logging System
- **Multi-level logging**: Trace, Info, Debug, Error levels
- **Automatic class detection**: Logs include calling class name
- **Date-stamped log files**: Separate log file per execution
- **Structured logs**: Easy to parse and analyze

### 5. Screenshot & Reporting Integration
- **On-demand screenshots**: Capture screenshots at any point
- **Auto-attachment**: Screenshots automatically attached to Allure reports
- **Organized storage**: Screenshots saved in dedicated directory
- **Error handling**: Graceful failure if screenshot can't be taken

### 6. Test Organization
- **TestNG Annotations**: `@Test`, `@BeforeMethod`, `@AfterMethod` for lifecycle management
- **Test Suite XML**: Configure which tests to run via testing.xml
- **Positive & Negative Testing**: Both valid and invalid scenarios covered
- **Clean Test Methods**: Readable, focused, and maintainable

---

## Test Coverage

### Test Modules Covered

| Module | Test Class | Test Scenarios |
|--------|------------|----------------|
| **Authentication** | `LoginTest` | • Valid login<br>• Invalid username<br>• Invalid password |
| **Logout** | `LogoutTest` | • Successful logout flow |
| **Dashboard** | `DashboardTest` | • Dashboard page verification |
| **Employee Management** | `CreateEmployeeTest` | • Valid employee creation<br>• Missing first name (negative) |
| | `DeleteEmployeeTest` | • Employee deletion flow |
| **Claims** | `AssignClaimTest` | • Claim assignment process |
| **Posts** | `CreatePostTest` | • Post creation |
| | `DeletePostTest` | • Post deletion |

---

## Project Structure

```
OrangeHRMTesting/
│
├── src/
│   ├── main/
│   │   ├── java/com/orangehrm/
│   │   │   ├── Drivers/
│   │   │   │   └── WebDriver.java                    # Thread-safe driver manager
│   │   │   │
│   │   │   ├── Pages/                                # Page Object Classes
│   │   │   │   ├── LoginPage.java
│   │   │   │   ├── LogoutPage.java
│   │   │   │   ├── DashboardPage.java
│   │   │   │   ├── AssignClaimPage.java
│   │   │   │   ├── CreateEmployeePage.java
│   │   │   │   ├── DeleteEmployeePage.java
│   │   │   │   ├── CreatePostPage.java
│   │   │   │   └── DeletePostPage.java
│   │   │   │
│   │   │   └── Utils/                                # Utility Classes
│   │   │       ├── AllureReports.java               # Allure integration
│   │   │       ├── Elements.java                    # Element interaction helpers
│   │   │       ├── Logs.java                        # Log4j2 wrapper
│   │   │       ├── ScreenShots.java                 # Screenshot capture
│   │   │       └── Waits.java                       # WebDriverWait utilities
│   │   │
│   │   └── resources/
│   │       ├── allure.properties                    # Allure configuration
│   │       └── log4j2.properties                    # Log4j2 configuration
│   │
│   └── test/
│       ├── java/com/orangehrm/tests/                # Test Classes
│       │   ├── LoginTest.java
│       │   ├── LogoutTest.java
│       │   ├── DashboardTest.java
│       │   ├── AssignClaimTest.java
│       │   ├── CreateEmployeeTest.java
│       │   ├── DeleteEmployeeTest.java
│       │   ├── CreatePostTest.java
│       │   └── DeletePostTest.java
│       │
│       └── resources/
│           └── testing.xml                          # TestNG suite file
│
├── test-outputs/
│   ├── Logs/                                         # Execution logs (timestamped)
│   ├── allure-results/                               # Allure raw data
│   └── screenshots/                                  # Captured screenshots
│
├── .gitignore
├── pom.xml                                           # Maven configuration
└── README.md
```

---

## Key Components Deep Dive

### 1. WebDriver Manager (`WebDriver.java`)
```java
// ThreadLocal ensures thread safety
private static final ThreadLocal<ChromeDriver> driverThreadLocal = new ThreadLocal<>();

// Methods:
setupDriver(String browser)    // Initialize driver
getDriver()                    // Get current thread's driver
quitDriver()                   // Clean up driver
```
**Features:**
- Chrome browser only (configurable)
- Auto-maximized window
- Proper cleanup to prevent memory leaks

### 2. Elements Utility (`Elements.java`)
Core element interaction methods:
- `sendData()` - Send text to input fields
- `submitData()` - Submit forms
- `clickButton()` - Click buttons
- `getData()` - Extract text
- `getElementsSize()` - Count elements
- `scrolling()` - Scroll to element
- `selectingFromDropDown()` - Select dropdown option

### 3. Waits Utility (`Waits.java`)
```java
waitUntilVisible(driver, locator)      // 10 seconds
waitForClickable(driver, locator)      // 15 seconds
waitForUrlToBe(driver, expectedUrl)
waitForUrlContains(driver, expected)
sleepForDemo()                         // 500ms
```

### 4. Page Object Example (`LoginPage.java`)
```java
// Locators defined as private final By
private final By username = By.cssSelector("...");

// Fluent methods return this
public LoginPage enterUsername(String username) {
    Elements.sendData(driver, this.username, username);
    return this;
}

// Assertion methods
public void assertSuccessfulLogin(String expectedUrl)
public void assertInvalidCredentials()
```

---

## Prerequisites

### Software Requirements
- **Java Development Kit (JDK)**: 21 or later
- **Maven**: 3.6.x or later
- **Chrome Browser**: Latest stable version
- **Allure Command Line**: For report generation (optional but recommended)
- **IDE**: IntelliJ IDEA, Eclipse, or VS Code (with Java extensions)

### Environment Setup
1. Verify Java installation:
   ```bash
   java -version
   ```

2. Verify Maven installation:
   ```bash
   mvn -version
   ```

3. Install Allure (optional):
   ```bash
   # macOS with Homebrew
   brew install allure
   
   # Verify installation
   allure --version
   ```

---

## Setup & Installation

### Step 1: Clone or Download the Project
```bash
git clone <repository-url>
cd OrangeHRMTesting
```

### Step 2: Build the Project
```bash
mvn clean install
```
This will:
- Download all dependencies
- Compile the source code
- Run initial checks

### Step 3: Verify Configuration
- Check `pom.xml` for correct dependencies
- Review `testing.xml` for test suite configuration
- Verify property files in `src/main/resources/`

---

## How to Run Tests

### Option 1: Run All Tests via Maven
```bash
mvn test
```
Runs all tests defined in `testing.xml`

### Option 2: Run Specific Test Class
```bash
mvn test -Dtest=LoginTest
```

### Option 3: Run Specific Test Method
```bash
mvn test -Dtest=LoginTest#testValidLogin
```

### Option 4: Run via IDE
- Right-click on `testing.xml` → Run
- Or right-click on any test class/method → Run

---

## Reporting & Logging

### 1. Allure Reports (Visual)
Generate and view beautiful interactive reports:

```bash
# Generate and serve report
allure serve test-outputs/allure-results

# Or generate report files only
allure generate test-outputs/allure-results -o allure-report --clean
```

**Allure Report Features:**
- Test execution timeline
- Pass/fail statistics
- Attached screenshots
- Detailed test steps
- History trends (if using CI/CD)
- Environment information

### 2. Log Files
- **Location**: `test-outputs/Logs/`
- **Naming**: `log_YYYY-MM-DD_HH-MM-SS PM.log`
- **Content**: Detailed execution logs with timestamps and log levels

### 3. Screenshots
- **Location**: `test-outputs/screenshots/`
- **Format**: PNG images
- **Attachment**: Auto-attached to Allure reports

---

## Configuration

### Browser Configuration
Edit `src/main/java/com/orangehrm/Drivers/WebDriver.java`:
```java
// Currently supports Chrome (switch statement can be extended)
setupDriver("chrome");
```

### Test Suite Configuration
Edit `src/test/resources/testing.xml`:
```xml
<suite name="OrangeHRM Suite" parallel="false">
    <test name="OrangeHRM Tests">
        <classes>
            <class name="com.orangehrm.tests.LoginTest"/>
            <!-- Add/remove test classes here -->
        </classes>
    </test>
</suite>
```

### Application URL
URLs are defined in each test class:
- Login: `https://opensource-demo.orangehrmlive.com/web/index.php/auth/login`
- Dashboard: `https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index`

### Default Credentials
- **Username**: `Admin`
- **Password**: `admin123`

---

## Best Practices Implemented

1. **Page Object Model (POM)**
   - Separates concerns
   - Easy maintenance when UI changes
   - Reusable page methods

2. **Fluent Interfaces**
   - Readable test code
   - Method chaining improves flow

3. **Thread Safety**
   - ThreadLocal for WebDriver
   - Safe for parallel execution

4. **Explicit Waits**
   - No Thread.sleep() except for demos
   - Element-specific waits

5. **Centralized Utilities**
   - DRY (Don't Repeat Yourself) principle
   - Consistent behavior across tests

6. **Comprehensive Logging**
   - Debugging made easier
   - Audit trail of test execution

7. **Screenshot on Demand**
   - Visual evidence
   - Attached to reports

8. **Positive + Negative Testing**
   - Both valid and invalid scenarios
   - Better coverage

9. **TestNG Annotations**
   - Proper test lifecycle
   - Setup/teardown per method

10. **Maven Build Management**
    - Standard project structure
    - Easy dependency management

---

## Future Enhancements (Optional)

- [ ] Add multi-browser support (Firefox, Edge, Safari)
- [ ] Implement data-driven testing (Excel/CSV/JSON)
- [ ] Add parallel execution support
- [ ] Integrate with CI/CD (Jenkins, GitHub Actions)
- [ ] Add configuration properties file
- [ ] Implement test listeners for better reporting
- [ ] Add API testing layer
- [ ] Database validation integration
- [ ] Docker containerization
- [ ] Performance testing integration

---

## Troubleshooting

### Common Issues

1. **ChromeDriver version mismatch**
   - Selenium 4.44.0 automatically manages ChromeDriver
   - Ensure Chrome browser is updated

2. **Dependencies not downloading**
   - Check internet connection
   - Delete `.m2/repository` and run `mvn clean install`

3. **Allure not found**
   - Install Allure CLI as per prerequisites
   - Or use `mvn allure:serve` instead

4. **Tests failing due to timeouts**
   - Adjust wait times in `Waits.java`
   - Check network connectivity

---

## License & Credits

- **OrangeHRM**: Open-source HR management system
- **Demo Application**: https://opensource-demo.orangehrmlive.com/
- **Frameworks**: Selenium, TestNG, Allure, Log4j2

---

**Built with ❤️ for test automation excellence!**
