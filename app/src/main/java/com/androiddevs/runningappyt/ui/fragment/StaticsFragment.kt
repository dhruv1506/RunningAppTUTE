package com.androiddevs.runningappyt.ui.fragment

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.androiddevs.runningappyt.R
import com.androiddevs.runningappyt.ui.viewmodels.MainViewModel
import com.androiddevs.runningappyt.ui.viewmodels.StaticsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StaticsFragment:Fragment(R.layout.fragment_statistics) {

    private val viewModel:StaticsViewModel by viewModels()
}