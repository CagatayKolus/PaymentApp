# PaymentApp
An android application which uses example payment results for listing payment methods.

## Prerequisites

#### 1. Check the API

If the app cannot list payment methods, check the listresult endpoint from the link below.

	https://raw.githubusercontent.com/optile/checkout-android/develop/shared-test/lists/listresult.json

#### 2. Ready to run.

## Features
- Listing payment methods
- Caching results (offline capability)
- Pull to refresh
- Unit tests

## Tech Stack
- **Kotlin** - Officially supported programming language for Android development by Google
- **Kotlin DSL** - Alternative syntax to the Groovy DSL
- **Coroutines** - Perform asynchronous operations
- **Flow** - Handle the stream of data asynchronously
- **Android Architecture Components**
  - **LiveData** - Notify views about data changes
  - **Room** - Persistence library
  - **ViewModel** - UI related data holder
  - **ViewBinding** - Allows to more easily write code that interacts with views
- **Hilt** - Dependency Injection framework
- **Retrofit** - Networking library
- **Moshi** - A modern JSON library for Kotlin and Java
 
## Local Unit Tests
- The project uses MockWebServer (scriptable web server) to test API interactions.

## Screenshots
![payment_app](https://user-images.githubusercontent.com/25778714/131154528-5505ffcb-b4a0-404d-8450-1d087d973c4a.jpg)

## Architecture
![arch500](https://user-images.githubusercontent.com/25778714/113482640-3801f100-94a8-11eb-98d6-e15cb21a905b.png)
