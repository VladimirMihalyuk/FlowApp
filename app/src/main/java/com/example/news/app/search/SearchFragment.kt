package com.example.news.app.search

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.news.R
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.coroutines.flow.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import reactivecircus.flowbinding.android.widget.textChangeEvents

class SearchFragment : Fragment(R.layout.fragment_search) {
    private val searchViewModel: SearchViewModel by viewModel()

    private val searchAdapter = SearchAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        list.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = searchAdapter
        }

        searchViewModel.listOfNews.observe(viewLifecycleOwner) {
            searchAdapter.submitList(it)
        }

        editTextTextPersonName
            .textChangeEvents()
            .debounce(1500)
            .map { it.text.toString() }
            .filter { it.isNotEmpty() && it.isNotBlank() }
            .onEach { searchViewModel.searchByString(it) }
            .launchIn(lifecycleScope)

        editTextTextPersonName.setText(R.string.covid19)
    }
}