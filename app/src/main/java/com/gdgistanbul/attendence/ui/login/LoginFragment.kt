package com.gdgistanbul.attendence.ui.login

import android.content.Context
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.gdgistanbul.attendence.R
import com.gdgistanbul.attendence.util.AUTHORIZATION_URL
import com.gdgistanbul.attendence.util.CODE
import com.gdgistanbul.attendence.util.REDIRECT_URI
import com.gdgistanbul.viewmodel.MeetupViewModel
import kotlinx.android.synthetic.main.fragment_login.*
import org.koin.android.ext.android.inject

class LoginFragment : Fragment() {
    private val viewModel: MeetupViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getAccessToken()

        webViewAuthorize.settings.javaScriptEnabled = true
        webViewAuthorize.settings.loadWithOverviewMode = true
        webViewAuthorize.settings.useWideViewPort = true
        webViewAuthorize.loadUrl(AUTHORIZATION_URL)

        webViewAuthorize.webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                verification(url)
            }
        }
    }

    private fun getAccessToken() {
        viewModel.accessTokenLiveData.observe(this, Observer { login ->
            val sharedPref = activity?.getSharedPreferences("attendance", Context.MODE_PRIVATE)
            val editor = sharedPref?.edit()
            editor?.putString("accesstoken", login.accessToken)
            editor?.commit()

            findNavController().navigate(R.id.action_loginFragment_to_eventListFragment)
        })
    }

    fun verification(code: String?) {
        code?.let {
            if (it.contains(REDIRECT_URI) && it.contains(CODE)) {
                val codeIndex = code.indexOf(CODE)
                val code = code.substring(codeIndex + CODE.length, code.length)
                viewModel.getAccessToken(code)
            }
        }
    }
}