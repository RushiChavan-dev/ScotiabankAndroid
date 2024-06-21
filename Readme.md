# Documentation: Project Scotiabank Android Overview and Setup Guide

## Introduction and Overview

### Purpose and Goals

The purpose of this documentation is to provide a comprehensive guide on how my Android application works and how it integrates modern development tools and libraries. Specifically, the application utilizes Dagger Hilt for dependency injection, Jetpack Compose for UI development, Retrofit for network communication, MVVM as the architecture pattern, and OkHttp as the HTTP client. The goal is to demonstrate best practices in Android development, focusing on efficient dependency management, responsive UI design, seamless network communication, and overall application architecture.

### Intended Audience

This documentation is intended for Android developers who want to understand the flow of the application. It targets developers interested in modern Android development techniques and best practices, including dependency injection, reactive UI development, and efficient network communication.

## Architecture

### Overall Architecture

The application follows the MVVM (Model-View-ViewModel) architecture pattern:

- **Model:** Data models representing GitHub user information and repository details.
- **View:** Fragments and Jetpack Compose UI components for user interaction.
- **ViewModel:** Manages UI-related logic and communicates with the repository to fetch data.
- **Repository:** Acts as an abstraction layer for data operations, interacts with Retrofit for network requests.

### Data Mapping Strategy

- **RepoDtoMapper:** Handles mapping between RepoDto objects received from external APIs and Repo objects used within the application's domain logic. Ensures seamless data transformation and integration between data layers.

## Technologies Used

### Programming Language

- Kotlin
- XML
- Gradle

### Frameworks

- Android SDK
- Jetpack Compose
- Retrofit
- Dagger Hilt

### Libraries and Tools

- OkHttp
- Gson
- Material UI
- Glide (for image loading)
- Android Studio (IDE)

## Installation and Setup

### Development Environment Setup

#### Clone, Build, and Run the Application

**Clone the Repository:**

```sh
git clone https://github.com/your_username/your_repository.git
```

**Open in Android Studio:**

1. Launch Android Studio.
2. Choose "Open an existing Android Studio project."
3. Navigate to the directory where you cloned the repository and select the project.

**Set up local.properties:**

1. Locate the `local.properties` file in the root directory of your Android project.
2. Add the following lines to `local.properties`:
   ```properties
   api_key=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
   base_url=https://api.github.com/users/
   ```
   Replace `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` with your actual GitHub API key (personal access token). Or you can use this one for 30 days.
3. Save the `local.properties` file.

**Build and Run:**

1. Build the project to download dependencies and set up the environment.
2. Run the application on an Android device or emulator.

## Features

### Main Features

- **Search GitHub Users:** Allows users to search for GitHub usernames.
- **Display User Information:** Retrieves and displays user profile information including avatar, username, and counts.
- **Fetch User Repositories:** Retrieves a list of repositories for a specified GitHub user.
- **Display User’s Each Repository Details Separately:** Retrieves and displays each repository's details including fork count, all repo forks count, repo name, and repo description.

### Screenshots


<p align="left">
  <img src="https://github.com/RushiChavan-dev/ScotiabankAndroid/assets/50754786/5381877b-bcb7-4598-ad60-243a37bd0cca" width="180"/>
  <img src="https://github.com/RushiChavan-dev/ScotiabankAndroid/assets/50754786/5ae07479-5717-42e0-845e-c955ddd345ce" width="180"/>
  <img src="https://github.com/RushiChavan-dev/ScotiabankAndroid/assets/50754786/7933cda7-14dd-42c0-b5f9-30bb14160c0b" width="180"/>
  <img src="https://github.com/RushiChavan-dev/ScotiabankAndroid/assets/50754786/b8791f21-4a1a-4eee-9c20-f9d305f71034" width="180"/>
  <img src="https://github.com/RushiChavan-dev/ScotiabankAndroid/assets/50754786/dd40e66a-f0d7-4c9d-abf2-74c49254eb37" width="180"/>
</p>

## User Interface (UI) Design

### UI Design Principles

The UI design adheres to Google's Material Design guidelines:

- Clean and intuitive UI with a minimalistic design.
- Responsive and fluid navigation using Jetpack Compose.

