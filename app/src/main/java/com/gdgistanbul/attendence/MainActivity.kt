package com.gdgistanbul.attendence

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gdgistanbul.attendence.ui.main.MainFragment
import com.gdgistanbul.viewmodel.MeetupViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: MeetupViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
        viewModel.refreshEvents()
    }

}
