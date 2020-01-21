package com.gdgistanbul.attendence.ui.login

import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import com.gdgistanbul.api.Secrets
import com.gdgistanbul.attendence.R
import com.gdgistanbul.attendence.extension.navigate
import com.gdgistanbul.attendence.extension.observeEvent
import com.gdgistanbul.viewmodel.MeetupViewModel
import kotlinx.android.synthetic.main.fragment_login_web_view.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginWebViewFragment : Fragment() {
    private val meetupViewModel by viewModel<MeetupViewModel>()

    private val authUrl = Uri.Builder()
        .scheme("https")
        .authority("secure.meetup.com")
        .appendPath("oauth2")
        .appendPath("authorize")
        .appendQueryParameter("client_id", Secrets.MEETUP_KEY)
        .appendQueryParameter("response_type", "code")
        .appendQueryParameter("redirect_uri", Secrets.MEETUP_REDIRECT_URL)
        .build()
        .toString()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login_web_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeEvent(meetupViewModel.loginLiveData) { navigateToEventList() }

        with(webViewAuthorize) {
            with(settings) {
                javaScriptEnabled = true
                loadWithOverviewMode = true
                useWideViewPort = true
            }

            loadUrl(authUrl)

            webViewClient = object : WebViewClient() {
                override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                    if (url?.contains(Secrets.MEETUP_REDIRECT_URL) == true) {
                        webViewAuthorize.stopLoading()
                        authenticate(url)
                    } else {
                        super.onPageStarted(view, url, favicon)
                    }
                }
            }
        }
    }

    private fun authenticate(url: String) {
        url.toUri().getQueryParameter("code")?.let { code ->
            meetupViewModel.authenticate(code)
        }
    }

    private fun navigateToEventList() {
        navigate(LoginWebViewFragmentDirections.actionLoginWebViewFragmentToEventListFragment())
    }
}