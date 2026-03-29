# Initial Concept
Custom Gradle convention plugins for standardized build configurations across Android, Rust, Lark, and GitHub publishing.

## Product Vision
The "Convention Plugins" project aims to centralize and standardize Gradle build logic for `foodiestudio` repositories. By encapsulating complex configurations (like Android Compose, Cargo builds, and custom publishing tasks) into reusable plugins, it significantly reduces boilerplate in individual project `build.gradle.kts` files, ensures consistency across projects, and accelerates the setup of new modules.

## Target Audience
- Internal developers at `foodiestudio`
- Contributors to projects relying on these centralized build conventions.

## Key Features
- **Android Conventions:** Pre-configured plugins for Android Application and Library modules, with optional Compose support and SDK version management.
- **Rust Integration:** Plugins to seamlessly build Rust desktop applications and generate FFI bindings within the Gradle lifecycle.
- **Lark/Feishu Integration:** Tasks to download and interact with Lark Sheets for localization or configuration management.
- **GitHub Publishing:** Standardized tasks to streamline the process of publishing artifacts to GitHub Packages or Releases.