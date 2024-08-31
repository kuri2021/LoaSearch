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

    override fun errorDialog(kind:String,activity:Activity){
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.error_dialog)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        Log.d("code",kind)
        when(kind){
            "auctions_label"->{
                dialog.findViewById<TextView>(R.id.error_massage).text = "레벨 범위가 맞지 않습니다."
            }
            "itemName"->{
                dialog.findViewById<TextView>(R.id.error_massage).text = "아이템 명을 입력해 주세요."
            }
            "503"->{
                dialog.findViewById<TextView>(R.id.error_massage).text = "현재 서버가 임시 점검중 입니다."
            }
        }
        dialog.findViewById<Button>(R.id.result).setOnClickListener {
            when(kind){
                "503"->{
                    activity.finish()
                    dialog.dismiss()
                }
                else->{
                    dialog.dismiss()
                }
            }
        }
        dialog.show()
    }
}