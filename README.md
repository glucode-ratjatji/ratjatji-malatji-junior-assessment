# TODO & Weather App

## Overview
This project is a native Android application that allows users to manage their daily tasks while providing contextual weather information. Users can track their pending and completed tasks, alongside viewing the current temperature, sunrise, and sunset times to better plan their activities. 

## Tech Stack
* **Language:** Kotlin
* **UI Framework:** Jetpack Compose
* **Local Persistence:** Jetpack Room 
* **Networking:** WeatherAPI

## Features & Implementation Status

### TODO List Implementation
- [x] Setup project with local data persistence
- [x] Basic list view with "To do" and "Completed" sections
- [x] Add tasks and persist to local database
- [x] Task details (Title, Description, Completion status)
- [x] Mark tasks as completed (moves to completed section)
- [x] Remove tasks

### Weather Integration
*Note: Weather data is powered by [WeatherAPI](https://www.weatherapi.com/docs/).*
- [x] Fetch and display Current Temperature
- [x] Fetch and display Sunrise time
- [x] Fetch and display Sunset time
- [ ] Generic networking layer
- [ ] Fetch weather data based on device location

### UI/UX & Quality
- [x] Custom UI/UX implementation
- [ ] Support Light and Dark mode
- [ ] Support native dynamic text sizing
- [ ] Unit tests for at least one class

---
| Home Page | Add Task (BottomSheet) |
| :---: | :---: |
| <img src="https://github.com/user-attachments/assets/dbc31983-2313-4adb-a159-542c4f157a4d" width="250" alt="Home Page"/> <br> *Main list and weather view* | <img src="https://github.com/user-attachments/assets/e90575c0-3ebe-4db0-a3c2-e35d61f3b930" width="250" alt="Add Task"/> <br> *BottomSheet for adding tasks* |

| Edit Functionality | Delete Functionality |
| :---: | :---: |
| <img src="https://github.com/user-attachments/assets/0179c71d-489c-4416-9199-0df4e098b753" width="250" alt="Edit Task"/> <br> *Editing an existing task* | <img src="https://github.com/user-attachments/assets/7d4c85ea-fb73-4e8b-b3d9-467c2463bda7" width="250" alt="Delete Task"/> <br> *Removing a task* |

## Setup Instructions
1. Clone the repository to your local machine.
2. Obtain a free API key from [WeatherAPI](https://www.weatherapi.com/).
3. Add your API key to the project (e.g., via `local.properties` or your preferred secure configuration method).
4. Sync the project with Gradle files and build using Android Studio.
