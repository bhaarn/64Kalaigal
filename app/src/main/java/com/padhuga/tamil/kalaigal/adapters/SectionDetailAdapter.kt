package com.padhuga.tamil.kalaigal.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.padhuga.tamil.kalaigal.activities.DetailsFragment
import com.padhuga.tamil.kalaigal.models.ParentModel


class SectionDetailAdapter(fm: FragmentManager, private val parentModel: ParentModel, private val position: Int) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return DetailsFragment.newInstance(this.position, position)
    }

    override fun getCount(): Int {
        return parentModel.data.type[position].type.size
    }

    override fun getPageTitle(position: Int): CharSequence {
        return this.parentModel.data.type[position].title
    }
}