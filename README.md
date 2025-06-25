---

# 🌌 NASA API App

Welcome to the **NASA API App**! 🚀 This modern Android application built with **Kotlin** and **Jetpack Compose** brings you daily awe-inspiring content from the NASA APOD (Astronomy Picture of the Day) API — in a clean, reactive, and scalable architecture.

---

## 🧱 Clean Architecture Overview

The app is structured into **well-separated layers**, each with a clear responsibility. This enhances maintainability, testability, and scalability.

### 🖼️ 1. **Presentation Layer (UI)**

* **Role**: Handles all user-facing components.
* **Includes**: Composables, Screens, `HomeScreen`, and state rendering.
* **State Management**: `HomeViewModel` exposes `DataUiState` (Loading, Success, Error) via `StateFlow`.

> ✅ Built with **Jetpack Compose**.

---

### 🔄 2. **Domain Layer**

* **Role**: Holds business logic and entity models.
* **Components**:

    * `HomeRepository` interface
    * `PlanetaryApodEntity`
    * `PlanetaryApodMapper` (from DTO to Entity)

> ✅ Pure Kotlin – no Android dependencies.

---

### 🌐 3. **Data Layer**

* **Role**: Bridges the domain and remote data source.
* **Components**:

    * `HomeApi` (Retrofit service for NASA APOD)
    * `HomeDataSource` & `HomeDataSourceImpl`
    * `HomeRepositoryImpl`

> ✅ All network calls are done via **Retrofit** with **Gson** converter and **Coil** for image loading.

---

### 🧪 4. **Core / DI Layer**

* **Dependency Injection**: Configured using **Koin**

    * `appModule` for global/shared services (e.g., Retrofit, Gson)
    * `homeDI` for Home feature-specific components

* **App Startup**: `MainApplication` initializes Koin at runtime.

---

### 🎨 5. **UI Theme**

* **Files**:

    * `Color.kt`: Centralized color palette.
    * `Theme.kt`: Applies light/dark/dynamic theming.
    * `Type.kt`: Typography and font configuration.

> ✅ Fully supports **Material 3** with dynamic theming (Android 12+).

---

## 🚀 Tech Stack

| Layer        | Technology                    |
| ------------ | ----------------------------- |
| UI           | Jetpack Compose, Material3    |
| DI           | Koin                          |
| Network      | Retrofit + Gson               |
| Async / Flow | Kotlin Coroutines + StateFlow |
| Images       | Coil                          |
| Architecture | MVVM + Clean Architecture     |

---

## 🔧 Project Setup

Make sure your environment uses:

* Kotlin `2.1.21`
* Compose Compiler `1.6.x+`
* Java `21` (Zulu)
* AGP `8.1.1`
* Gradle `8.11.1`
* Compile/Target SDK `35`

> 🧠 You’ll also need a NASA API key. The app uses `"DEMO_KEY"` by default, but you can replace it in `HomeDataSourceImpl`.

---

## 📎 Summary

This project demonstrates modern **Android development best practices** using:

* ✅ Modular & layered architecture
* ✅ Reactive state management
* ✅ Fully declarative UI
* ✅ Testable and scalable data flow

> ✨ Explore the cosmos at your fingertips — powered by clean code and NASA's open data ✨

---