# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

MathApp is an Android app for a small mathematics faculty to manage student attendance, view/edit schedules, and access educational information. Registration is admin-only.

- Min SDK: 24 (Android 7+), Target/Compile SDK: 35
- No dark theme support; Dynamic Colors supported on Android 12+

## Build Commands

```bash
./gradlew assembleDebug          # Build debug APK
./gradlew assembleRelease        # Build release APK
./gradlew test                   # Run unit tests
./gradlew connectedAndroidTest   # Run instrumented tests on device/emulator
./gradlew lint                   # Run lint checks
./gradlew clean                  # Clean build directory
```

Run a single test class:
```bash
./gradlew test --tests "com.example.mathapp.ExampleUnitTest"
```

## Architecture

Clean Architecture + MVVM with four layers:

```
UI (Jetpack Compose)  →  Presentation (ViewModel + State)  →  Domain (entities/interfaces)  →  Data (repositories)
```

Each feature (timetable, journal, profile, notification) has a parallel structure across all four layers:
- `ui/<feature>/` — Composable screens
- `presentation/<feature>/` — ViewModel + State (sealed interface) + optional Effects (SharedFlow)
- `domain/<feature>/` — Domain data classes
- `data/<feature>/` — Repository interface + `Mock*Repository` implementation

**Dependency Injection:** Koin 3.5.6. Module defined in `di/AppModule.kt`, started in `MathApp` Application class. ViewModels injected via `koinViewModel()` in Composables. No Hilt/Dagger — keep using Koin.

**Navigation:** Type-safe Compose Navigation using `@Serializable` sealed interface `NavigationScreen`. Routes: Timetable (default), Journal, Notifications, Profile. Bottom nav is driven by `NavigationGroup` enum; badge state flows from `RootViewModel`.

**State management pattern:**
- `StateFlow<FeatureState>` collected in screens via `collectAsState()`
- Side effects use `SharedFlow` (see `TimetableEffect`)
- `RootViewModel` owns cross-feature state (e.g., notification badges)

## Key Tech Stack

- Kotlin 2.0.21, AGP 8.10.1
- Jetpack Compose (BOM 2024.09.00) + Material3
- `androidx.navigation:navigation-compose` with Kotlin Serialization for type-safe routes
- Koin 3.5.6 (`koin-android` + `koin-androidx-compose`)
- Retrofit 2.11.0 + OkHttp 4.12.0 + kotlinx.serialization converter
- JVM target: 11
