package com.padhuga.tamil.kalaigal.activities

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.padhuga.tamil.kalaigal.R
import com.padhuga.tamil.kalaigal.utilities.Constants
import kotlinx.android.synthetic.main.fragment_details.view.*


class DetailsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                     savedInstanceState: Bundle?): View? {
        val rootView = inflater?.inflate(
                R.layout.fragment_details, container, false) as ViewGroup
        setData(rootView)
        return rootView
    }

    private fun setData(rootView: ViewGroup) {
        val position = arguments.getInt(Constants.ARG_SECTION_POSITION)
        val childPosition = arguments.getInt(Constants.ARG_CHILD_POSITION)
        val parentModel = (activity as BaseActivity).parentModel.data.type[position].type[childPosition]

        rootView.parent_title?.text = parentModel.title
        rootView.parent_soothiram?.text = parentModel.soothiram
        rootView.parent_content?.text = parentModel.desc
        rootView.parent_example?.text = parentModel.example
    }

    companion object {
        fun newInstance(position: Int, childPosition: Int): DetailsFragment {
            val detailsFragment = DetailsFragment()
            val args = Bundle()
            args.putInt(Constants.ARG_SECTION_POSITION, position)
            args.putInt(Constants.ARG_CHILD_POSITION, childPosition)
            detailsFragment.arguments = args
            return detailsFragment
        }
    }
}

