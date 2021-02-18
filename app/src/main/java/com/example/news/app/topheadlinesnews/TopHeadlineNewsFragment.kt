package com.example.news.app.topheadlinesnews


import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.news.R
import kotlinx.android.synthetic.main.fragment_topheadlines_news.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class TopHeadlineNewsFragment : Fragment(R.layout.fragment_topheadlines_news) {
    private val topHeadlineNewsViewModel: TopHeadlineNewsViewModel by viewModel()

    private val headlineAdapter =
        HeadlineNewsAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        list.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = headlineAdapter
        }

        lifecycleScope.launch {
            topHeadlineNewsViewModel.movies.collectLatest {
                headlineAdapter.submitData(it)
            }
        }
    }
}