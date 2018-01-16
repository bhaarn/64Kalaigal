package com.padhuga.tamil.kalaigal.activities

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.padhuga.tamil.kalaigal.R
import com.padhuga.tamil.kalaigal.utilities.Constants
import kotlinx.android.synthetic.main.fragment_search.view.*

class SearchFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_search, container, false)
        val query = arguments!!.getString(Constants.ARG_QUERY_TEXT)
        val queryResults = ArrayList<String>()
        val searchRetriever = (activity as BaseActivity).showSearchResults(query)
        for (titleIndex in searchRetriever.indices) {
            queryResults.add(titleIndex, searchRetriever[titleIndex].title)
        }
        val searchArrayAdapter = ArrayAdapter(context!!, android.R.layout.simple_list_item_1, queryResults)
        rootView.search_results_list.adapter = searchArrayAdapter
        rootView.search_results_list.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ -> setupUI(searchRetriever[position].position,  searchRetriever[position].childPosition) }
        return rootView
    }

    private fun setupUI(position: Int, childPosition: Int) {
        val detailsFragment = DetailsFragment()
        val args = Bundle()
        args.putInt(Constants.ARG_SECTION_POSITION, position)
        args.putInt(Constants.ARG_CHILD_POSITION, childPosition)
        detailsFragment.arguments = args
        val fragmentManager = fragmentManager
        val fragmentTransaction = fragmentManager!!.beginTransaction()
        fragmentTransaction.replace(android.R.id.content, detailsFragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }
}