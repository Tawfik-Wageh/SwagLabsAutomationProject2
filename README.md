# SwagLabsAutomationProject2

## Badges
![Build Status](https://img.shields.io/badge/build-passing-brightgreen)
![License](https://img.shields.io/badge/license-MIT-blue)

## Project Overview
SwagLabsAutomationProject2 is a robust automated testing framework for the Swag Labs web application. It is designed to help teams ensure quality and reliability through automated UI testing. The framework is modular, scalable, and integrates seamlessly with CI/CD pipelines.

## Technologies Used
- Java
- Selenium WebDriver
- TestNG
- Maven
- Allure Reporting
- Log4j2

## Features
- Cross-browser testing (Chrome, Firefox, Edge)
- Page Object Model (POM) design pattern
- Custom utilities for browser actions, assertions, and file handling
- TestNG listeners for enhanced test management
- Allure reporting integration
- Configurable properties for environment, waits, and logging
- Easy integration with CI/CD tools (Jenkins, GitHub Actions)

## Prerequisites
- Java 8 or higher
- Maven 3.6+
- Chrome, Firefox, and Edge browsers installed

## Installation
1. Clone the repository:
   ```
   git clone <repository-url>
   ```
2. Navigate to the project directory:
   ```
   cd SwagLabsAutomationProject2
   ```
3. Install dependencies:
   ```
   mvn clean install
   ```

## Usage
- To run all tests:
  ```
  mvn test
  ```
- To generate Allure report:
  ```
  mvn allure:serve
  ```
  or view the HTML report in `test-outputs/allure-report/`

### Allure Setup
If Allure is not installed, download and install it from [Allure Documentation](https://docs.qameta.io/allure/).
- To install Allure commandline:
  ```
  npm install -g allure-commandline --save-dev
  ```
- To view results locally:
  ```
  allure serve test-outputs/allure-results
  ```

## Project Structure
- `src/main/java/com/swaglabs/` - Main source code
  - `drivers/` - WebDriver factories and options
  - `pages/` - Page Object classes
  - `Utils/` - Utility classes
  - `Listeners/` - TestNG listeners
- `src/test/java/com/swaglabs/tests/` - Test classes
- `src/main/resources/` - Configuration files
- `test-outputs/` - Test reports and logs
- `pom.xml` - Maven configuration

## Reporting
- Allure results are stored in `test-outputs/allure-results/`
- HTML reports are generated in `test-outputs/allure-report/`
- Logs are available in `test-outputs/Logs/`

## Troubleshooting
- **WebDriver not found:** Ensure browser drivers are installed and their paths are set in environment variables.
- **Allure report not generated:** Check that Allure is installed and results are present in the correct folder.
- **Tests not running:** Verify Java and Maven versions, and check for missing dependencies in `pom.xml`.

## FAQ
**Q: How do I add a new test?**
A: Create a new class in `src/test/java/com/swaglabs/tests/` and follow the existing test structure.

**Q: How do I change browser settings?**
A: Update the relevant properties in `src/main/resources/web.properties`.

**Q: How do I contribute?**
A: Fork the repository, create a feature branch, and submit a pull request.

## Contribution
Contributions are welcome! Please fork the repository and submit a pull request. For major changes, open an issue first to discuss what you would like to change.

## Contact
For support or questions, please open an issue or contact the maintainer at [your-email@example.com].

## License
This project is licensed under the MIT License. See the LICENSE file for details.
