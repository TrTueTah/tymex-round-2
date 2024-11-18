
# Project Overview

This project contains multiple challenges, each with its own set of tasks and solutions. Below is the folder structure and a brief description of each component.

## Folder Structure
```
.gitignore
Challenge 1/CurrencyConverter/app
src/
├
│   
│           
└── main/
    ├── java/
    │   └── com/example/currencyconverter/
    │       └── data/
    │           └── api/
    │               └── CurrencyApiService.kt
    │           └── model/
    │               └── ApiResponse.kt
    │           └── repository/
    │               └── CurrencyRepositoryImplement.kt
    │       └── di/
    │           └── AppModule.kt
    │       └── domain/
    │           └── repository/
    │               └── CurrencyRepository.kt
    │           └── usecase/
    │               ├── ConvertCurrency.kt
    |               ├── GetExchangeRates.kt
    │               └── GetSupportedCurrencies.kt
    |            └── model/
    |               └── Currency.kt
    │       └── ui/
    │           └── theme/
    │               ├── Color.kt
    │               ├── Theme.kt
    │               └── Type.kt
    │           └── view/
    │               └── MainActivity.kt
    │           └── viewmodel/
    │               └── MainViewModel.kt
    │       └── utils/
    │           ├── RetrofitInstance.kt
    │       └── CurrencyConverterApp.kt
    ├
    ├── AndroidManifest.xml
    └── build.gradle.kts
Challenge 2/
    Question2.1/
        Product.java
        ProductInventoryManagement.java
    Question2.2/
        FindMissingNumber.java

README.md
LICENSE
```

## Challenges

### Challenge 1

A Currency Converter app built with **Kotlin**, following the **Clean Architecture** pattern. This project uses **Retrofit** for API requests and **Koin** for Dependency Injection.

**Project Structure and Component Definitions:**

*Data Layer (data/)*
The Data Layer is responsible for handling data operations such as API calls, data storage, and retrieval. It consists of:
- api/:
  - CurrencyApiService.kt: Defines the interface for Retrofit API calls. It includes functions for fetching exchange rates and supported currencies from a remote server.
- model/:
  - ApiResponse.kt: Represents the data model for the API response. It typically includes fields like exchange rates, base currency, and supported currencies.
- repository/:
  - CurrencyRepositoryImplement.kt: Implements the CurrencyRepository interface. It handles data fetching from both network (API) and local storage if needed, providing a single source of truth for currency data.

*Dependency Injection (di/)*
The Dependency Injection (DI) Layer uses Dagger Hilt or another DI framework to provide dependencies throughout the app.
- AppModule.kt: Defines the dependency injection module, providing instances of Retrofit, API service, and repository. It ensures that dependencies are created and managed efficiently.

*Domain Layer (domain/)*
The Domain Layer contains business logic and use cases. It abstracts away implementation details from the UI and Data layers.
- repository/:
  - CurrencyRepository.kt: An interface defining methods for data operations. It acts as an abstraction layer for the repository implementation, facilitating unit testing and modularity.
- usecase/:
  - ConvertCurrency.kt: A use case class that handles the currency conversion logic.
  - GetExchangeRates.kt: A use case class that fetches the latest exchange rates from the repository.
  - GetSupportedCurrencies.kt: A use case class that retrieves the list of supported currencies.
- model/:
  - Currency.kt: Represents a data model for currency information, including properties like currency code and name.

*UI Layer (ui/)*
The UI Layer contains components related to the user interface, including themes, views, and view models.
- theme/:
  - Color.kt: Defines color resources for the app's theme.
  - Theme.kt: Sets up the overall theme, including colors, typography, and other UI elements.
  - Type.kt: Defines text styles and typography for the app.
- view/:
  - MainActivity.kt: The main entry point of the app's UI. It hosts the main screen, initializes the ViewModel, and displays the user interface for currency conversion.
- viewmodel/:
  - MainViewModel.kt: A ViewModel that handles UI-related data. It communicates with use cases to fetch data and update the UI state.

*Utils (utils/)*
Contains utility classes and helper functions that are used throughout the app.
- RetrofitInstance.kt: Provides a singleton instance of Retrofit, configured with the base URL, JSON converter (like Gson), and other settings.

*Application Class*
- CurrencyConverterApp.kt: The application class where you initialize app-wide configurations, such as setting up Dagger Hilt for dependency injection.

*Configuration Files*
- AndroidManifest.xml: Defines essential app information, including the main activity, permissions, and app-wide configurations.
- build.gradle.kts: The Gradle build script for the app module, written in Kotlin DSL. It includes dependencies, build configurations, and plugins.


### Challenge 2

This challenge includes two questions, each with its own Java class:

- *Question2.1/*:
  - `ProductInventoryManagement.java`: Contains methods to manage inventory, including calculating total value, finding the most expensive product, checking stock, and sorting products by price and quantity.
  - `Product.java`: Contains a class representing a product with properties such as name, price, and quantity.

- *Question2.2/*:
  - `FindMissingNumber.java`: Contains a method to find the missing number in an array.

## Getting Started

To run Android Application in Challenge 1, you need to satisfy these requirements:
- [Android Studio Download](https://developer.android.com/studio)
- Virtual Device (AVD) with Android SDK 35 or higher (Android 14).
- Java Development Kit (JDK) installed on your system.

In Challenge 2, you just need any Java IDE or text editor with Java support, such as IntelliJ IDEA, Visual Studio Code.

## Running the Android Application

To run the Android application in Challenge 1, follow these steps:

1. Open the project in Android Studio.
2. You need to add the **API_KEY** and the **BASE_URL** to the *local.properties* to run this Application. Add these variables like this:
```
API_KEY=""
BASE_URL=""
```
4. Connect an Android device or start an emulator.
5. Click on the "Run" button in Android Studio to build and run the application on the connected device or emulator.

## Running the Java Classes

To run the Java classes in Challenge 2, follow these steps:

1. Open the Java files in an IDE or text editor with Java support.
2. Run the `main` method in the respective Java class to execute the code and see the output.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.
