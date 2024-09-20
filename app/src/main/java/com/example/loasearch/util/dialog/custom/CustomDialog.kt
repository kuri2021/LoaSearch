package com.example.loasearch.util.dialog.custom

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.loasearch.R

class CustomDialog(var context: Context): AppCompatActivity(), CustomDialogInformation {

    private val dialog:Dialog = Dialog(context)

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
            "401" ->{
                dialog.findViewById<TextView>(R.id.error_massage).text = context.getString(R.string.Unauthorized)
            }
            "403"->{
                dialog.findViewById<TextView>(R.id.error_massage).text = context.getString(R.string.Forbidden)
            }
            "500"->{
                dialog.findViewById<TextView>(R.id.error_massage).text = context.getString(R.string.InternalServerError)
            }
            "502"->{
                dialog.findViewById<TextView>(R.id.error_massage).text = context.getString(R.string.BadGateway)
            }
            "503"->{
                dialog.findViewById<TextView>(R.id.error_massage).text = context.getString(R.string.ServiceUnavailable)
            }
            else -> {
                dialog.findViewById<TextView>(R.id.error_massage).text = context.getString(R.string.Unknown)
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