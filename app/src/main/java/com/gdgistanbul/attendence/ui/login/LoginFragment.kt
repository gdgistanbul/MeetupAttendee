package com.gdgistanbul.attendence.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.gdgistanbul.attendence.R
import com.gdgistanbul.attendence.extension.clicks
import com.gdgistanbul.attendence.extension.navigate
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnLoginWithMeetup
            .clicks()
            .debounce(ALLOWED_BUTTON_CLICK_PERIOD_IN_MILLIS)
            .onEach { navigateToLoginWebView() }
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun navigateToLoginWebView() {
        navigate(LoginFragmentDirections.actionLoginFragmentToLoginWebViewFragment())
    }

    companion object {
        private const val ALLOWED_BUTTON_CLICK_PERIOD_IN_MILLIS = 400L
    }
}