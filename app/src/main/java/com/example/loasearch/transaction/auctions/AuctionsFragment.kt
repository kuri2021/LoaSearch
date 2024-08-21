package com.example.loasearch.transaction.auctions

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.loasearch.R
import com.example.loasearch.databinding.FragmentAuctionBinding
import com.example.loasearch.transaction.TransactionActivity
import com.google.android.material.bottomsheet.BottomSheetDialog

class AuctionsFragment:Fragment() {

    private lateinit var binding : FragmentAuctionBinding
    private lateinit var mContext: Context
    private lateinit var mActivity : TransactionActivity
    private lateinit var dialog: Dialog
    private lateinit var bottomSheetDialog:BottomSheetDialog

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
        mActivity = context as TransactionActivity
        dialog = Dialog(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentAuctionBinding.inflate(layoutInflater)

        return binding.root
    }
    fun auctionBottomDialogSet(){
        bottomSheetDialog = BottomSheetDialog(mContext)
        val view: View = layoutInflater.inflate(R.layout.aucrion_dialog, null)
        view.findViewById<Button>(R.id.result).setOnClickListener {
            listSetting()
        }
        bottomSheetDialog.setContentView(view)
        bottomSheetDialog.show()
    }

    fun listSetting(){
        Toast.makeText(mContext,"작동2", Toast.LENGTH_SHORT).show()
        mActivity.dialogClose()
    }



}