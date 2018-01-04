package com.padhuga.tamil.kalaigal.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.padhuga.tamil.kalaigal.activities.MainFragment
import com.padhuga.tamil.kalaigal.models.ParentModel

class SectionPagerAdapter(fm: FragmentManager, private val parentModel: ParentModel) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return MainFragment.newInstance(position)
    }

    override fun getCount(): Int {
        return parentModel.data.type.size
    }

    override fun getPageTitle(position: Int): CharSequence {
        return parentModel.data.type[position].title
    }
}
