package com.example.loasearch.util.dialog.custom

import android.app.Activity
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.loasearch.R
import org.w3c.dom.Text

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

    override fun errorDialog(code:String,activity:Activity){
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.error_dialog)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        Log.d("code",code)
        when(code){
            "503"->{
                dialog.findViewById<TextView>(R.id.error_massage).text = "현재 서버가 임시 점검중 입니다."
            }
        }
        dialog.findViewById<Button>(R.id.result).setOnClickListener {
            if (code == "503"){
                activity.finish()
            }
            dialog.dismiss()
        }
        dialog.show()
    }
}