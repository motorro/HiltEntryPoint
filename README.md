# Hilt custom entry point in Activity

This is a test project to figure out why Hilt is not working with custom entry point in `ActivityRetainedComponent` while it works with `ActivityComponent`.

[Issue in Dagger repository](https://github.com/google/dagger/issues/4610)

The following [code](app/src/main/kotlin/com/motorro/hiltentrypoint/MainActivity.kt) works perfectly fine with `ActivityComponent`:

```kotlin
@EntryPoint
@InstallIn(ActivityComponent::class)
interface ActivityEntryPoint {
    fun dep(): Dep
}

// Later when called from Activity:
val entryPoint = EntryPoints.get(context, ActivityEntryPoint::class.java)
```

However, when I change `@InstallIn` from `ActivityComponent` to `ActivityRetainedComponent` it fails with the following error:

```
Caused by: java.lang.ClassCastException: Cannot cast com.motorro.hiltentrypoint.DaggerApp_HiltComponents_SingletonC$ActivityCImpl to com.motorro.hiltentrypoint.ActivityEntryPoint
    at java.lang.Class.cast(Class.java:4193)
    at dagger.hilt.EntryPoints.get(EntryPoints.java:57)
    at dagger.hilt.EntryPoints.get(EntryPoints.java:59)
    at com.motorro.hiltentrypoint.ActivityHelper.<init>(MainActivity.kt:41)
    at com.motorro.hiltentrypoint.MainActivity.onCreate(MainActivity.kt:29)
    at android.app.Activity.performCreate(Activity.java:8595)
    at android.app.Activity.performCreate(Activity.java:8573)
    at android.app.Instrumentation.callActivityOnCreate(Instrumentation.java:1456)
    at android.app.ActivityThread.performLaunchActivity(ActivityThread.java:3764)
```
