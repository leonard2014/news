package com.leonard.news.ui

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.leonard.news.R
import com.leonard.news.databinding.NewsListFragmentBinding
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class NewsListFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: NewsListViewModelFactory

    private lateinit var viewModel: NewsListViewModel

    private val adapter by lazy { NewsListAdapter(viewModel::onNewsItemClicked) }

    private var _binding: NewsListFragmentBinding? = null
    private val binding get() = _binding!!

    private val navController
        get() = _binding?.root?.findNavController()

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)

        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this, viewModelFactory).get(NewsListViewModel::class.java)

        lifecycle.addObserver(viewModel)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = NewsListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding?.newsList?.adapter = this.adapter

        viewModel.newsListLiveData.observe(viewLifecycleOwner, Observer { adapter.newsList = it })

        viewModel.loadingLiveData.observe(viewLifecycleOwner, Observer { _binding?.progressBar?.isVisible = it })

        viewModel.showErrorLiveData.observe(viewLifecycleOwner, Observer { showErrorDialog() })

        viewModel.showNewsLiveData.observe(viewLifecycleOwner, Observer(this::showNews))
    }

    private fun showNews(url: String) {
        val action = NewsListFragmentDirections.actionNewsListFragmentToNewsFragment(url)
        navController?.navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showErrorDialog() {
        AlertDialog.Builder(requireContext())
            .setTitle(R.string.error_title)
            .setMessage(R.string.error_message)
            .setPositiveButton(R.string.ok) { _, _ -> }
            .show()
    }

}