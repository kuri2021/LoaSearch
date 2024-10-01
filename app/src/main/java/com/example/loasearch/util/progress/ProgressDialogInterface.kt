package com.example.loasearch.util.progress

import android.app.ProgressDialog

interface ProgressDialogInterface {
    fun customProgressStart(customProgress: ProgressDialog)
    fun customProgressEnd(customProgress:ProgressDialog)
}