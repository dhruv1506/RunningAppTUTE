package com.androiddevs.runningappyt.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.androiddevs.runningappyt.R
import com.androiddevs.runningappyt.db.RunDAO
import com.androiddevs.runningappyt.other.Constants.ACTION_SHOW_TRACKING_FRAGMENT
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        navigateToTrackingFramgmentIfNeeded(intent)
        bottomNavigationView.setupWithNavController(navHostFragment.findNavController())

        navHostFragment.findNavController().addOnDestinationChangedListener { _, destination, _ ->

            when(destination.id)
            {
                R.id.settingsFragment,R.id.runFragment,R.id.staticsFragment ->
                    bottomNavigationView.visibility= View.VISIBLE
                else ->
                    bottomNavigationView.visibility=View.GONE
            }

        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        navigateToTrackingFramgmentIfNeeded(intent)
    }
    //

    private fun navigateToTrackingFramgmentIfNeeded(intent: Intent?)
    {


        if (intent?.action==ACTION_SHOW_TRACKING_FRAGMENT)
        {
            navHostFragment.findNavController().navigate(R.id.action_global_trackingFragment)
        }
    }
}
