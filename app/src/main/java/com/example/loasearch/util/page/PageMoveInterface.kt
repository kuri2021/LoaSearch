package com.example.loasearch.util.page

import android.app.Activity

interface PageMoveInterface {
    fun nextActivateActivity(activityKind: Activity)
    fun getBackActivity()
}