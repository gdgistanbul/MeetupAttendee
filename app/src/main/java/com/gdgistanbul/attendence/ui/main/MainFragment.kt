package com.gdgistanbul.attendence.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.gdgistanbul.attendence.R
import com.gdgistanbul.viewmodel.MeetupViewModel
import kotlinx.android.synthetic.main.main_fragment.*
import org.koin.android.viewmodel.ext.android.sharedViewModel

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private val viewModel: MeetupViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.eventsLiveData.observe(this, Observer {
            message.text = "${it.size}"
        })
        viewModel.toastLiveData.observe(this, Observer {
            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
        })
    }

}
