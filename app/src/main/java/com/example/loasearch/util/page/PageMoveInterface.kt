package com.example.loasearch.util.page

import android.app.Activity

interface PageMoveInterface {
    fun nextActivateActivity(activityKind: Activity,close:Boolean,extra:ArrayList<PageMoveExtraData>?)
    fun getBackActivity()
}