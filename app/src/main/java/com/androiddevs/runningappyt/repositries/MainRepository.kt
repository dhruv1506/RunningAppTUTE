package com.androiddevs.runningappyt.repositries

import com.androiddevs.runningappyt.db.Run
import com.androiddevs.runningappyt.db.RunDAO
import javax.inject.Inject

class MainRepository @Inject constructor(val runDAO: RunDAO) {

    suspend fun insertRun(run: Run) = runDAO.insertRun(run)

    suspend fun deleteRun(run: Run) = runDAO.deleteRun(run)

    // Function to get all type of sorted runs based on different parameters
    fun getAllRunsSortedByDate() = runDAO.getAllRunsSortedByDate()

    fun getAllRunsSortedByAverageSpeed() = runDAO.getAllRunsSortedByAverageSpeed()

    fun getAllRunsSortedByCaloriesBurned() = runDAO.getAllRunsSortedByCaloriesBurned()

    fun getAllRunsSortedByDistance() = runDAO.getAllRunsSortedByDistance()

    fun getAllRunsSortedByTimesInMillis() = runDAO.getAllRunsSortedByTimesInMillis()

    // aggregated functions for different parameters

    fun getTotalAvgSpeed() = runDAO.getTotalAvgSpeed()

    fun getTotalCaloriesBurned() = runDAO.getTotalCaloriesBurned()

    fun getTotalDistance() = runDAO.getTotalDistance()

    fun getTotalTimeInMillis() = runDAO.getTotalTimeInMillis()
}