package com.example.loasearch.transaction.market

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.loasearch.R
import com.example.loasearch.api.LoaApi
import com.example.loasearch.api.data.GlobalVariable
import com.example.loasearch.api.data.market.PostMarketData
import com.example.loasearch.databinding.FragmentMarketBinding
import com.example.loasearch.transaction.TransactionActivity
import com.example.loasearch.transaction.market.adapter.AuctionAdapter
import com.example.loasearch.transaction.market.adapter.AuctionListItem
import com.example.loasearch.util.dialog.custom.CustomDialog
import com.example.loasearch.util.page.PageMove
import com.google.android.material.bottomsheet.BottomSheetDialog

class MarketFragment:Fragment() {

    private lateinit var binding : FragmentMarketBinding
    private lateinit var mContext: Context
    private lateinit var mActivity : TransactionActivity
    private lateinit var dialog:Dialog
    private lateinit var bottomSheetDialog:BottomSheetDialog

    private lateinit var adapter: AuctionAdapter
    var listItem = ArrayList<AuctionListItem>()

    private lateinit var itemName:String
    private lateinit var className :String
    private var categoryCode :Int = 0
    private lateinit var tire :String
    private lateinit var gradeName :String
    private var pageNo:Int = 0

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
        mActivity = context as TransactionActivity
        dialog = Dialog(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentMarketBinding.inflate(layoutInflater)

        binding.marketDefaultRegion.setOnClickListener {
            marketBottomDialogSet()
        }
        if (GlobalVariable.marketOption==null){
            LoaApi().getMarketOptions {
                if (it != "성공"){
                    CustomDialog(dialog).defaultSetting(R.layout.error_dialog){
                        PageMove(mActivity).getBackActivity()
                    }
                }
            }
        }
        return binding.root
    }

