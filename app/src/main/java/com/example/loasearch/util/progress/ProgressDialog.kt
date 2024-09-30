package com.example.loasearch.util.progress

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable

class ProgressDialog(context:Context): Dialog(context!!), ProgressDialogInterface {

    private var progressStatus = false

    override fun customProgress_start(customProgress: ProgressDialog) {
        if (!progressStatus) {
            progressStatus = true
            customProgress.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            customProgress.setCanceledOnTouchOutside(false)
            customProgress.setCancelable(false)
            customProgress.show()
        }
    }

    override fun customProgress_end(customProgress: ProgressDialog) {
        customProgress.setCanceledOnTouchOutside(true);
        customProgress.setCancelable(true)
        customProgress.cancel()
        progressStatus = false
    }
}