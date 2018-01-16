package com.padhuga.tamil.kalaigal.activities

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.google.gson.Gson
import com.padhuga.tamil.kalaigal.R
import com.padhuga.tamil.kalaigal.models.ParentModel
import com.padhuga.tamil.kalaigal.models.SearchRetriever
import com.padhuga.tamil.kalaigal.utilities.Constants
import java.util.*

open class BaseActivity : AppCompatActivity() {
    lateinit var parentModel: ParentModel
    private lateinit var menu: Menu

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        handleIntent(intent)
        parentModel = Gson().fromJson(application.assets.open(resources.getString(R.string.json_file_name)).bufferedReader().use {
            it.readText()
        }, ParentModel::class.java)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        this.menu = menu
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_app_search -> consume { performSearch(menu) }
        R.id.action_app_rate -> consume { rateApp() }
        R.id.action_feature_help -> consume { fragmentLoader(HelpFragment()) }
        R.id.action_app_share -> consume { shareApp() }
        R.id.action_more_apps -> consume { moreApps() }
        R.id.action_about -> consume { fragmentLoader(AboutFragment()) }
        else -> super.onOptionsItemSelected(item)
    }

    private inline fun consume(f: () -> Unit): Boolean {
        f()
        return true
    }

    override fun onNewIntent(intent: Intent) {
        handleIntent(intent)
    }

    private fun performSearch(menu: Menu) {
        Toast.makeText(applicationContext, R.string.tamil_keyboard_check, Toast.LENGTH_LONG).show()
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu.findItem(R.id.action_app_search).actionView as SearchView
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(componentName))
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

    private fun handleIntent(intent: Intent) {

        if (Intent.ACTION_SEARCH == intent.action) {
            val query = intent.getStringExtra(SearchManager.QUERY)
            val searchFragment = SearchFragment()
            val args = Bundle()
            args.putString(Constants.ARG_QUERY_TEXT, query)
            searchFragment.arguments = args
            supportFragmentManager.beginTransaction()
                    .replace(android.R.id.content, searchFragment, searchFragment.javaClass.simpleName).addToBackStack(null).commit()
        }
    }

    fun showSearchResults(query: String): ArrayList<SearchRetriever> {
        val searchRetriever = ArrayList<SearchRetriever>()
        for (position in 0 until parentModel.data.type.size) {
            (0 until parentModel.data.type[position].type.size)
                    .filter {
                        parentModel.data.type[position].type[it].title.contains(query) ||
                                parentModel.data.type[position].type[it].soothiram.contains(query) ||
                                parentModel.data.type[position].type[it].desc.contains(query) ||
                                parentModel.data.type[position].type[it].example.contains(query)
                    }
                    .mapTo(searchRetriever) {
                        SearchRetriever(parentModel.data.type[position].type[it].title,
                                position, it)
                    }
        }
        return searchRetriever
    }
}
