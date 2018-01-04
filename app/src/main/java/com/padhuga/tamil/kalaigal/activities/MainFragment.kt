package com.padhuga.tamil.kalaigal.activities

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.ListFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import com.padhuga.tamil.kalaigal.R
import com.padhuga.tamil.kalaigal.utilities.Constants

class MainFragment : ListFragment() {
    private var parentPosition: Int = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                     savedInstanceState: Bundle?): View {
        val rootView = inflater.inflate(R.layout.fragment_main, container, false)
        parentPosition = arguments!!.getInt(Constants.ARG_SECTION_POSITION)
        initializeList()
        return rootView
    }

    private fun initializeList() {
        val listData = ArrayList<String>()
        (0 until (activity as BaseActivity).parentModel.data.type[parentPosition].type.size).mapTo(listData) { (activity as BaseActivity).parentModel.data.type[parentPosition].type[it].title }
        listAdapter = ArrayAdapter(context, android.R.layout.simple_list_item_1, listData)
    }

    override fun onListItemClick(l: ListView, v: View, position: Int, id: Long) {
        val intent = Intent(activity, DetailsActivity::class.java)
        intent.putExtra(Constants.ARG_SECTION_POSITION, parentPosition)
        intent.putExtra(Constants.ARG_CHILD_POSITION, position)
        startActivity(intent)
    }

    companion object {
        fun newInstance(position: Int): MainFragment {
            val mainFragment = MainFragment()
            val args = Bundle()
            args.putInt(Constants.ARG_SECTION_POSITION, position)
            mainFragment.arguments = args
            return mainFragment
        }
    }
}

