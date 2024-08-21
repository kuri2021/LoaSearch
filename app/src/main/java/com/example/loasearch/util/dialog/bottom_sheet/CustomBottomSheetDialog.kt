package com.example.loasearch.util.dialog.bottom_sheet

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.loasearch.R
import com.example.loasearch.transaction.market.MarketFragment
import com.google.android.material.bottomsheet.BottomSheetDialog

class CustomBottomSheetDialog(var context: Context): AppCompatActivity(), CustomBottomSheetDialogInformation {

    override fun marketOptionDialog(){
        val bottomSheetDialog = BottomSheetDialog(context)
        val view =  layoutInflater.inflate(R.layout.market_dialog, null)
        view.findViewById<Button>(R.id.result).setOnClickListener {
            val fragment = supportFragmentManager.findFragmentById(R.id.transition_frg) as MarketFragment
            fragment.listSetting()
        }
        bottomSheetDialog.setContentView(view)
        bottomSheetDialog.show()
    }

    override fun auctionOptionDialog(){
        val bottomSheetDialog = BottomSheetDialog(context)
        val view =  layoutInflater.inflate(R.layout.aucrion_dialog, null)
        view.findViewById<Button>(R.id.result).setOnClickListener {
            val fragment = supportFragmentManager.findFragmentById(R.id.transition_frg) as MarketFragment
            fragment.listSetting()
        }
        bottomSheetDialog.setContentView(view)
        bottomSheetDialog.show()
    }
}