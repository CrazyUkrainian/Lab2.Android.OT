# README - UI & Data Layer

## What’s this project about?
This app is built in **Kotlin** and follows a clean structure to keep things organized and maintainable. The two main parts are:
1. **UI Layer** → Handles everything the user sees and interacts with.
2. **Data Layer** → Manages all the app’s data and logic.
 
structure 👇

---

##  UI Layer (What the user sees)
The UI layer is all about visuals and  user  interactions. The code for this located:
```
app/src/main/java/AndroidTest/OT/ui/
```
###  Main Components:
- **Components (`ui/components/`)**
    - `ShiftList.kt` → displays a list of work shifts
    - `UserInfo.kt` → shows user-related details 
    - `ThemeToggleButton.kt` → allows switching between light and dark mode

- **Theme (`ui/theme/`)**
    - `Theme.kt` → defines the app’s overall look
    - `Color.kt` → stores all app colors
    - `Type.kt` → handles text styles and sizes

- **ViewModel (`ui/viewmodel/`)**
    - `AppSettingsViewModel.kt` → manages app settings like themes and preferences

 **How does it work?** UI elements communicate with **ViewModels**, which fetch or modify data when needed.

---

##  Data Layer 
This layer takes care of fetching, storing, and processing data. The code located:
```
app/src/main/java/AndroidTest/OT/data/
```
### Main Components:
- **Models (`data/model/`)** → define the app’s data structures
    - `AppSettings.kt` → stores user preferences
    - `Shift.kt` → represents a work shift (start finish etc)
    - `User.kt` → stores user-related details

- **Repositories (`data/repository/`)** → handle data retrieval and updates
    - `AppSettingsRepository.kt` → manages app settings
    - `ShiftRepository.kt` → fetches and updates shift data
    - `UserRepository.kt` → manages user-related operations

 **How does it work?** The UI asks the **ViewModel** for data, the **ViewModel** requests it from the **Repository**, and the **Repository** retrieves it from storage or an API.

---

##  How UI & Data Work Together
1. A UI component (like `ShiftList.kt`) requests data from the **ViewModel**
2. The **ViewModel** fetches data from the **Repository**
3. The **Repository** gets data from storage or an API and sends it back
4. The **ViewModel** updates the UI when new data arrives

 keeps everything structured, easy to maintain, and avoid all the possible complexity

---

## TL;DR
- **UI Layer** → handles what users see
- **Data Layer** → manages app data
- **MVVM Pattern** → UI → ViewModel → Repository → Data
- keeps the app modular, scalable, and easier to debug



