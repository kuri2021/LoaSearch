package com.kuri2024.loasearch.util.page

import android.app.Activity

interface PageMoveInterface {
    fun nextActivateActivity(activityKind: Activity,close:Boolean,extra:ArrayList<PageMoveExtraData>?)
    fun getBackActivity()
}