package com.example.loasearch.transaction.auctions

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.AdapterView
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Spinner
import androidx.fragment.app.Fragment
import com.example.loasearch.R
import com.example.loasearch.api.LoaApi
import com.example.loasearch.api.data.GlobalVariable
import com.example.loasearch.api.data.post_auctions.PostAuctionsItemData
import com.example.loasearch.databinding.FragmentAuctionBinding
import com.example.loasearch.transaction.TransactionActivity
import com.example.loasearch.transaction.adapter.SpinnerAdapter
import com.example.loasearch.transaction.auctions.adapter.AuctionAdapter
import com.example.loasearch.util.dialog.custom.CustomDialog
import com.example.loasearch.util.page.PageMove
import com.google.android.material.bottomsheet.BottomSheetDialog

class AuctionsFragment:Fragment() {

    private lateinit var binding : FragmentAuctionBinding
    private lateinit var mContext: Context
    private lateinit var mActivity : TransactionActivity
    private lateinit var dialog: Dialog
    private lateinit var bottomSheetDialog:BottomSheetDialog
    private lateinit var adapter: AuctionAdapter

    private lateinit var className :String
    private var itemName:String = ""
    private var categoryCode :Int = 0
    private lateinit var tire :String
    private lateinit var gradeName :String
    private var pageNo:Int = 0
    private var lowLevel:Int = 0
    private var highLevel:Int = 0

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
        mActivity = context as TransactionActivity
        dialog = Dialog(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentAuctionBinding.inflate(layoutInflater)

        binding.auctionDefaultRegion.setOnClickListener {
            auctionBottomDialogSet()
        }
        if (GlobalVariable.auctionOption==null){
            LoaApi().getAuctionsOptions{
                if (it != "200"){
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

        val lowLevelEt = view.findViewById<EditText>(R.id.auction_low_label_et)
        val highLevelEt = view.findViewById<EditText>(R.id.auction_hite_label_et)

        lowLevelEt.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE){
                true
            }else{
                false
            }
        }


        val aClassSp = view.findViewById<Spinner>(R.id.auction_class_sp)
        spinnerSetting(aClassSp,getAuctionClasses())
        aClassSp.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                Log.d("auctions",parent.getItemAtPosition(position).toString())
                className = parent.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        val aCategory1Sp = view.findViewById<Spinner>(R.id.auction_category1_sp)
        val aCategory = view.findViewById<LinearLayout>(R.id.auction_category2)
        val aCategory2Sp = view.findViewById<Spinner>(R.id.auction_category2_sp)
        spinnerSetting(aCategory1Sp,getAuctionHighCategory())
        aCategory1Sp.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                Log.d("auctions",parent.getItemAtPosition(position).toString())
                val categoryName = parent.getItemAtPosition(position).toString()
                val index = GlobalVariable.auctionOption!!.Categories.indexOfFirst { it.CodeName == categoryName }
                if (GlobalVariable.auctionOption!!.Categories[index].Subs.isNotEmpty()){
                    aCategory.visibility = View.VISIBLE
                    spinnerSetting(aCategory2Sp,getAuctionLowCategory(index))
                    aCategory2Sp.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                        override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                            val category2Name = parent.getItemAtPosition(position).toString()
                            val index2 = GlobalVariable.auctionOption!!.Categories[index].Subs.indexOfFirst { it.CodeName == category2Name }
                            categoryCode = GlobalVariable.auctionOption!!.Categories[index].Subs[index2].Code
                        }

                        override fun onNothingSelected(parent: AdapterView<*>) {
                        }
                    }
                }else{
                    aCategory.visibility = View.GONE
                    categoryCode = GlobalVariable.auctionOption!!.Categories[index].Code
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
        val aTierSp = view.findViewById<Spinner>(R.id.auction_tier_sp)
        spinnerSetting(aTierSp,getAuctionTiers())
        aTierSp.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                Log.d("auctions",parent.getItemAtPosition(position).toString())
                tire = parent.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
        val aGradeSp = view.findViewById<Spinner>(R.id.auction_grade_sp)
        spinnerSetting(aGradeSp,getAuctionGrades())
        aGradeSp.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                Log.d("auctions",parent.getItemAtPosition(position).toString())
                gradeName = parent.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }



        view.findViewById<Button>(R.id.auction_result).setOnClickListener {
            val checkName = view.findViewById<EditText>(R.id.auction_item_name_et).text.toString().replace(" ","")
            if (checkName!=""){
                itemName = checkName
                val low = if (lowLevelEt.text.toString()==""){
                    0
                }else{
                    lowLevelEt.text.toString().toInt()
                }
                val high = if (highLevelEt.text.toString()==""){
                    1800
                }else{
                    highLevelEt.text.toString().toInt()
                }
                if (labelCheck(low,high)){
                    LoaApi().postAuctions("GRADE",categoryCode,className,tire,gradeName,itemName,pageNo,"ASC",lowLevel,highLevel,null){
                        if (it.Items!=null){
                            itemSearch(it)
                        }else{
                            binding.auctionStatus.text = "조건에 맞는 아이템이 없습니다."
                        }
                        bottomSheetDialog.cancel()
                    }
                }
            }else{
                CustomDialog(dialog).errorDialog("itemName",mActivity)
            }

        }
        bottomSheetDialog.setContentView(view)
        bottomSheetDialog.show()
    }

    private fun spinnerSetting(sp:Spinner,list:ArrayList<String>){
        val adapter = SpinnerAdapter(mContext, R.layout.news_item, list)
        sp.adapter = adapter
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
        for (i in 0..<GlobalVariable.auctionOption!!.Categories[index].Subs.size) {
            list.add(GlobalVariable.auctionOption!!.Categories[index].Subs[i].CodeName)
        }
        return list
    }

    private fun getAuctionClasses():ArrayList<String> {
        val list = ArrayList<String>()
        for (i in 0..<GlobalVariable.auctionOption!!.Classes.size) {
            list.add(GlobalVariable.auctionOption!!.Classes[i])
            Log.d("Classes",GlobalVariable.auctionOption!!.Classes[i])
        }
        return list
    }

    private fun getAuctionGrades():ArrayList<String>  {
        val list = ArrayList<String>()
        list.add("전체 등급")
        for (i in 0..<GlobalVariable.auctionOption!!.ItemGrades.size) {
            list.add(GlobalVariable.auctionOption!!.ItemGrades[i])
        }
        return list
    }

    private fun getAuctionTiers():ArrayList<String>  {
        val list = ArrayList<String>()
        list.add("전체 티어")
        for (i in 0..<GlobalVariable.auctionOption!!.ItemTiers.size) {
            list.add(GlobalVariable.auctionOption!!.ItemTiers[i].toString())
        }
        return list
    }

    private fun labelCheck(low:Int, high:Int):Boolean{
        if (low<high){
            lowLevel = low
            highLevel = high
            return true
        }else{
            CustomDialog(dialog).errorDialog("auctions_label",mActivity)
            return false
        }
    }

    private fun itemSearch(data:PostAuctionsItemData){
        binding.auctionDefaultRegion.visibility = View.GONE
        binding.auctionRecycler.visibility = View.VISIBLE
        adapter= AuctionAdapter(mContext,data.Items)
        binding.auctionRecycler.adapter = adapter
    }



}