package com.motorro.hiltentrypoint

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import dagger.hilt.EntryPoint
import dagger.hilt.EntryPoints
import dagger.hilt.InstallIn
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.components.ActivityComponent
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        ActivityHelper(this)
    }
}

@EntryPoint
@InstallIn(ActivityComponent::class)
// Crashes: @InstallIn(ActivityRetainedComponent::class)
interface ActivityEntryPoint {
    fun dep(): Dep
}

class ActivityHelper(context: Any) {
    init {
        val entryPoint = EntryPoints.get(context, ActivityEntryPoint::class.java)
        Log.w("EntryPoint", "NextNum: ${entryPoint.dep().getDep()}")
    }
}

class Dep @Inject constructor() {
    fun getDep(): Int = 10
}
