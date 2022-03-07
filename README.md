Cheese App
==========

Simple app from the 'Android Architecture Components Paging Sample'.

This sample showcases the following Architecture Components:

* [Paging](https://developer.android.com/topic/libraries/architecture/paging.html)
* [Room](https://developer.android.com/topic/libraries/architecture/room.html)
* [ViewModels](https://developer.android.com/reference/android/arch/lifecycle/ViewModel.html)
* [LiveData](https://developer.android.com/reference/android/arch/lifecycle/LiveData.html)

caveats: unnecessary complication by adding seperators. also requires paging 3. anyhow: reasonably simple example for paging.

### Features

This sample contains a single screen with a list of text items. Items can be added to the list with the input at the top, and swiping items in the list removes them.

----

Environment

- Kotlin 1.6.10
- Android Studio Bumblebee 2021.1.1
- Gradle Plugin 7.1.2

(previous note: to make Room compile on M1 macs you need to add `kapt "org.xerial:sqlite-jdbc:3.36.0"` before kapt for room. this is resolved in room 2.4)

----

updated: 2022-03-07

----

License
-------

Copyright 2017 The Android Open Source Project, Inc.

Licensed to the Apache Software Foundation (ASF) under one or more contributor
license agreements.  See the NOTICE file distributed with this work for
additional information regarding copyright ownership.  The ASF licenses this
file to you under the Apache License, Version 2.0 (the "License"); you may not
use this file except in compliance with the License.  You may obtain a copy of
the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
License for the specific language governing permissions and limitations under
the License.
