package com.example.labsny.ui.bases

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {
    lateinit var binding: VB
    abstract val bindingInflate: (LayoutInflater) -> VB

    //    abstract val presenter:T
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = bindingInflate(layoutInflater)
        setContentView(binding.root)
    }

}