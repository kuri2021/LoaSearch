package com.example.loasearch.transaction.auctions

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.loasearch.R
import com.example.loasearch.api.LoaApi
import com.example.loasearch.api.data.GlobalVariable
import com.example.loasearch.databinding.FragmentAuctionBinding
import com.example.loasearch.transaction.TransactionActivity
import com.example.loasearch.util.dialog.custom.CustomDialog
import com.example.loasearch.util.page.PageMove
import com.google.android.material.bottomsheet.BottomSheetDialog

class AuctionsFragment:Fragment() {

    private lateinit var binding : FragmentAuctionBinding
    private lateinit var mContext: Context
    private lateinit var mActivity : TransactionActivity
    private lateinit var dialog: Dialog
    private lateinit var bottomSheetDialog:BottomSheetDialog

    private lateinit var className :String

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
        mActivity = context as TransactionActivity
        dialog = Dialog(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentAuctionBinding.inflate(layoutInflater)

        binding.auctionDefaultRegion.setOnClickListener {
            auctionBottomDialogSet()
        }
        if (GlobalVariable.auctionOption==null){
            LoaApi().getAuctionsOptions{
                if (it != "성공"){
                    CustomDialog(dialog).defaultSetting(R.layout.error_dialog){
                        PageMove(mActivity).getBackActivity()
                    }
                }
            }
        }

        return binding.root
    }
    fun auctionBottomDialogSet(){
        bottomSheetDialog = BottomSheetDialog(mContext)
        val view: View = layoutInflater.inflate(R.layout.auction_dialog, null)
        val classAdapter = ArrayAdapter(mContext, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, getAuctionClasses())
        val aClassSp = view.findViewById<Spinner>(R.id.auction_class_sp)

        classAdapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item)
        aClassSp.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                className = parent.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        view.findViewById<Button>(R.id.auction_result).setOnClickListener {
            listSetting()
        }
        bottomSheetDialog.setContentView(view)
        bottomSheetDialog.show()
    }

    fun listSetting(){
        Toast.makeText(mContext,"작동2", Toast.LENGTH_SHORT).show()
        mActivity.dialogClose()
    }

    private fun getAuctionHighCategory():ArrayList<String> {
        val list = ArrayList<String>()
        for (i in 0..<GlobalVariable.auctionOption!!.Categories.size) {
            list.add(GlobalVariable.auctionOption!!.Categories[i].CodeName)
        }
        return list
    }

    private fun getAuctionLowCategory(index:Int):ArrayList<String> {
        val list = ArrayList<String>()
        for (i in 0..<GlobalVariable.marketOption!!.Categories[1].Subs.size) {
            list.add(GlobalVariable.marketOption!!.Categories[1].Subs[i].CodeName)
        }
        return list
    }

    private fun getAuctionClasses():ArrayList<String> {
        val list = ArrayList<String>()
        for (i in 0..<GlobalVariable.auctionOption!!.Classes.size) {
            list.add(GlobalVariable.auctionOption!!.Classes[i])
        }
        return list
    }

    private fun getAuctionGrades():ArrayList<String>  {
        val list = ArrayList<String>()
        list.add("전체 등급")
        for (i in 0..<GlobalVariable.marketOption!!.ItemGrades.size) {
            list.add(GlobalVariable.marketOption!!.ItemGrades[i])
        }
        return list
    }

    private fun getAuctionTiers():ArrayList<String>  {
        val list = ArrayList<String>()
        list.add("전체 티어")
        for (i in 0..<GlobalVariable.marketOption!!.ItemTiers.size) {
            list.add(GlobalVariable.marketOption!!.ItemTiers[i].toString())
        }
        return list
    }



}