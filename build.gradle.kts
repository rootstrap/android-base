// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }

    dependencies {
        with(Dependencies.ClassPaths) {
            classpath(ANDROID_GRADLE_PLUGIN)
            classpath(KOTLIN_GRADLE_PLUGIN)
            classpath(GMS)
            classpath(FIREBASE_CRASHLYTICS_GRADLE)
        }
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven("https://jitpack.io")
    }
}

val deletePreviousGitHook by tasks.registering(Delete::class) {
    group = "utils"
    description = "Deleting previous githook"

    val preCommit = "${rootProject.rootDir}/.git/hooks/pre-commit"
    val prePush = "${rootProject.rootDir}/.git/hooks/pre-push"
    if (file(preCommit).exists() && file(prePush).exists()) {
        delete(preCommit, prePush)
    }
}

val installGitHook by tasks.registering(Copy::class) {
    group = "utils"
    description = "Adding githook to local working copy, this must be run manually"

    dependsOn(deletePreviousGitHook)
    from("${rootProject.rootDir}/pre-commit", "${rootProject.rootDir}/pre-push")
    into("${rootProject.rootDir}/.git/hooks")
    fileMode = 777
}

tasks.getByPath(":app:build").dependsOn(":project:installGitHook")

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
