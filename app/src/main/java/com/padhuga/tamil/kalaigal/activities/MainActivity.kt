package com.padhuga.tamil.kalaigal.activities

import android.os.Bundle
import android.util.Log
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import com.padhuga.tamil.kalaigal.R
import com.padhuga.tamil.kalaigal.adapters.SectionPagerAdapter
import kotlinx.android.synthetic.main.activity_main_list.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_list)
        initialize()
    }

    private fun initialize() {
        container.adapter = SectionPagerAdapter(supportFragmentManager, parentModel)
        tabLayout.setupWithViewPager(container)

        initializeAds()
    }

    private fun initializeAds() {
        MobileAds.initialize(this, resources.getString(R.string.ad_id))
        val adRequest = AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .addTestDevice("66E56BD85B959A0701EA3C5F7D32E19D")
                .build()
        adView.loadAd(adRequest)
        Log.d("Bharani", adRequest.isTestDevice(this).toString())
    }
}