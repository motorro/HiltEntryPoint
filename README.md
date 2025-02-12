# Hilt custom entry point in Activity

This is a test project to figure out why Hilt is not working with custom entry point in `ActivityRetainedComponent` while it works with `ActivityComponent`.

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
Caused by: java.lang.IllegalStateException: Hilt Activity must be attached to an @HiltAndroidApp Application. Did you forget to specify your Application's class name in your manifest's <application />'s android:name attribute?
    at dagger.hilt.android.internal.managers.ActivityComponentManager.createComponent(ActivityComponentManager.java:88)
    at dagger.hilt.android.internal.managers.ActivityComponentManager.generatedComponent(ActivityComponentManager.java:68)
    at com.motorro.hiltentrypoint.Hilt_MainActivity.generatedComponent(Hilt_MainActivity.java:77)
    at com.motorro.hiltentrypoint.Hilt_MainActivity.inject(Hilt_MainActivity.java:99)
    at com.motorro.hiltentrypoint.Hilt_MainActivity$1.onContextAvailable(Hilt_MainActivity.java:46)
    at androidx.activity.contextaware.ContextAwareHelper.dispatchOnContextAvailable(ContextAwareHelper.kt:78)
    at androidx.activity.ComponentActivity.onCreate(ComponentActivity.kt:327)
    at androidx.fragment.app.FragmentActivity.onCreate(FragmentActivity.java:217)
    at com.motorro.hiltentrypoint.Hilt_MainActivity.onCreate(Hilt_MainActivity.java:63)
    at com.motorro.hiltentrypoint.MainActivity.onCreate(MainActivity.kt:19)
    at android.app.Activity.performCreate(Activity.java:8595)
    at android.app.Activity.performCreate(Activity.java:8573)
    at android.app.Instrumentation.callActivityOnCreate(Instrumentation.java:1456)
    at android.app.ActivityThread.performLaunchActivity(ActivityThread.java:3764)
```