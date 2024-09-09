# NASA API App

Welcome to the NASA API App! This is a simple yet powerful application developed in Kotlin, designed to showcase the capabilities of the NASA API with a fresh, modern UI/UX experience. This project serves as a practical example of how to build a well-structured app using Kotlin, focusing on clean architecture principles.

## Project Architecture

The app is organized into the following key components, each with a specific role in ensuring a modular, maintainable, and scalable codebase:

### 1. **UI**
- This folder contains the app's pages and user interface elements.
- It is responsible for displaying data and interacting with the user.

### 2. **UI-Theme**
- Contains the theme-related files:
    - **Color.kt**: Manages the color palette of the app.
    - **Theme.kt**: Defines the overall theme settings.
    - **Type.kt**: Handles typography styles.

### 3. **Provider**
- Houses the **NetworkProvider.kt** file.
- This is where network-related configurations and service instances are managed.

### 4. **Model**
- Contains the data models that represent the structure of the data handled by the app.
- These classes are used to parse the responses from the NASA API.

### 5. **Datasource**
- A crucial part of the architecture, this folder includes the `State` subfolder.
- The `State` classes are responsible for managing different states within the app, ensuring that the UI updates correctly in response to data changes or network events.

## Summary

This app is an excellent example of applying a clean and modular architecture to Android development with Kotlin. By separating concerns into different layers—UI, Theme, Provider, Model, and Datasource—the codebase is easier to maintain, test, and scale as the project grows. Explore the different components to see how they come together to create a seamless user experience using the NASA API.
