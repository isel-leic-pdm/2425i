
## Week 1 - 09/09/2023 to 15/09/2023
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
* Lecture video (in Portuguese): Revisiting Classes, Values and Objects


## Week 2 - 16/09/2023 to 22/09/2023 (preview)
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
  * Dice Roller application (v0.1). The dice roll is made by pressing "The Roll it" button and the result is displayed on the screen as a dice face. The dice face is a drawable resource, which is immediately displayed on the screen when the button is pressed.
  
* For reference:
  * [Thinking in Compose](https://developer.android.com/develop/ui/compose/mental-model)
  * [Compose layout basics - Jetpack](https://developer.android.com/jetpack/compose/layouts/basics)
  * [State and Jetpack Compose](https://developer.android.com/jetpack/compose/state)
  * [Test your Compose layout](https://developer.android.com/develop/ui/compose/testing)
  * [Material Design for Android](https://developer.android.com/develop/ui/views/theming/look-and-feel)

#### Video lecture (in Portuguese):
* [Android @ ISEL - 2024 - (02-A) Construindo a UI com Compose](https://youtu.be/c58d1Tb0jzU?si=14L1inlQw2e86OGU)

### Practical Class
* Goal: Create a simple UI with Jetpack Compose
* Challenge: Implement the CrowdTally application, used for counting the attendance in a venue with limited capacity. The application is comprised of a screen with the current count and buttons to decrease and increase that count.
* Challenge solution: CrowdTally live coding session video _(coming soon)_

### Bonus Content
* Lecture video (in Portuguese): Revisiting Polymorphism and Inheritance _(coming soon)_

## Week 3 - 23/09/2024 to 29/09/2024 _(preview)_
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
* [Test your Compose layout](https://developer.android.com/develop/ui/compose/testing)


#### Video lecture (in Portuguese):
* [Android @ ISEL - 2024 - (03-A) Estado de apresentação com Compose](https://www.youtube.com/live/hyO4i8zM4mE?si=wHxervtnenn6j-FM) 
* Android @ ISEL - 2024 - (03-B) Estado de apresentação com Compose _(coming soon)_

### Practical Class
* Goal: Architecting a simple UI with Jetpack Compose
* Challenge: Add to the CrowdTally application the ability to configure the maximum capacity of the venue. The application should display the current count and the maximum capacity of the 
venue's occupation. The application should support screen rotation, preserving the current count and the maximum capacity.
* Challenge 2: Change your solution in order to add unit tests 
* Challenge 3: Change your solution in order to add Instrumentation tests to your solution

### Bonus Content
* Lecture video (in Portuguese): Latency numbers every programmer should know _(coming soon)_

