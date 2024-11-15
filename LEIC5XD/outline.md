
## Week 1 - 09/09/2024 to 15/09/2024
### Subject: Course introduction and work environment setup

#### Topic breakdown:
* Syllabus, teaching methodology and bibliography.
  * Evaluation
  * Resources

#### For reference:
* [Download Android Studio & App Tools](https://developer.android.com/studio)
* [Android API Levels](https://apilevels.com/)
* [SDK Platform release notes | Android Developers](https://developer.android.com/studio/releases/platforms)
* [Coding Conventions | Kotlin Documentation](https://kotlinlang.org/docs/coding-conventions.html)

#### Video lecture (in Portuguese): 
* [Android @ ISEL - 2024 - (01) Apresentação](https://www.youtube.com/live/vA3tJif2mvE?si=LbBhVcN_8Em17DrA)

### Practical Class
* Goal: Install Android Studio and create a new project (the *Hello Android* app) using Andrdoid Studio's wizard.
* Challenge: From the myriad of questions that may arise, elect the two most relevant  for improving your mental model of the Android platform.

### Bonus Content
* [Lecture video (in Portuguese): Revisiting Classes, Values and Objects](https://www.youtube.com/live/tiDL_uQmkdI?si=7__MpJ_GPCyVttIK)

## Week 2 - 16/09/2024 to 22/09/2024
### Subject: Building the UI with Jetpack Compose

#### Topic breakdown:
* The Activity component as the UI host
  * Basic lifecycle: `onCreate`, `onStart`, `onStop` and `onDestroy`
  * Concurrency model: execution warranties and constraints of lifecycle methods
* Jetpack Compose (revisions from TDS course)
  * `@Composable` functions: the building blocks of the UI
  * Mental model: the UI as a function of the state (state -> `@Composable` -> UI)
  * Stateless and Stateful `@Composable` functions
  * Concurrency model: execution warranties and constraints of event handlers

* Demos:
  * Logging Activity. Lets log the lifecycle events. 
  * Dice Roller application (v0.1). The dice roll is made by pressing "The Roll it" button and the result is displayed on the screen as a dice face. The dice face is a drawable resource, 
 which is immediately displayed on the screen when the button is pressed.
  
* For reference:
  * [Thinking in Compose](https://developer.android.com/develop/ui/compose/mental-model)
  * [Compose layout basics - Jetpack](https://developer.android.com/jetpack/compose/layouts/basics)
  * [State and Jetpack Compose](https://developer.android.com/jetpack/compose/state)
  * [Test your Compose layout](https://developer.android.com/develop/ui/compose/testing)

#### Video lecture (in Portuguese):
* [Android @ ISEL - 2024 - (02-A) Construindo a UI com Compose](https://youtu.be/c58d1Tb0jzU?si=14L1inlQw2e86OGU)

### Practical Class
* Goal: Create a simple UI with Jetpack Compose
* Challenge: Implement the CrowdTally application, used for counting the attendance in a venue with limited capacity. The application is comprised of a screen with the current count and 
buttons to decrease and increase that count.
* [Challenge solution: CrowdTally live coding session video](https://youtu.be/Qw7E5oX5vTI?si=XbZ_qjhnQWHBKEfO)

### Bonus Content
* [Lecture video (in Portuguese): Revisiting Polymorphism and Inheritance](https://youtu.be/m3b6mTpMecc?si=o-If3d2CTfxbXOqb)

## Week 3 - 23/09/2024 to 29/09/2024
### Subject: Building the UI with Jetpack Compose - State management
#### Topic breakdown:
* The Activity component: continued
  * Basic lifecycle, revisited: behavior on configuration changes
  * Implications of reconfigurations on the UI state
* Jetpack Compose, continued
  * State hoisting: lifting the state to the parent composable
  * Preserving UI state
    * `rememberSaveable`
    * The `Parcelable` contract and automatic implementation with `@Parcelize`
    * Compose's `Saver` contract
  * Side effects: `LaunchedEffect`
* Architecting the UI: introduction
  * Suporting multiple screen orientations: `LocalConfiguration.current.orientation` 
  * Implementing the screen as a state machine

### For reference:
* [State hoisting](https://developer.android.com/jetpack/compose/state#state-hoisting)
* [Save UI state in Jetpack Compose](https://developer.android.com/develop/ui/compose/state-saving)
* [Parcelable implementation generator](https://developer.android.com/kotlin/parcelize)
* [Side-effects in Compose](https://developer.android.com/develop/ui/compose/side-effects)
* [Architecting your Compose UI](https://developer.android.com/jetpack/compose/architecture)

#### Video lecture (in Portuguese):
* [Android @ ISEL - 2024 - (03-A) Estado de apresentação com Compose](https://www.youtube.com/live/hyO4i8zM4mE?si=wHxervtnenn6j-FM) 
* [Android @ ISEL - 2024 - (03-B) Estado de apresentação com Compose](https://www.youtube.com/live/WQan-xlawWc?si=agxi6fRAmZyEzkWj)

### Practical Class
* Goal: Architecting a simple UI with Jetpack Compose
* Challenge: Add to the CrowdTally application the ability to configure the maximum capacity of the venue. The application should display the current count and the maximum capacity of the 
venue's occupation. The application should support screen rotation, preserving the current count and the maximum capacity.
* [Challenge solution: CrowdTally v0.2 live coding session video](https://www.youtube.com/live/L3HJkK8HuvE?si=_EFaXKjNacVoaYAX)

### Bonus Content
* [Lecture video (in Portuguese): Latency numbers every programmer should know](https://www.youtube.com/live/aQxFRASkybI?si=s2hcP4KXamCYtoz9)
* [Latency Numbers Every Programmer Should Know](https://gist.github.com/jboner/2841832)
* [Latency Numbers Every Programmer Should Know (interactive)](https://colin-scott.github.io/personal_website/research/interactive_latency.html)

## Week 4 - 30/09/2024 to 06/10/2024
### Subject: Navigating between screens
#### Topic breakdown:
* UX: navigating between activities
  * User task and back stack
  * Intents: explicit and implicit
* The Activity component: continued
  * Basic lifecycle, revisited: behavior on navigation
  
### For reference:
* [Tasks and Back Stack](https://developer.android.com/guide/components/activities/tasks-and-back-stack)
* [Intents and intent filters](https://developer.android.com/guide/components/intents-filters)
  * [Sending the user to another app](https://developer.android.com/training/basics/intents/sending)
  * [Common Intents](https://developer.android.com/guide/components/intents-common)

#### Video lecture (in Portuguese):
* [Android @ ISEL - 2024 - (04) Navegação entre ecrãs](https://www.youtube.com/live/kCq6f9dFX90?si=Z_-KqLYsyoi-sC2G)

### Practical Class
* Main Goal: Complete last week's challenge
* Secondary Goal: Continue to exercise architecting a UI with Jetpack Compose
* Challenge 1: Implement the About screen for the course's project
* Challenge 2: Implement the the StopWatch application. The application should have a screen that displays a timer with a start/stop button and a reset button. 
The timer should be updated every second and its state should be preserved on screen rotation.

### Bonus Content
* Mutability: Necessity or Habit? [(Talk at Kotlin Lisbon Meetup)](https://www.meetup.com/kotlin-lisboa/events/301917980/?eventOrigin=group_upcoming_events)
  * [Watch it here](https://www.youtube.com/live/-sJhepOzSCc?si=OE6c0JwbRRxpnikJ)

## Week 5 - 07/10/2024 to 13/10/2024
### Subject: Beyound the UI - ViewModel
#### Topic breakdown:
* Android's concurrency model, revisited
  * Execution on event handlers
  * `rememberCoroutineScope`
* The `ViewModel` framework component
  * Purpose and aplicability
  * Lifecycle and relation with the Activity component
    * `ViewModelStore` and `ViewModelStoreOwner`
  * The `ViewModel` as the execution host: `viewModelScope`
* Design considerations:
  * The ViewModel as:
    * the facade to the domain
    * the container of aplication state relevant to the screen
    * the host of the use case execution
  * The view model as the actual host of the screen's state machine

### For reference:
* [ViewModel Overview](https://developer.android.com/topic/libraries/architecture/viewmodel)
  * [View Model lifecycle](https://developer.android.com/topic/libraries/architecture/viewmodel#lifecycle)
  * [Use Kotlin Coroutines with lifecycle-aware components](https://developer.android.com/topic/libraries/architecture/coroutines#viewmodelscope)
* [ViewModelStore](https://developer.android.com/reference/androidx/lifecycle/ViewModelStore)
* [ViewModelStoreOwner](https://developer.android.com/reference/androidx/lifecycle/ViewModelStoreOwner)

### Practical Class
* Goal: Work on the course's project. 
* Recomended approach: You may already implement the aplication's screens and navigation between them. You may also start working on identifying wich screen's will require a view model and start drafting the required services's interfaces. These services may, for the time being, be implemented as fakes. Completing theses tasks will give you a significant head start on the project's development.

#### Video lecture (in Portuguese):
* [Android @ ISEL - 2024 - (05-A) ViewModel](https://youtube.com/live/AsTpSVg5nMU?feature=share)
* [Android @ ISEL - 2024 - (05-B) Sessão de codificação ao vivo](https://www.youtube.com/live/xEafTGgGN5Q?si=3UlJJhBDnMTUyl19)

## Week 6 - 14/10/2024 to 20/10/2024
### Subject: Beyond the UI - Manual Dependency Injection
#### Topic breakdown:
* ViewModel, revisited
  * Instantiating using a custom factory, to enable parametric construction
  * Automated testing of the ViewModel
* Application
  * Motivation and lifecycle
  * Usage foe dependency resolution (as a Service Locator)

### For reference:
* [Application](https://developer.android.com/reference/android/app/Application)
* [Manual dependency injection](https://developer.android.com/training/dependency-injection/manual)
* [Testing Coroutines on Android](https://developer.android.com/kotlin/coroutines/test)

### Practical Class
* Goal: Work on the course's project. 
* Recomended approach: You may already implement the aplication's screens and navigation between them. You may also start working on identifying wich screen's will require a view model and start drafting the required services's interfaces. These services may, for the time being, be implemented as fakes. Completing theses tasks will give you a significant head start on the project's development.

#### Video lecture (in Portuguese):
* [Android @ ISEL - 2024 - (06-A) Injeccção manual de dependências](https://www.youtube.com/live/GcJ3KwN_sOE?si=dyrVLyHeshuABZhr)
* [Android @ ISEL - 2024 - (06-B) Sessão de codificação ao vivo](https://www.youtube.com/live/8KjdDZxreYw?si=PlDMx6K-ZGUO9Lj_)

### Bonus Content
* [Lecture video (in Portuguese): Revisiting Kotlin's concurrency model](https://www.youtube.com/live/XRq5QUf5QFE?si=VzEl6V_oHxutyu80)

## Week 7 - 21/10/2024 to 27/10/2024
### Subject: Comunication with HTTP APIs
#### Topic breakdown:
* The Android application as a HTTP client
  * Motivation and consequences of distribution
  * Required permissions
* HTTP communication with Ktor client
  * Programming model
  * Making assinchrounous requests
* JSON serialization with Kotlinx Serialization

### For reference:
* [Connecting to the network](https://developer.android.com/training/basics/network-ops/connecting)
* [Ktor Client](https://ktor.io/docs/client-create-multiplatform-application.html#ktor-dependencies)
  * [Ktor engines](https://ktor.io/docs/client-engines.html)
  * [Content negotiation](https://ktor.io/docs/client-serialization.html)
  * [Kotlin Serialization](https://kotlinlang.org/docs/serialization.html)

### Practical Class
* Goal: Work on the course's project.
* Recomended approach: The students that are working on the joint project with DAW may start the actual implementation of the project's services. 

#### Video lecture (in Portuguese):
* [Android @ ISEL - 2024 - (07) Comunicação com APIs HTTP](https://www.youtube.com/live/nj--yWhmk64?si=E4TuYve-EaBmPF2B)

## Week 8 - 28/10/2024 to 03/11/2024
### Subject: Persistent storage with Firestore
#### Topic breakdown:
* General characterization
  * Data model (i.e. Document DB)
  * Documents, collections and references
  * Supported data types
* Description of the Android SDK API
  * Adding, updating and deleting data
  * Reading data: queries and observable queries

### For reference:
* Prepare your environment for Firebase
  * [Add Firebase to your Android project](https://firebase.google.com/docs/android/setup)
  * [Install, configure and integrate Local Emulator Suite](https://firebase.google.com/docs/emulator-suite/install_and_configure)
  * [Connect your app and start prototyping | Firebase Local Emulator Suite](https://firebase.google.com/docs/emulator-suite/connect_and_prototype?database=Firestore)
* Firestore API
  * [Getting started with Cloud Firestore](https://firebase.google.com/docs/firestore/quickstart)
  * [Cloud Firestore Data model | Firebase](https://firebase.google.com/docs/firestore/data-model) 
  * [Supported data types | Firestore | Firebase](https://firebase.google.com/docs/firestore/manage-data/data-types)
  * [Add data to Cloud Firestore | Firebase](https://firebase.google.com/docs/firestore/manage-data/add-data)
  * [Delete data from Cloud Firestore | Firebase](https://firebase.google.com/docs/firestore/manage-data/delete-data)
  * [Get data with Cloud Firestore | Firebase](https://firebase.google.com/docs/firestore/query-data/get-data)
  * [Get real time updates with Cloud Firestore | Firebase](https://firebase.google.com/docs/firestore/query-data/listen)
  * [Firestore API Docs](https://firebase.google.com/docs/reference/kotlin/com/google/firebase/firestore/package-summary)
* Kotlin concurrency model
  * [Asynchronous Flow | Kotlin Documentation](https://kotlinlang.org/docs/flow.html) 
  * [Kotlin flows on Android](https://developer.android.com/kotlin/flow#callback)

### Practical Class
* Goal: Work on the course's project.
* Recomended approach: The students that are working on the [CheR application](https://github.com/isel-leic-pdm/2425i/blob/main/assignments/PDM-2425-1_Option_B.pdf) may start the actual implementation of the project's services using Firestore.

#### Video lecture (in Portuguese):
* [Android @ ISEL - 2024 - (08) Firestore](https://www.youtube.com/live/Ja00LqGUy50?si=yE8yFjN0XIGda-P4)
* [Android @ ISEL - 2024 - (09) Kotlin flows](https://youtube.com/live/tPTkTNAsE8U?feature=share)

## Week 9 - 04/11/2024 to 10/11/2024
### Subject: Intermediary project presentations

## Week 10 - 11/11/2024 to 17/11/2024
### Subject: Local storage with Preferences DataStore
#### Topic breakdown:
* General characterization
  * Data model (i.e. key-value store)
  * Supported data types
* Kotlin concurrency model
  * Flows, revisited 

### For reference:
  * [DataStore Overview](https://developer.android.com/topic/libraries/architecture/datastore)
  * [TDD Is The Best Design Technique](https://www.youtube.com/watch?v=ln4WnxX-wrw)

### Practical Class
* Goal: Work on the course's project. 
* Recomended approach: Replace the fake user preferences with the actual implementation using Preferences DataStore.

#### Video lecture (in Portuguese):
* [Android @ ISEL - 2024 - (10) Construindo a aplicação "Guess a Doodle"](https://youtube.com/live/rT7ZEH9nifQ?feature=share)
* [Android @ ISEL - 2024 - (11) Construindo a aplicação "Guess a Doodle"](https://www.youtube.com/live/_mn1qdA1Mi0?si=4pSfMGc8oygF7K7r)
* [Android @ ISEL - 2024 - (12) Preferences DataStore](https://youtube.com/live/MfGLWty6VIo?feature=share)

## Week 11 - 18/11/2024 to 24/11/2024 _(preview)_
### Subject: Android application architecture
#### Topic breakdown:
* The Android application as a reactive system
  * The reactive paradigm
  * Kotlin flows: cold and hot flows
  * Designing for unidirectional data flows
* Testing Kotlin flows

### Practical Class
* Goal: Work on the course's project. 
* Suggested approach: Change the application's architecture to use a reactive approach.

### For reference:
* [Reactive programming](https://en.wikipedia.org/wiki/Reactive_programming)
* [Guide to app architecture](https://developer.android.com/topic/architecture)
* [Unidirectional data flow](https://developer.android.com/topic/architecture/ui-layer#udf)
* [Testing Kotlin coroutines](https://developer.android.com/kotlin/coroutines/test)
* [Testing Kotlin flows](https://developer.android.com/kotlin/flow/test)

#### Video lecture (in Portuguese):
* Android @ ISEL - 2024 - (13) Construindo a aplicação "Guess a Doodle" _(coming soon)_
* Android @ ISEL - 2024 - (14) Construindo a aplicação "Guess a Doodle" _(coming soon)_
* Android @ ISEL - 2024 - (15) Construindo a aplicação "Guess a Doodle" _(coming soon)_
