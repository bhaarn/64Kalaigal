package com.padhuga.tamil.kalaigal.activities

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.padhuga.tamil.kalaigal.R
import kotlinx.android.synthetic.main.fragment_help.view.*


class HelpFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater?.inflate(R.layout.fragment_help, container, false)
        rootView?.help_text?.setText(R.string.help_text)
        return rootView
    }
}
