# 🌌 NASA API App

Welcome to the **NASA API App**! 🚀 This Kotlin-based application showcases the wonders of space through a sleek, modern interface, all powered by the NASA API.

## 🏛️ Project Architecture

Our app is built on a clean, modular architecture designed for maintainability and scalability. Here’s a quick breakdown:

### 1. 🎨 **UI**
   - **Purpose**: Displays data and interacts with users.
   - **Contents**: All the app's pages and visual elements.
   - **Subfolder**: `UI-Theme`
     - **Purpose**: Defines the look and feel of the app.
     - **Files**:
       - **Color.kt**: 🎨 Manages the color palette.
       - **Theme.kt**: 📐 Configures theme settings.
       - **Type.kt**: ✒️ Handles typography styles.

### 2. 🌐 **Provider**
   - **Purpose**: Manages network configurations and service instances.
   - **Key File**: `NetworkProvider.kt`

### 3. 🗂️ **Model**
   - **Purpose**: Structures and parses data from the NASA API.
   - **Contents**: Data model classes.

### 4. 🔄 **Datasource**
   - **Purpose**: Acts as the bridge between the network provider and the UI.
   - **Functionality**: Connects to the `Provider`, fetches data from the NASA API, and returns it to the app's UI for display.
   - **Subfolder**: `State` — Manages different UI states, ensuring that the user interface updates reactively as data is retrieved.

## 🚀 Summary

This app is a stellar example of applying clean architecture principles to Android development with Kotlin. By organizing the project into distinct layers—UI, Theme, Provider, Model, and Datasource—we've created a codebase that's easy to maintain, test, and scale. Dive into each component to see how they work together to deliver a smooth and engaging user experience powered by the NASA API.

---
✨ **Explore the cosmos through our app and experience the universe at your fingertips!** ✨
