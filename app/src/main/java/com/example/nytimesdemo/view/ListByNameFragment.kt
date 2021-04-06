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
import com.example.nytimesdemo.databinding.FragmentListByNameBinding
import com.example.nytimesdemo.models.BookDetailsItem
import com.example.nytimesdemo.models.ResultsItem
import com.example.nytimesdemo.models.UpdatedBy
import com.example.nytimesdemo.util.Status
import com.example.nytimesdemo.viewmodels.BestSellersViewModel
import com.example.nytimesdemo.viewmodels.ListByNameViewModel
import dagger.hilt.android.AndroidEntryPoint

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "listName"

/**
 * A simple [Fragment] subclass.
 * Use the [ListByNameFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class ListByNameFragment : Fragment() {
    private var listName: String? = null
    private val listByNameViewModel: ListByNameViewModel by viewModels()
    private lateinit var binding: FragmentListByNameBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            listName = it.getString("listName");

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate<FragmentListByNameBinding>(
            inflater,
            R.layout.fragment_list_by_name,
            container,
            false
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listByNameViewModel?.data(listName)
        binding.rvBestSellers.layoutManager = LinearLayoutManager(context)
        setUpObserver()
        binding.btnRetry.setOnClickListener {
            listByNameViewModel.fetchBestSellers()
        }
    }


    companion object {
        @JvmStatic
        fun newInstance(param1: String) =
            ListByNameFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                }
            }
    }

    private fun setUpObserver() {
        listByNameViewModel._bestSellers.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    var listResult : MutableList<BookDetailsItem> = arrayListOf()
                    for(item in it.data ?: ArrayList()){
                        listResult.addAll(item?.bookDetails?.filterNotNull() ?: arrayListOf())
                    }
                    binding.rvBestSellers.adapter = ListByNameAdapter(listResult)
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