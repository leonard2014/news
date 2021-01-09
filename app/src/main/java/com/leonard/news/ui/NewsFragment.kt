package com.leonard.news.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.leonard.news.R

class NewsFragment : Fragment() {

    private val args: NewsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.news_fragment, container, false)

        setupWebview(view)

        return view
    }

    private fun setupWebview(rootView: View) {
        val webView = rootView.findViewById(R.id.webview) as WebView

        // Enable Javascript
        val webSettings: WebSettings = webView.getSettings()
        webSettings.javaScriptEnabled = true

        // Force links and redirects to open in the WebView instead of in a browser
        webView.webViewClient = WebViewClient()

        webView.loadUrl(args.url)
    }

}