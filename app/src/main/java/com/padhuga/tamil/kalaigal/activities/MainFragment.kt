package com.padhuga.tamil.kalaigal.activities

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.ListFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import com.padhuga.tamil.kalaigal.utilities.Constants
import com.padhuga.tamil.kalaigal.R

class MainFragment : ListFragment() {
    private var parentPosition: Int = 0

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                     savedInstanceState: Bundle?): View? {
        val rootView = inflater?.inflate(R.layout.fragment_main, container, false)
        parentPosition = getArguments().getInt(Constants.ARG_SECTION_POSITION)
        initializeList(rootView)
        return rootView
    }

    private fun initializeList(rootView: View?) {
        val listData = ArrayList<String>()
        for (i in 0 until (getActivity() as BaseActivity).parentModel.data.type[parentPosition].type.size) {
            listData.add((getActivity() as BaseActivity).parentModel.data.type[parentPosition].type[i].title)
        }

        val listViewAdapter = ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, listData)
        setListAdapter(listViewAdapter)
    }

    override fun onListItemClick(l: ListView, v: View, position: Int, id: Long) {
        setupUI(position)
    }

    private fun setupUI(childPosition: Int) {
        val intent = Intent(getActivity(), DetailsActivity::class.java)
        intent.putExtra(Constants.ARG_SECTION_POSITION, parentPosition)
        intent.putExtra(Constants.ARG_CHILD_POSITION, childPosition)
        startActivity(intent)
    }

    companion object {
        fun newInstance(position: Int): MainFragment {
            val mainFragment = MainFragment()
            val args = Bundle()
            args.putInt(Constants.ARG_SECTION_POSITION, position)
            mainFragment.setArguments(args)
            return mainFragment
        }
    }
}

