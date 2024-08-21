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
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.loasearch.R
import com.example.loasearch.api.LoaApi
import com.example.loasearch.api.data.GlobalVariable
import com.example.loasearch.databinding.FragmentMarketBinding
import com.example.loasearch.transaction.TransactionActivity
import com.example.loasearch.util.dialog.custom.CustomDialog
import com.google.android.material.bottomsheet.BottomSheetDialog

class MarketFragment:Fragment() {

    private lateinit var binding : FragmentMarketBinding
    private lateinit var mContext: Context
    private lateinit var mActivity : TransactionActivity
    private lateinit var dialog:Dialog
    private lateinit var bottomSheetDialog:BottomSheetDialog

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
        mActivity = context as TransactionActivity
        dialog = Dialog(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentMarketBinding.inflate(layoutInflater)

        binding.auctionDefaultRegion.setOnClickListener {
            CustomDialog(dialog).defaultSetting(R.layout.error_dialog)
        }
        if (GlobalVariable.marketOption==null){
            LoaApi().getMarketOptions {
                if (it != "성공"){
                    CustomDialog(dialog).defaultSetting(R.layout.error_dialog)
                }
            }
        }
        return binding.root
    }

    fun marketBottomDialogSet(){
        bottomSheetDialog = BottomSheetDialog(mContext)
        val items = arrayOf("Option 1", "Option 2", "Option 3", "Option 4")

        val view: View = layoutInflater.inflate(R.layout.market_dialog, null)
        val classAdapter = ArrayAdapter(mContext, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,items)
        val mClassSp = view.findViewById<Spinner>(R.id.market_class_sp)
        classAdapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item)
        mClassSp.adapter = classAdapter
        mClassSp.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                // 선택된 항목에 대한 동작 처리
                val selectedItem = parent.getItemAtPosition(position).toString()
                Toast.makeText(mContext, "클래스Selected: $selectedItem", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // 아무것도 선택되지 않았을 때의 동작 처리
            }
        }
        val mTierSp = view.findViewById<Spinner>(R.id.market_tier_sp)
        mTierSp.adapter = classAdapter
        mTierSp.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                // 선택된 항목에 대한 동작 처리
                val selectedItem = parent.getItemAtPosition(position).toString()
                Toast.makeText(mContext, "티어Selected: $selectedItem", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // 아무것도 선택되지 않았을 때의 동작 처리
            }
        }
        val mGradeSp = view.findViewById<Spinner>(R.id.market_grade_sp)
        mGradeSp.adapter = classAdapter
        mGradeSp.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                // 선택된 항목에 대한 동작 처리
                val selectedItem = parent.getItemAtPosition(position).toString()
                Toast.makeText(mContext, "등급Selected: $selectedItem", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // 아무것도 선택되지 않았을 때의 동작 처리
            }
        }
        val mCategorySp = view.findViewById<Spinner>(R.id.market_category_sp)
        mCategorySp.adapter = classAdapter
        mCategorySp.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                // 선택된 항목에 대한 동작 처리
                val selectedItem = parent.getItemAtPosition(position).toString()
                Toast.makeText(mContext, "카테고리 Selected: $selectedItem ", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // 아무것도 선택되지 않았을 때의 동작 처리
            }
        }
        view.findViewById<Button>(R.id.market_result).setOnClickListener {
            listSetting()
        }
        bottomSheetDialog.setContentView(view)
        bottomSheetDialog.show()
    }

    fun listSetting(){
        Toast.makeText(mContext,"작동",Toast.LENGTH_SHORT).show()

        mActivity.dialogClose()
    }

}