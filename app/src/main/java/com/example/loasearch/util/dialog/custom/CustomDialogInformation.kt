package com.example.loasearch.util.dialog.custom

import android.app.Activity

interface CustomDialogInformation {
    fun defaultSetting(layout:Int,callback:(String)->Unit)
    fun errorDialog(code:String,activity: Activity)
}