# E-Municipal Management Test Automation

## 🚀 Project Overview
Comprehensive test automation framework for E-Municipal Grievance Management System using Selenium WebDriver, TestNG, and Cucumber BDD.

## 🏗️ Architecture
- **Page Object Model** with PageFactory
- **BDD** with Cucumber
- **Data-Driven Testing** with JSON/Properties
- **Environment Management** (Dev/Staging/Prod)
- **Thread-Safe** WebDriver management
- **Modular Structure** for scalability

## 📁 Project Structure
```
src/
├── main/java/com/aadrika/egovernance/
│   ├── enums/                    # Browser, Environment enums
│   ├── pages/
│   │   ├── common/              # BasePage
│   │   ├── login/               # LoginPage
│   │   └── grievance/           # GrievanceRegistrationPage
│   └── utils/
│       ├── constants/           # FrameworkConstants
│       ├── driver/              # DriverManager, DriverFactory
│       └── helpers/             # ConfigReader, CaptchaSolver
├── test/java/com/aadrika/egovernance/
│   ├── base/                    # BaseTest
│   └── tests/
│       ├── smoke/              # Smoke tests
│       └── regression/         # Regression tests
└── test/resources/
    ├── config/                 # Environment configs
    ├── features/               # BDD feature files
    ├── testdata/              # Test data files
    └── suites/                # TestNG suites
```

## 🛠️ Technologies Used
- **Java 11**
- **Selenium WebDriver 4.11.0**
- **TestNG 7.8.0**
- **Cucumber 7.14.0**
- **Maven** for dependency management
- **WebDriverManager** for driver management
- **Jackson** for JSON processing
- **Tess4J** for CAPTCHA solving

## 🚦 Getting Started

### Prerequisites
- Java JDK 11+
- Maven 3.6+
- Chrome Browser
- Git

### Installation
```bash
git clone https://github.com/yourusername/e-municipal-management.git
cd e-municipal-management
mvn clean install
```

## 🏃‍♂️ Running Tests

### Run All Tests
```bash
mvn test
```

### Run Specific Environment
```bash
mvn test -Denv=dev
mvn test -Denv=staging
mvn test -Denv=prod
```

### Run Specific Browser
```bash
mvn test -Dbrowser=chrome
mvn test -Dbrowser=firefox
```

### Run Specific Test Suite
```bash
mvn test -Dsuite=smoke_suite.xml
mvn test -Dsuite=regression_suite.xml
```

### Run with Tags
```bash
mvn test -Dcucumber.filter.tags="@smoke"
mvn test -Dcucumber.filter.tags="@regression"
```

## 📊 Test Reports
- **TestNG Reports**: `target/surefire-reports/`
- **Cucumber Reports**: `target/cucumber-html-reports/`
- **Screenshots**: `screenshots/` (on failure)

## 🔧 Configuration

### Environment Configuration
- **Dev**: `src/test/resources/config/dev.properties`
- **Staging**: `src/test/resources/config/staging.properties`
- **Prod**: `src/test/resources/config/prod.properties`

### Test Data
- **JSON Data**: `src/test/resources/testdata/grievance_data.json`
- **User Data**: Centralized in JSON format
- **Configuration**: Environment-specific properties

## 🧪 Test Categories
- **@smoke**: Critical functionality tests
- **@regression**: Full regression suite
- **@grievance**: Grievance module tests
- **@negative**: Negative scenario tests

## 🤝 Contributing
1. Fork the repository
2. Create feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit changes (`git commit -m 'Add AmazingFeature'`)
4. Push to branch (`git push origin feature/AmazingFeature`)
5. Open Pull Request

## 📝 License
This project is licensed under the MIT License.

## 📞 Contact
- **Email**: rajeev.qa.sparrow@gmail.com
- **Project**: E-Municipal Management Automation