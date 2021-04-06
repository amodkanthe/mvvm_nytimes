package com.example.nytimesdemo.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nytimesdemo.R
import com.example.nytimesdemo.databinding.FragmentBestSellersBinding
import com.example.nytimesdemo.models.ResultsItem
import com.example.nytimesdemo.models.UpdatedBy
import com.example.nytimesdemo.util.Status
import com.example.nytimesdemo.viewmodels.BestSellersViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList

@AndroidEntryPoint
class BestSellersFragment : Fragment() {

    private val bestSellerViewModel: BestSellersViewModel by viewModels()
    private lateinit var binding: FragmentBestSellersBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate<FragmentBestSellersBinding>(
            inflater,
            R.layout.fragment_best_sellers,
            container,
            false
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvBestSellers.layoutManager = LinearLayoutManager(context)
        setUpObserver()
        binding.btnRetry.setOnClickListener {
            getBestSeller()
        }

    }

    fun getBestSeller(){
        binding.llError.visibility = View.GONE
        bestSellerViewModel.fetchBestSellers()
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            BestSellersFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }

    private fun setUpObserver() {
        bestSellerViewModel._bestSellers.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    val list = (it.data?.sortedByDescending {  it?.newestPublishedDate})
                    val map = list?.groupBy { it?.updated }
                    var listResult : MutableList<Any> = arrayListOf()
                    if (map != null) {
                        for ((key, value) in map){
                            listResult.add(UpdatedBy("$key(${value.size})"))
                            listResult.addAll(value.filterNotNull() ?: arrayListOf())
                        }
                    }
                    binding.rvBestSellers.adapter = BestSellersAdapter(listResult,listener = {
                        val listName = (it as? ResultsItem)?.listNameEncoded ?: ""
                        val bundle = bundleOf("listName" to listName)
                        view?.findNavController()?.navigate(R.id.action_bestSellersFragment_to_listByNameFragment, bundle)
                    })
                    binding.pbBestSellers.visibility = View.GONE
                }
                Status.LOADING -> {
                    binding.llError.visibility = View.GONE
                    binding.pbBestSellers.visibility = View.VISIBLE
                }
                Status.ERROR -> {
                    binding.pbBestSellers.visibility = View.GONE
                    binding.llError.visibility = View.VISIBLE
                }
            }
        })
    }
}