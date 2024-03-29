# Cheese App

A cookbook-type app based on the 'Android Architecture Components Paging Sample'.

It showcases the following Architecture Components:

* [Paging](https://developer.android.com/topic/libraries/architecture/paging.html)
* [Room](https://developer.android.com/topic/libraries/architecture/room.html)
* [ViewModels](https://developer.android.com/reference/android/arch/lifecycle/ViewModel.html)
* [LiveData](https://developer.android.com/reference/android/arch/lifecycle/LiveData.html)

caveats: unnecessary complication by adding separators in the Adapter. also requires paging 3. anyhow: it is a reasonably simple example for paging.

### Features

displays a single screen with a list of text items representing cheeses. Items can be added to the list with the input at the top, and swiping items in the list removes them.

---

Environment

- Kotlin 1.8.10
- Android Studio Flamingo 2022.2.1
- Gradle Plugin 8.0.0

---

```
language: kotlin
repo: cheeseapp
status: archived
updated: 2023-05-02
```