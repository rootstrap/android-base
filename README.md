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

