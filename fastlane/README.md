# Android Fastlane configuration
============================

## Installation and requirements

* Ensure JDK 1.8 is installed

* Ensure proper version of Android SDK command line tools is installed

* Install _fastlane_ using
```
[sudo] gem install fastlane -NV
```
or alternatively using `brew cask install fastlane`


## General workflow

* Fastlane for Android basically executes Gradle commands for cleaning, installing Android dependencies and assembling the project into a .apk
* Application file is published to Google Play Store - keystore file needs to be present under `./app` and json API key file present in the root folder.


## Actions breakdown

Modify the Fastfile as appropiate for your project.

Execute with
```
fastlane lane_name
```

### debug_*
Builds and archive corresponding flavor for local use

### deploy_*
Builds corresponding flavor and pushes to Play Store

----
More information about fastlane can be found on [fastlane.tools](https://fastlane.tools).
The documentation of fastlane can be found on [docs.fastlane.tools](https://docs.fastlane.tools).
