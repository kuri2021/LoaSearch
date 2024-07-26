package com.example.loasearch.util.page

import android.app.Activity
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import com.example.loasearch.R

class PageMove(private val activity: Activity):PageMoveInterface {
    override fun nextActivateActivity(activityKind: Activity){
        val intent = Intent(activity.applicationContext, activityKind::class.java)
        activity.applicationContext.startActivity(intent.addFlags(FLAG_ACTIVITY_NEW_TASK))
        activity.overridePendingTransition(R.anim.slide_in_right, R.anim.standing_page)
    }
    override fun getBackActivity(){
        activity.finish()
        activity.overridePendingTransition(R.anim.standing_page,R.anim.slide_out_right)
    }
}