# Specification - Plugin Removal (Refactor)

## Overview
Remove the Android-specific convention plugins (`AndroidApplicationPlugin`, `AndroidApplicationComposePlugin`, `AndroidLibraryPlugin`, `AndroidLibraryComposePlugin`) and their related internal logic and dependencies. This is to streamline the codebase and focus on the remaining plugins (Rust, Lark, GitHub).

## Functional Requirements
- **Delete Files:** Remove all Kotlin files in `com.github.foodiestudio.plugin.android` package.
- **Delete Internal Logic:** Remove `com.github.foodiestudio.plugin.android.internal` package.
- **Dependency Cleanup:** Remove Android Gradle Plugin (AGP), Jetpack Compose, and other Android-specific dependencies from `build.gradle.kts`.
- **Properties Cleanup:** Remove any Android-specific version numbers or flags from `gradle.properties`.
- **README Update:** Remove all documentation sections related to Android plugins from `README.md`.

## Non-Functional Requirements
- **Maintainability:** Reduce the codebase size and complexity.
- **Stability:** Ensure Rust, Lark, and GitHub plugins remain fully functional and can be applied without errors.

## Acceptance Criteria
- [ ] Android plugin source files and the `internal` directory are deleted.
- [ ] `build.gradle.kts` does not contain references to `com.android.application`, `com.android.library`, or Compose-related libraries.
- [ ] `gradle.properties` is cleaned of Android-specific keys.
- [ ] `README.md` no longer mentions Android plugins.
- [ ] The project builds successfully (`./gradlew build`).
- [ ] Manual verification shows that `Rust` and `Lark` plugins are still registered and usable (e.g., `gradle tasks` shows their tasks).

## Out of Scope
- Modifying or refactoring the Rust, Lark, or GitHub plugins.
- Updating GitHub Actions workflows unless they fail due to the removal.