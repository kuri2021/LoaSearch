package com.example.loasearch.util.progress

import android.app.Dialog
import android.app.ProgressDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import com.example.loasearch.R

class CustomProgressDialog(context:Context?): Dialog(context!!), ProgressDialogInterface {

    private var progressStatus = false

    init {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.dialog_progress)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setCanceledOnTouchOutside(false)
        setCancelable(false)
    }

    override fun customProgressStart(customProgress: ProgressDialog) {
        if (!progressStatus) {
            show()
        }
    }

    override fun customProgressEnd(customProgress: ProgressDialog) {
        if (progressStatus) dismiss()
    }
}