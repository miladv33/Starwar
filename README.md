# Star Wars Character Explorer App

A modern Android application built with Jetpack Compose that allows users to explore Star Wars characters, their details, films, and species information using the SWAPI (Star Wars API).

## Architecture Overview

This application follows Clean Architecture principles combined with MVI (Model-View-Intent) pattern for the presentation layer. The project is structured into the following layers:

### Clean Architecture Layers

1. **Presentation Layer (UI)**
   - Implements MVI pattern
   - Uses Jetpack Compose for UI
   - ViewModels manage UI state and handle user interactions
   - Compose screens observe state flows and render UI accordingly

2. **Domain Layer**
   - Contains business logic and use cases
   - Defines repository interfaces
   - Houses domain models
   - Independent of any framework-specific code

3. **Data Layer**
   - Implements repository interfaces
   - Handles data operations and transformations
   - Contains API service definitions
   - Manages local storage (if applicable)

### MVI Implementation

The application follows Model-View-Intent pattern with the following components:

- **Model**: Represented by State classes (e.g., `SearchScreenState`, `DetailScreenState`)
- **View**: Compose screens that render UI based on states
- **Intent**: User actions represented by Event classes (e.g., `SearchScreenEvent`, `DetailScreenEvent`)

#### State Management
- States are immutable data classes
- ViewModels expose states through StateFlow
- UI recomposes based on state changes

## Key Features

- Search Star Wars characters
- View detailed character information
- Browse character appearances in films
- Explore species information
- Dark/Light theme support
- Material Design 3 implementation

## Tech Stack

### Core Libraries
- **Kotlin** - Primary programming language
- **Jetpack Compose** - Modern UI toolkit
- **Coroutines & Flow** - Asynchronous programming
- **Hilt** - Dependency injection
- **Retrofit** - Network calls
- **Gson** - JSON parsing
- **Navigation Compose** - In-app navigation

### Architecture Components
- **ViewModel** - UI state management
- **StateFlow** - Reactive state holder
- **Lifecycle** - Lifecycle-aware components

### Networking
- **OkHttp** - HTTP client
- **Retrofit** - REST client
- **Stetho** - Network inspection (Debug builds)

### Dependencies

```gradle
[versions]
agp = "8.4.0"
animation = "1.7.6"
collectionKtx = "1.4.5"
converterGson = "2.9.0"
gson = "2.10.1"
hiltAndroid = "2.54"
kotlin = "1.9.21"
coreKtx = "1.15.0"
lifecycleRuntimeKtx = "2.8.7"
activityCompose = "1.9.3"
composeBom = "2023.08.00"
navigation-compose = "2.7.7"
```

## Project Structure

```
com.example.snapfood/
├── core/
│   └── di/
│       ├── module/
│       │   ├── AppModule
│       │   ├── NetworkModule
│       │   └── RepositoryModule
│       └── qualifier/
├── data/
│   ├── api/
│   │   └── StarWarsApi
│   ├── dto/
│   ├── mapper/
│   └── repository/
├── domain/
│   ├── model/
│   ├── repository/
│   └── usecase/
└── presentation/
    ├── navigation/
    ├── theme/
    └── ui/
        ├── common/
        ├── details/
        └── search/
```

## UI Components

### Search Screen
- Search box with real-time filtering
- Character list with basic information
- Smooth navigation to details screen

### Details Screen
- Character basic information
- Films appearances with opening crawl
- Species information
- Loading states and error handling

## Theme

The app implements a dynamic Material Design 3 theme with:
- Light/Dark mode support
- Custom color schemes
- Consistent typography
- Reusable components

## Network Layer

### API Configuration
- Base URL: https://swapi.dev/api/
- Token-based authentication support
- Custom header management
- Debug logging and Stetho integration

### Error Handling
- Generic error handling through Resource wrapper
- Loading states management
- Error states with user-friendly messages

## Dependency Injection

Hilt is used for dependency injection with the following modules:
- `AppModule`: Application-wide dependencies
- `NetworkModule`: Network-related dependencies
- `RepositoryModule`: Repository implementations

## Getting Started

### Prerequisites
- Android Studio Hedgehog | 2023.1.1 or newer
- JDK 17 or newer
- Android SDK 34

### Building the Project
1. Clone the repository
2. Open in Android Studio
3. Sync Gradle files
4. Run on an emulator or physical device

## Architecture Flow

1. **User Interaction**
   - User interacts with UI
   - Events are created and sent to ViewModel

2. **ViewModel Processing**
   - ViewModel receives events
   - Processes through use cases
   - Updates state

3. **Data Flow**
   - Use cases interact with repositories
   - Repositories fetch from API
   - Data is mapped to domain models

4. **UI Updates**
   - State updates trigger recomposition
   - UI reflects new state
   - Loading and error states handled

## Contributing

Please read CONTRIBUTING.md for details on our code of conduct and the process for submitting pull requests.

## License

This project is licensed under the MIT License - see the LICENSE.md file for details