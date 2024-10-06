
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
* Android @ ISEL - 2024 - (05-A) ViewModel _(coming soon)_
* Android @ ISEL - 2024 - (05-B) Sessão de codificação ao vivo _(coming soon)_

### Bonus Content
* Lecture video (in Portuguese): Revisiting Kotlin's concurrency model _(coming soon)_

