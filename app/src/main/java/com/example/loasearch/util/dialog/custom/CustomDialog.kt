package com.example.loasearch.util.dialog.custom

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.loasearch.R
import com.example.loasearch.api.data.GlobalVariable

class CustomDialog(var context: Context): AppCompatActivity(), CustomDialogInformation {

    private val dialog:Dialog = Dialog(context)

    override fun defaultSetting(layout:Int,callback:(String)->Unit){
        dialog.setCancelable(false)
        dialog.setContentView(layout)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window?.setLayout(
            (Resources.getSystem().displayMetrics.widthPixels * 0.85).toInt(),  // 화면의 85% 너비로 설정
            WindowManager.LayoutParams.WRAP_CONTENT  // 높이는 콘텐츠에 맞게 설정
        )
        dialog.findViewById<Button>(R.id.result).setOnClickListener {
            callback("enter")
            dialog.dismiss()
        }
        dialog.show()
    }

    override fun errorDialog(code:String, activity:Activity,callback:(Boolean)->Unit){
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.error_dialog)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        Log.d("code",code)
        when(code){
            "signupFail"->{
                dialog.findViewById<TextView>(R.id.error_massage).text = "회원 등록에 실패하였습니다\n다시 시도 해 주세요"
            }
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
            when(code){
                "503"->{
                    GlobalVariable.resetData()
                    activity.finish()
                    dialog.dismiss()
                }
                else->{
                    callback(true)
                    dialog.dismiss()
                }
            }
        }
        dialog.show()
    }
}