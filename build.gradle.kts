buildscript {
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.20")
    }
}
// Top-level build file where you can add configuration options common to all sub-projects/modules.
@kotlin.Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id("com.android.application") version("7.3.1") apply false
    id("com.android.library") version("7.3.1") apply false
    id("org.jetbrains.kotlin.android") version("1.7.20") apply false
}
tasks.register("clean",Delete::class){
    delete(rootProject.buildDir)
}