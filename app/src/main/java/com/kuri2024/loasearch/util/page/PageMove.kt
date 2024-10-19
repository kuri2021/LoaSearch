package com.kuri2024.loasearch.util.page

import android.app.Activity
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import com.kuri2024.loasearch.R

class PageMove(private val activity: Activity) : PageMoveInterface {
    override fun nextActivateActivity(activityKind: Activity, close: Boolean, extra: ArrayList<PageMoveExtraData>?) {
        val intent = Intent(activity.applicationContext, activityKind::class.java)
        if (extra != null) {
            for (i in 0..<extra.size) {

                intent.putExtra(extra[i].name, extra[i].value)
            }
        }
        activity.applicationContext.startActivity(intent.addFlags(FLAG_ACTIVITY_NEW_TASK))
        activity.overridePendingTransition(R.anim.slide_in_right, R.anim.standing_page)
        if (close) {
            activity.finish()
        }
    }

    override fun getBackActivity() {
        activity.overridePendingTransition(R.anim.standing_page, R.anim.slide_out_right)
        activity.finish()
    }
}