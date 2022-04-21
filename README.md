[![Maintainability](https://api.codeclimate.com/v1/badges/0178f2031dec54c86ff9/maintainability)](https://codeclimate.com/repos/5cd1d8c8af2ce517db016a12/maintainability)
[![License](https://img.shields.io/github/license/rootstrap/ios-base.svg)](https://opensource.org/licenses/MIT)

# Android Base

**Android Base** is a boilerplate project created by Rootstrap for new projects using Kotlin 1.3.61. The main objective is helping any new projects jump start into feature development by providing a handful of functionalities.

## Prerequisites
- Android SDK
- Android Studio
- Firebase google-services.json file.
- Change your release key information on the build.gradle:
```
signingConfigs {
    releaseConfig {
        keyAlias setAlias
        keyPassword setPassword
        storeFile file(setStoreFile)
        storePassword setStorePassword
    }
}
```
- Build the project with Android Studio.   

## Installation
1. Clone

2. Build with Android Studio

To manage user and session after sign in/up we store that information in Preferences. The parameters that we save are due to the usage of Device Token Auth for authentication on the server side.

Please Check
```
ResponseInterceptor.kt
AuthenticationInterceptor.kt
```
to handle the server side authentication, in case you need to modify them:

## Usage
- You can use this open source project as a template of your new Android projects.

## Key File encryption

Build signing requires a developer-owned keystore. Location and credentials for it are specified in `gradle.properties`. Likewise submission to Google Play requires a Developer API key in .json format (`google-api.json`).
It is recommended that these files remains outside the source repo

We suggest using [git secret](https://git-secret.io/) as a simple and secure solution for keeping these sensitive files in the repo. See [Config](./secure/Readme.md) for detailed instructions.


## Build and Release with Fastlane

We provide configuration files for automating build, test and submission of the application using [Fastlane](https://docs.fastlane.tools/)

### Requirements

* Ensure JDK 1.8 is installed
* Ensure proper version of Android SDK command line tools is installed
* Install _fastlane_ using
```
[sudo] gem install fastlane -NV
```
or alternatively using `brew cask install fastlane`

### Usage
Lanes for each deployment target example are provided with some basic behavior:
- Each target has two options: `debug_x` and `deploy_x`.
- Each option will:
  - Increment the build number.
  - Run `gradlew clean`
  - Run `gradlew androidDependencies`
  - Build the app (`gradle assemble`) for the target flavor.
- The `deploy` lanes will additionaly submit the APK to the corresponding track in the Play Store.

Check `fastlane/Appfile` and `fastlane/Fastfile` for more information.

## CI/CD configuration with Bitrise (updated on Dec 12th 2021)

We are going to start using a tool called Bitrise to configure the CI/CD pipelines for mobiles apps.

--> For Android apps you can find how to do it in this link: https://www.notion.so/rootstrap/Android-CI-CD-26d4abd4f2454224be8f617110147366

## Continuous Integration with GitHub Actions (DEPRECATED)

We provide an example workflow [cicd.yml](.github/workflows/cicd.yml) including two jobs for running under [GitHub Actions](https://docs.github.com/en/actions), which can be modified according to the specifics of each project:

* `ci`
    * runs upon every push and PR
    * installs Fastlane and runs `debug_dev` lane
* `release`
    * runs upon every push to `develop` or `master`
    * downloads keystore and Google api key from S3 (credentials need to be present in repo Secrets)
    * installs Fastlane and runs `deploy_*` lane depending on branch (`Dev` if in `develop`, `Stsaging` if in `master`) - This could be easily modified to release `Prod` instead 

## Analytics
- Add analytics manager:
    1. Firebase
    2. MixPanel[Optional]

**How use:**
In the Application class -> onCreate
```
Analytics.addProvider(GoogleAnalytics(applicationContext))
Analytics.addProvider(MixPanelAnalytics(applicationContext))
```
or an array of providers
`Analytics.addProviders(arrayOfProviders)`

then use:
`Analytics.track(PageEvents.visit(VISIT_MAIN))`
or for events
`Analytics.track(UserEvents.login())`
in order to track the login event.

- For firebase replace the file: google-services.json with the once for your App and follow the Firebase instructions.
- For MixPanel, you have to replace the API key: 
`<string name="mixpanel_api_key">mixpanel_api_key</string>`


## Utility extensions
We have a bunch of pre-made extensions usually used on every project to accelerate feature development.
you can access them in the util.extensions package. They include but are not limited to :

- `Fragment.collectOnLifeCycle(...)` extension to reduce the boiler plate code and indentation when
  collecting flows from a fragment.
- `ProgressBar.progressTo(...)` extension to animate progress updates in one line with ease 
  and with good support for older android versions.
- `TextView.setClickableKeyword(...)` extension to add clickable functions to words or prhases inside a 
  Textview, allowing to also change their color, font (for example, to bold the keyword), and underline
- `TextView.setColoredKeyword(...) ` extension to change the color of a word or prhase in a Textview.


## Code Quality Standards
In order to meet the required code quality standards, this project uses [Ktlint](https://github.com/pinterest/ktlint) and [Detekt](https://github.com/arturbosch/detekt)

## Contributing
Bug reports (please use Issues) and pull requests are welcome on GitHub at [android-base](https://github.com/rootstrap/android-base). This project is intended to be a safe, welcoming space for collaboration, and contributors are expected to adhere to the [Contributor Covenant](http://contributor-covenant.org) code of conduct.

## License
The library is available as open source under the terms of the [MIT License](https://opensource.org/licenses/MIT).

NOTE: Remove the free LICENSE file for private projects or replace it with the corresponding license.

## Credits
**Android Base** is maintained by [Rootstrap](http://www.rootstrap.com) with the help of our [contributors](https://github.com/rootstrap/android-base/contributors).

[<img src="https://s3-us-west-1.amazonaws.com/rootstrap.com/img/rs.png" width="100"/>](http://www.rootstrap.com)

