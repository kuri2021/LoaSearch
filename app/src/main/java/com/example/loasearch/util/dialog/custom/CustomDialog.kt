package com.example.loasearch.util.dialog.custom

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.loasearch.R

class CustomDialog(var dialog: Dialog): AppCompatActivity(), CustomDialogInformation {

    override fun defaultSetting(layout:Int,callback:(String)->Unit){
        dialog.setCancelable(false)
        dialog.setContentView(layout)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.findViewById<Button>(R.id.result).setOnClickListener {
            callback("enter")
            dialog.dismiss()
        }
        dialog.show()
    }
}