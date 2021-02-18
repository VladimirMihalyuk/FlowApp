package com.example.news.app.newsdetails

import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.news.R
import kotlinx.android.synthetic.main.fragment_blank.*


class NewsFragment : Fragment(R.layout.fragment_blank) {
    private val args: NewsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vWeb.webChromeClient = WebChromeClient()
        vWeb.webViewClient = WebViewClient()
        vWeb.loadUrl(args.url)
        tv_title_toolbar.text = args.author
        close_btn.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}