### Wireframes/UI Flow Diagrams

Include wireframes or UI flow diagrams if available.

![Group 3](https://github.com/RushiChavan-dev/ScotiabankAndroid/assets/50754786/0069858e-30bb-45ce-9e84-57ff9b7f173d)

## Data Model

### Data Model Overview

- **User:** Represents a GitHub user's profile information (avatar URL, username, bio, etc.).
- **Repo:** Represents a GitHub repository's details (name, description, updatedAt, etc.).

### Data Flow Diagrams

Illustrate data flows between components, focusing on ViewModel interactions with the data layer:
![Group 1](https://github.com/RushiChavan-dev/ScotiabankAndroid/assets/50754786/5796bf43-60a5-4583-8a48-932f1ec9402b)

1. **User Interaction:** Initiates user actions, such as clicking buttons or entering search queries.
2. **Fragment:** Android UI component that interacts with the user and forwards events to the ViewModel.
3. **ViewModel:** Manages UI-related data and business logic. It communicates with the Repository to fetch and manage data.
4. **Repository (UserRepository.kt):** Acts as an intermediary between ViewModel and data sources (Retrofit). It contains methods to perform data operations, including network requests.
5. **Retrofit:** Network library that facilitates communication with remote APIs (Remote API). It sends HTTP requests (Network Request) and receives responses (JSON Response).
6. **Remote API:** Represents the external API endpoint from which data is fetched.
7. **Network Request:** Request sent by Retrofit (Repository) to fetch data from the Remote API.
8. **JSON Response:** Data received from the Remote API in JSON format.
9. **Gson/Moshi:** JSON parsing libraries integrated with Retrofit. They convert JSON responses (JSON Response) into Kotlin objects (UserDto).
10. **UserDtoMapper.kt:** File containing the UserDtoMapper class, responsible for mapping UserDto objects (data transfer objects) to User domain objects (Mapped User Object).
11. **Mapped User Object:** Kotlin objects (User domain model) created from UserDto objects (UserDtoMapper.kt).
12. **Observed LiveData:** StateFlow objects observed by the Fragment or UI components. They reflect changes in data managed by the ViewModel.

## API Documentation

### APIs Used

**GitHub API v3:**

- **Base URL:** `https://api.github.com`
- **Endpoints:**
  - `/users/{username}:` Retrieve user profile information.
  - `/users/{username}/repos:` Retrieve repositories for a user.
- **Authentication:** Requires Bearer token (personal access token) to prevent exceeding the GitHub API rate limit. Using a personal access token allows higher rate limits for authenticated requests, ensuring the application can make more frequent API calls without hitting the limit.

### Example Request

```http
GET https://api.github.com/users/octocat/repos
Authorization: Bearer YOUR_PERSONAL_ACCESS_TOKEN
```

### Example Response

```json
[
  {
    "id": 1296269,
    "name": "Hello-World",
    "full_name": "octocat/Hello-World",
    "description": "This is your first repo!",
    "fork": false,
    "url": "https://api.github.com/repos/octocat/Hello-World"
    // Other fields as per GitHub API documentation
  },
  {
    // Next repository details
  }
]
```

Replace `YOUR_PERSONAL_ACCESS_TOKEN` with your actual GitHub personal access token.

## Testing

### Testing Approach

- **Unit Tests:** Test Network methods, Validation methods, Remote server Reposnses, using JUnit and AndroidJUnit4.
<!-- - **UI Tests:** Test Jetpack Compose UI components using Espresso. -->

### Running Tests

1. Open Android Studio.
2. Navigate to the test directory [com.scotiabank.assignment (test)]
3. Right-click on a test file and select "Run" to execute tests.

### Interpreting Results

View test results in the Android Studio console. Ensure all tests pass without errors.

## Deployment

### Deployment Process

To deploy the application to the Google Play Store:

1. Generate a signed APK or App Bundle.
2. Create a developer account on Google Play Console.
3. Upload the APK or Bundle and complete the submission process.

## Known Issues and Limitations

### Known Issues

- The animation on the ImageView transitions unexpectedly

### Limitations

- Limited error handling for network failures.
