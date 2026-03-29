# Tech Stack

## Primary Language
- **Kotlin:** Using Kotlin DSL to create expressive and type-safe Gradle build logic.

## Frameworks & APIs
- **Gradle Plugin API:** The core framework used to extend Gradle's build lifecycle, add custom tasks, and configure extensions.

## Execution Environment
- **Java Virtual Machine (JVM):** Targeting Java 17 for plugin compilation and execution.
- **Gradle:** The build tool executing these plugins.

## External Integrations
- **Lark/Feishu Open API (`com.larksuite.oapi:oapi-sdk`):** Used for interacting with Lark Sheets.
- **CSV Parsing (`com.github.doyaaaaaken:kotlin-csv-jvm`):** For data processing related to sheet downloads.
- **GitHub Packages / Releases:** Target registry for publishing artifacts.