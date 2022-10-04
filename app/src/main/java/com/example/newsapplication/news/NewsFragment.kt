package com.example.newsapplication.news


import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.newsapplication.databinding.FragmentNewsBinding
import com.example.newsapplication.entities.News
import com.example.newsapplication.entities.PostAdapter
import com.example.newsapplication.entities.PostItemClick
import com.example.newsapplication.utils.*
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class NewsFragment : Fragment() ,PostItemClick {
    private lateinit var binding: FragmentNewsBinding
    private val viewModel by viewModels<NewsViewModel>()
    private lateinit var adapter: PostAdapter
    private var newsList = mutableListOf<News>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewsBinding.inflate(inflater)
        setupRecyclerView()
        setupObservers()
        binding.swipeRefresh.setOnRefreshListener {
            viewModel.getNews(
                "eg",
                "63b1f94dad044add871d1e319c630265"
            )
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
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
        }
    }

    private fun setupRecyclerView() {
        adapter = PostAdapter( this,newsList)
        binding.itemsRecyclerview.adapter = adapter
    }
    private fun setupObservers() {
        viewModel.data.observe(viewLifecycleOwner, Observer { result ->
            binding.swipeRefresh.isRefreshing = result.status == Status.LOADING
            when (result.status) {
                Status.SUCCESS -> {
                    newsList.addAll(result.data?.articles!!.toMutableList())
                    if (binding.searchResultsET.visibility == View.VISIBLE)
                        filter(binding.searchResultsET.text.toString())
                    else
                        adapter.setItem(newsList)
                        binding.swipeRefresh.isRefreshing = false
                }
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
        adapter.setItem(filteredList.toMutableList())
    }

    override fun onClick(item: News) {
        val detailsFragment = item.let {
            NewsFragmentDirections.actionNewsListFragmentToDetailsFragment(it)
        }
        findNavController().navigate(detailsFragment)
    }
}