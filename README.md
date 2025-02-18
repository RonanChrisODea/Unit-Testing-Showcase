# Unit-Testing-Showcase


This repository demonstrates unit testing for a simple Banking Application written in Java, tested using JUnit 5 and built using Gradle.

## Why Gradle?

This project uses [Gradle](https://gradle.org/) with the **Gradle Wrapper**. This means:

*   **No separate Gradle installation is needed.** The project builds consistently across different environments.
*   **Easy command-line builds:** You can build and run tests directly from the terminal, generating an HTML test report.
*   **IDE Compatibility:** The project can be easily imported into IDEs like IntelliJ IDEA and Eclipse, which have built-in support for Gradle and JUnit.


## Project Structure

The project follows the standard Gradle directory structure:

```
Unit-Testing-Showcase/
├── src/
│   ├── main/
│   │   └── java/ 
│   │       └── ie/
│   │           └── ronanodea/
│   │               └── unitTesting/
│   │                   ├── Account.java
│   │                   ├── AccountManager.java
│   │                   └── BankingApp.java
│   └── test/
│       └── java/  
│           └── ie/
│               └── ronanodea/
│               └── unitTesting/
│                   ├── AccountManagerTest.java
│                   ├── AccountTest.java
│                   └── BankingAppTest.java
├── build.gradle.kts 
├── gradlew
├── gradlew.bat
└── gradle/
└── wrapper/
├── gradle-wrapper.jar
└── gradle-wrapper.properties
```

## Prerequisites

*   **Java Development Kit (JDK) 17 or later:** This project is set up to use JDK 21, but any version 17 or later should work. You can download a JDK from [Adoptium](https://adoptium.net/) or your preferred JDK vendor.  Make sure `java` and `javac` are on your `PATH`.
*   **Git:** Used to clone the project.

## Cloning the Repository

To get a copy of this project on your local machine, open a terminal (or Git Bash on Windows) and run:

```bash
git clone https://github.com/RonanChrisODea/Unit-Testing-Showcase.git
cd Unit-Testing-Showcase
```

## Building and Running Tests

This project uses the **Gradle Wrapper**. You *do not* need to install Gradle separately. The wrapper will automatically download and use the correct version of Gradle for this project.

### Using the Terminal (Recommended)

1.  **Navigate to the project root:** Open a terminal and use the `cd` command to navigate to the `unitTestingShowcase` directory (the directory containing `gradlew.bat`).

2.  **Build and Run Tests:** Execute the following command:

    *   **Windows:**

        ```bash
        .\gradlew.bat test
        ```
    *   **Linux/macOS:**

        ```bash
        ./gradlew test
        ```

    This will compile the code, run all the unit tests, and generate a test report.

3.  **View the Test Report:** After the tests run, open the HTML test report in your browser, found at the following directories:

    *   **Windows:**  `.\build\reports\tests\test\index.html`
    *   **Linux/macOS:** `build/reports/tests/test/index.html`

## Generating and Viewing Javadoc Documentation

1. Generate Javadoc using the Gradle javadoc task:

   *    **Windows:** 

         ```Bash
         .\gradlew.bat javadoc
         ```
   *    **Linux/macOS:**
        ```Bash
         ./gradlew javadoc
        ```
2.  **View Documentation:**

After compiling the documentation navigate to and open the following in your browser:

  *   **Windows:** `.\build\docs\javadoc\index.html`
  *   **Linux/macOS**: `build/docs/javadoc/index.html`

   
## Using an IDE (IntelliJ IDEA, Eclipse, etc.)

1.  **Import the Project:**
    *   **IntelliJ IDEA:**  Choose "Open" and select the `build.gradle.kts` file or the project's root directory. IntelliJ IDEA will automatically detect the Gradle project.
    *   **Eclipse:** Choose "File" -> "Import..." -> "Gradle" -> "Existing Gradle Project". Select the project's root directory.
    * Other IDEs: Use their appropriate "Import Project" feature for Gradle projects.

2.  **Run Tests:**
    *   **IntelliJ IDEA:** Right-click on the `src/test` directory in the Project tool window and choose "Run 'All Tests'".  You can also right-click on individual test classes or methods and run them.
    *   **Eclipse:**  Right-click on the project, go to "Run As" -> "Gradle Test". You can also configure run configurations for specific tests.
    *   **Other IDEs:**  Use the IDE's built-in test runner, usually accessible by right-clicking on test files or directories.

## Cleaning the Build

To remove all generated files (compiled classes, test reports, etc.), run:

*   **Windows:**

    ```bash
    .\gradlew.bat clean
    ```

*   **Linux/macOS:**

    ```bash
    ./gradlew clean
    ```
