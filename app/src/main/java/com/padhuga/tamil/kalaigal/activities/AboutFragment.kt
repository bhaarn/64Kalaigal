package com.padhuga.tamil.kalaigal.activities

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.padhuga.tamil.kalaigal.R
import kotlinx.android.synthetic.main.fragment_about.view.*

class AboutFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val rootView = inflater.inflate(R.layout.fragment_about, container, false)
        rootView.about_text.setText(R.string.about_text)
        return rootView
    }
}