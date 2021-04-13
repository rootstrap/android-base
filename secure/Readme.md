# Secret configuration files

Any sensitive files should be placed in this directory. This primarily includes:

* `google-api.json`: Google Play Developer API key - See [Developer API docs](https://developers.google.com/android-publisher/getting_started)

* `key.keystore`: container for cryptographic keys - see [Android keystore system](https://developer.android.com/training/articles/keystore)

* `gradle.properties`: Gradle properties file including keystore location, user and password


**All the plaintext files here are placed in .gitignore**

## Securing config files

For running the build through a CI pipeline we need to make sure all sensitive files are available to all build agents. Managing these files separately brings issues to ensure they are: a- version-controlled in a consistent manner with the code, and b- available to every build agent at build time.

We suggest using [git secret](https://git-secret.io/) as a simple and secure solution for keeping these sensitive files in the repo. 
This needs to be installed on any machine that requires access to the sourcecode and config (developer machines and CI agents).


## First time setup

* Generate gpg key -provide Real Name and Email address (to be used as USER-ID)
```
gpg --gen-key
```
* (Alternatively) Import existing owner public key
```
gpg --import ci-public-key.gpg
```
* Install git-secret
```
brew install git-secret
```
* Initialize git-secret on the project root folder (this createds `.gitsecret` folder)
```
git secret init
```
* Add access for USER-ID and any other usernames that will require access
```
git secret tell {dev-user@email}
git secret tell {ci-user@email}
```
* Add sensitive files to secret vault
```
git secret add secure/*
git secret add gradle.properties
```
* Encrypt
```
git secret hide
```
* Commit and push files (this includes `.gitsecret` folder)


## Accesing the files

* Install git secret
* Ensure private gpg key matching public key for the user ID that was granted access is available
* Check source code
* Decrypt
```
git secret reveal
```

### from CI server

* Store private key into and make available to project configuration as GPG_PRIVATE_KEY

* Include steps in Build pipeline
```
# Install git-secret
brew install git-secret
# Create private key file
echo $GPG_PRIVATE_KEY > ./private_key.gpg
# Import private key
gpg --import ./private_key.gpg
# Reveal secrets
git secret reveal

# Continue normal build pipeline
```