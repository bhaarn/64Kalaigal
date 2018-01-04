package com.padhuga.tamil.kalaigal.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.google.gson.Gson
import com.padhuga.tamil.kalaigal.R
import com.padhuga.tamil.kalaigal.models.ParentModel

open class BaseActivity : AppCompatActivity() {
    lateinit var parentModel: ParentModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parentModel = Gson().fromJson(application.assets.open(resources.getString(R.string.json_file_name)).bufferedReader().use {
            it.readText()
        }, ParentModel::class.java)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_app_rate -> {
                rateApp()
                return true
            }
            R.id.action_feature_help -> {
                fragmentLoader(HelpFragment())
                return true
            }
            R.id.action_app_share -> {
                shareApp()
                return true
            }
            R.id.action_more_apps -> {
                moreApps()
                return true
            }
            R.id.action_about -> {
                fragmentLoader(AboutFragment())
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    private fun shareApp() {
        val sharingIntent = Intent(Intent.ACTION_SEND)
        sharingIntent.type = "text/plain"
        sharingIntent.putExtra(Intent.EXTRA_SUBJECT, R.string.app_name)
        sharingIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.share_message) + packageName)
        startActivity(Intent.createChooser(sharingIntent, getString(R.string.share_title)))
    }

    private fun moreApps() {
        startActivity(Intent(
                Intent.ACTION_VIEW,
                Uri.parse(getString(R.string.play_more_apps))))
    }

    private fun rateApp() {
        try {
            startActivity(Intent(Intent.ACTION_VIEW,
                    Uri.parse(getString(R.string.old_play_store) + packageName)))
        } catch (activityNotFoundException: android.content.ActivityNotFoundException) {
            startActivity(Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(getString(R.string.new_play_store) + packageName)))
        }

    }

    private fun fragmentLoader(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
                .replace(android.R.id.content, fragment).addToBackStack(null).commit()
    }
}