    fun marketBottomDialogSet(){
        bottomSheetDialog = BottomSheetDialog(mContext)
        val view: View = layoutInflater.inflate(R.layout.market_dialog, null)

        val classAdapter = ArrayAdapter(mContext, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, getMarketClasses())
        val mClassSp = view.findViewById<Spinner>(R.id.market_class_sp)
        classAdapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item)
        mClassSp.adapter = classAdapter
        mClassSp.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                className = parent.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        val tierAdapter = ArrayAdapter(mContext, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, getMarketTiers())
        val mTierSp = view.findViewById<Spinner>(R.id.market_tier_sp)
        mTierSp.adapter = tierAdapter
        mTierSp.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                tire = parent.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
            }
        }
        val gradeAdapter = ArrayAdapter(mContext, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, getMarketGrades())
        val mGradeSp = view.findViewById<Spinner>(R.id.market_grade_sp)
        mGradeSp.adapter = gradeAdapter
        mGradeSp.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                gradeName = parent.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
            }
        }
        val category1Adapter = ArrayAdapter(mContext, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,getMarketHighCategory())
        val mCategory1Sp = view.findViewById<Spinner>(R.id.market_category1_sp)
        val marketCategory2 = view.findViewById<LinearLayout>(R.id.market_category2)
        val mCategory2Sp = view.findViewById<Spinner>(R.id.market_category2_sp)
        mCategory1Sp.adapter = category1Adapter
        mCategory1Sp.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                val categoryName = parent.getItemAtPosition(position).toString()
                val index = GlobalVariable.marketOption!!.Categories.indexOfFirst { it.CodeName == categoryName }
                if (GlobalVariable.marketOption!!.Categories[index].Subs.isNotEmpty()){
                    marketCategory2.visibility = View.VISIBLE
                    val category2Adapter = ArrayAdapter(mContext, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,getMarketLowCategory(index))
                    mCategory2Sp.adapter = category2Adapter
                    mCategory2Sp.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                        override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                            val category2Name = parent.getItemAtPosition(position).toString()
                            val index2 = GlobalVariable.marketOption!!.Categories[index].Subs.indexOfFirst { it.CodeName == category2Name }
                            categoryCode = GlobalVariable.marketOption!!.Categories[index].Subs[index2].Code
                        }

                        override fun onNothingSelected(parent: AdapterView<*>) {
                        }
                    }
                }else{
                    marketCategory2.visibility = View.GONE
                    categoryCode = GlobalVariable.marketOption!!.Categories[index].Code
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
            }
        }


        view.findViewById<Button>(R.id.market_result).setOnClickListener {
            itemName = view.findViewById<EditText>(R.id.market_item_name_et).text.toString()
            listItem.clear()
            itemSearch()
        }
        bottomSheetDialog.setContentView(view)
        bottomSheetDialog.show()
    }
    private fun itemSearch(){
        LoaApi().postMarkets("GRADE",categoryCode,className,tire,gradeName,itemName,pageNo,"ASC"){
            if (it.Items.isNotEmpty()){
                listSetting(it)
            }else{
                binding.marketStatus.text = "조건에 맞는 아이템이 없습니다."
            }
            bottomSheetDialog.cancel()
        }
    }

    private fun listSetting(data: PostMarketData){
        binding.marketDefaultRegion.visibility = View.GONE
        binding.marketRecycler.visibility = View.VISIBLE
        for (i in 0..<data.Items.size){
            val icon = data.Items[i].Icon
            val currentMinPrice = data.Items[i].CurrentMinPrice
            val name = data.Items[i].Name
            val recentPrice = data.Items[i].RecentPrice
            val tradeRemainCount = data.Items[i].TradeRemainCount
            val yDayAvgPrice = data.Items[i].YDayAvgPrice
            listItem.add(AuctionListItem(icon,currentMinPrice,name,recentPrice,tradeRemainCount,yDayAvgPrice))
        }
        adapter = AuctionAdapter(mContext,listItem)
        binding.marketRecycler.adapter = adapter
        binding.marketRecycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                // 마지막 스크롤된 항목 위치
                val lastVisibleItemPosition = (recyclerView.layoutManager as LinearLayoutManager?)!!.findLastCompletelyVisibleItemPosition()
                // 항목 전체 개수
                val itemTotalCount = recyclerView.adapter!!.itemCount - 1
                if (lastVisibleItemPosition == itemTotalCount) {
                    pageNo+=1
                    itemSearch()
                }
            }
        })
    }

    private fun getMarketHighCategory():ArrayList<String> {
        val list = ArrayList<String>()
        for (i in 0..<GlobalVariable.marketOption!!.Categories.size) {
            list.add(GlobalVariable.marketOption!!.Categories[i].CodeName)
        }
        return list
    }

    private fun getMarketLowCategory(index:Int):ArrayList<String> {
        val list = ArrayList<String>()
        for (i in 0..<GlobalVariable.marketOption!!.Categories[1].Subs.size) {
            list.add(GlobalVariable.marketOption!!.Categories[1].Subs[i].CodeName)
        }
        return list
    }

    private fun getMarketClasses():ArrayList<String> {
        val list = ArrayList<String>()
        for (i in 0..<GlobalVariable.marketOption!!.Classes.size) {
            list.add(GlobalVariable.marketOption!!.Classes[i])
        }
        return list
    }

    private fun getMarketGrades():ArrayList<String>  {
        val list = ArrayList<String>()
        list.add("전체 등급")
        for (i in 0..<GlobalVariable.marketOption!!.ItemGrades.size) {
            list.add(GlobalVariable.marketOption!!.ItemGrades[i])
        }
        return list
    }

    private fun getMarketTiers():ArrayList<String>  {
        val list = ArrayList<String>()
        list.add("전체 티어")
        for (i in 0..<GlobalVariable.marketOption!!.ItemTiers.size) {
            list.add(GlobalVariable.marketOption!!.ItemTiers[i].toString())
        }
        return list
    }

}