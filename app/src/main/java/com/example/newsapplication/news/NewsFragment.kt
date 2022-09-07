package com.example.newsapplication.news

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.newsapplication.databinding.FragmentNewsBinding
import com.example.newsapplication.entities.News
import com.example.newsapplication.entities.PostAdapter
import com.example.newsapplication.entities.PostItemClick

import com.example.newsapplication.utils.*
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class NewsFragment : Fragment() {
    private lateinit var binding: FragmentNewsBinding
    private val viewModel by viewModels<NewsViewModel>()
    private var newsList = mutableListOf<News>()
    private val adapter by lazy {
        PostAdapter(PostItemClick { it ->
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentNewsBinding.inflate(inflater, container, false)
            .apply {
                binding = this
                lifecycleOwner = viewLifecycleOwner
                vm = viewModel
                searchIV.setOnClickListener {
                    hide(searchIV)
                    show(backImage, searchResultsET)
                }
                backImage.setOnClickListener {
                    show(searchIV)
                    hide(backImage, searchResultsET)
                    searchResultsET.clear()
                }
                searchResultsET.afterTextChanged {
                    filter(it)
                }
            }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        setupRecyclerView()
        binding.swipeRefresh.setOnRefreshListener {
            viewModel.getNews(
                "eg",
                "63b1f94dad044add871d1e319c630265"
            )
        }
    }

    private fun setupRecyclerView() {
        val layoutManager = GridLayoutManager(requireContext(), 1)
        binding.itemsRecyclerview.layoutManager = layoutManager
        binding.itemsRecyclerview.adapter = adapter


    }

    private fun setupObservers() {
        viewModel.data.observe(viewLifecycleOwner, Observer { result ->
            binding.swipeRefresh.isRefreshing = result.status == Status.LOADING
            when (result.status) {
                Status.SUCCESS -> {result.data?.let { adapter.submitList(result.data.articles) }
                if (binding.searchResultsET.visibility == View.VISIBLE)
                    filter(binding.searchResultsET.text.toString())
                else
                    binding.swipeRefresh.isRefreshing = false
                }
                Status.ERROR -> Toast.makeText(requireContext(), result.message, Toast.LENGTH_LONG)
                    .show()
                else -> {
                    binding.swipeRefresh.isRefreshing = false
                }
            }
        })
    }

    private fun filter(text: String) {
        val filteredList: ArrayList<News> = ArrayList()
        for (item in newsList) {
            if (item.title?.toLowerCase(Locale.ROOT)
                    ?.contains(text.toLowerCase(Locale.ROOT)) == true
            ) {
                filteredList.add(item)
            }
        }
        adapter.submitList(filteredList.toMutableList())
    }
}