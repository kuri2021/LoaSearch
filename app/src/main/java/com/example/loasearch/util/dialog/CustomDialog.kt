package com.example.loasearch.util.dialog

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity

class CustomDialog(var dialog: Dialog): AppCompatActivity(), CustomDialogInformation {

    override fun defaultSetting(layout:Int){
        dialog.setCancelable(false)
        dialog.setContentView(layout)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()
    }
}