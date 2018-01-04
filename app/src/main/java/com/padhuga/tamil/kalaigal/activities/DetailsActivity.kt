package com.padhuga.tamil.kalaigal.activities

import android.os.Bundle
import android.util.Log
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import com.padhuga.tamil.kalaigal.R
import com.padhuga.tamil.kalaigal.adapters.SectionDetailAdapter
import com.padhuga.tamil.kalaigal.utilities.Constants
import kotlinx.android.synthetic.main.activity_main_list.*
import kotlinx.android.synthetic.main.content_main.*


class DetailsActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val position = intent.getIntExtra(Constants.ARG_SECTION_POSITION, -1)
        val childPosition = intent.getIntExtra(Constants.ARG_CHILD_POSITION, -1)
        initialize(position, childPosition)
    }

    private fun initialize(position: Int, childPosition: Int) {
        val mSectionDetailAdapter = SectionDetailAdapter(supportFragmentManager, parentModel, position)
        container.adapter = mSectionDetailAdapter
        container.currentItem = childPosition

        initializeAds()
    }

    private fun initializeAds() {
        MobileAds.initialize(this, resources.getString(R.string.ad_id))
        val adRequest = AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .addTestDevice("66E56BD85B959A0701EA3C5F7D32E19D")
                .build()
        adView.loadAd(adRequest)
        val b = adRequest.isTestDevice(this)
        Log.d("Bharani", b.toString())
    }
}

