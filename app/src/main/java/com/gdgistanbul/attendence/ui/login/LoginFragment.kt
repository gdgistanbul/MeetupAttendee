package com.gdgistanbul.attendence.ui.login

import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.gdgistanbul.api.Secrets
import com.gdgistanbul.attendence.R
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
        viewModel.loginLiveData.observe(this, Observer { login ->
            findNavController().navigate(R.id.action_loginFragment_to_eventListFragment)
        })

        webViewAuthorize.settings.javaScriptEnabled = true
        webViewAuthorize.settings.loadWithOverviewMode = true
        webViewAuthorize.settings.useWideViewPort = true
        webViewAuthorize.loadUrl(
            "https://secure.meetup.com/oauth2/authorize?" +
                    "client_id=${Secrets.MEETUP_KEY}&" +
                    "response_type=code&" +
                    "redirect_uri=${Secrets.MEETUP_REDIRECT_URL}"
        )

        webViewAuthorize.webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                url?.let {
                    if (url.contains(Secrets.MEETUP_REDIRECT_URL)) {
                        webViewAuthorize.stopLoading()
                        authenticate(url)
                    } else {
                        super.onPageStarted(view, url, favicon)
                    }
                }

            }
        }
    }


    fun authenticate(url: String) {
        Uri.parse(url).getQueryParameter("code")?.let { code ->
            viewModel.authenticate(code)
        }
    }
}