# Implementation Plan - Plugin Removal (Refactor)

## Overview
This plan outlines the steps to remove Android-specific convention plugins, their internal logic, dependencies, and documentation.

## Phase 1: Preparation and Verification [checkpoint: e8af406]
- [x] Task: Baseline verification. Run `./gradlew clean build` and `./gradlew tasks` to ensure current stable state.
- [x] Task: Conductor - User Manual Verification 'Phase 1: Preparation and Verification' (Protocol in workflow.md)

## Phase 2: Source File Removal [checkpoint: 160c042]
- [x] Task: Delete Android plugin source files.
    - [x] Remove `src/main/java/com/github/foodiestudio/plugin/android/AndroidApplicationComposePlugin.kt`
    - [x] Remove `src/main/java/com/github/foodiestudio/plugin/android/AndroidApplicationPlugin.kt`
    - [x] Remove `src/main/java/com/github/foodiestudio/plugin/android/AndroidLibraryComposePlugin.kt`
    - [x] Remove `src/main/java/com/github/foodiestudio/plugin/android/AndroidLibraryPlugin.kt`
- [x] Task: Delete internal Android-specific logic.
    - [x] Remove the whole `src/main/java/com/github/foodiestudio/plugin/android/internal/` directory and its contents.
- [x] Task: Conductor - User Manual Verification 'Phase 2: Source File Removal' (Protocol in workflow.md)

## Phase 3: Configuration Cleanup
- [ ] Task: Clean up `build.gradle.kts`.
    - [ ] Remove `buildConfig` block (since all its fields are Android-specific).
    - [ ] Remove Android plugin registrations from the `gradlePlugin` block.
    - [ ] Remove `implementation(sharedLibs.android.gradlePlugin)` and other Android-specific dependencies.
- [ ] Task: Clean up `gradle.properties`.
    - [ ] Remove `android.useAndroidX=true`
    - [ ] Remove `android.enableJetifier=true`
- [ ] Task: Clean up `settings.gradle.kts`.
    - [ ] Remove Android-specific overrides from `versionCatalogs`.
- [ ] Task: Conductor - User Manual Verification 'Phase 3: Configuration Cleanup' (Protocol in workflow.md)

## Phase 4: Documentation Update
- [ ] Task: Update `README.md`.
    - [ ] Remove all sections describing Android-related plugins.
- [ ] Task: Conductor - User Manual Verification 'Phase 4: Documentation Update' (Protocol in workflow.md)

## Phase 5: Final Verification
- [ ] Task: Run full build and task list check.
    - [ ] Run `./gradlew clean build`
    - [ ] Run `./gradlew tasks` and verify Rust/Lark/GitHub tasks are present.
- [ ] Task: Conductor - User Manual Verification 'Phase 5: Final Verification' (Protocol in workflow.md)