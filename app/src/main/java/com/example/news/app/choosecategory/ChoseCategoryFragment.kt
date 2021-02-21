package com.example.news.app.choosecategory

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.news.R
import com.example.news.data.models.NewsCategory
import kotlinx.android.synthetic.main.fragment_chose_category.*
import kotlinx.coroutines.flow.collect
import org.koin.androidx.viewmodel.ext.android.viewModel

class ChoseCategoryFragment : Fragment(R.layout.fragment_chose_category) {
    private val choseCategoryViewModel: ChoseCategoryViewModel by viewModel()
    private val choseCategoryAdapter = ChoseCategoryAdapter()

    private val categoryMap: Map<Int, NewsCategory> by lazy {
        mapOf(
            business.id to NewsCategory.BUSINESS,
            entertainment.id to NewsCategory.ENTERTAINMENT,
            health.id to NewsCategory.HEALTH,
            science.id to NewsCategory.SCIENCE,
            sports.id to NewsCategory.SPORTS,
            technology.id to NewsCategory.TECHNOLOGY
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        list.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = choseCategoryAdapter
        }

        chipGroup.setOnCheckedChangeListener { _, checkedId ->
            choseCategoryViewModel.choseCategory(categoryMap[checkedId]!!)
        }

        choseCategoryViewModel.listOfNews.observe(viewLifecycleOwner) {
            choseCategoryAdapter.submitList(it)
            progressBar.isVisible = false
            list.isVisible = true

        }

        lifecycleScope.launchWhenStarted {
            choseCategoryViewModel.isLoading.collect {
                progressBar.isVisible = it
                list.isVisible = !it
            }
        }

        chipGroup.check(R.id.entertainment)
    }
}