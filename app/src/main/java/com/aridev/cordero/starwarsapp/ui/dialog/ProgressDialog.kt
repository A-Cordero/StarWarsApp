package com.aridev.cordero.starwarsapp.ui.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.Window
import com.aridev.cordero.starwarsapp.databinding.ProgresssDialogBinding


class ProgressDialog (context: Context): Dialog(context){
    private var myContext: Context =context
    private lateinit var binding: ProgresssDialogBinding
    init {
        initDialog()
    }
    private fun initDialog() {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.setCancelable(false)
        binding = ProgresssDialogBinding.inflate(LayoutInflater.from(myContext))
        this.setContentView(binding.root)
        this.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        this.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    fun setProgress(state : Boolean) {
        when(state) {
            true -> this.show()
            false -> this.dismiss()
        }
    }
}