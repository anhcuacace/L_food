package com.tunanh.lfood.ativity.fragment

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle

import android.util.Log
import android.view.LayoutInflater
import android.view.View

import android.view.ViewGroup

import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts

import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide

import com.tunanh.lfood.databinding.FragmentMoreBinding



class More : Fragment() {
    private var requestMultiplePermissions = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        permissions.entries.forEach {
            Log.d("DEBUG", "${it.key} = ${it.value}")
        }
    }
    private var  createItemBinding: FragmentMoreBinding? = null
    private val binding get() = createItemBinding!!
    private val startForResult=
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode== Activity.RESULT_OK){
                val intent=result.data
                if (intent!=null){
                    val url=intent.data
                    url.let {
                        Glide.with(view!!.context).load(it).into(binding.imageView2)
                    }
                }
            }
        }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        createItemBinding = FragmentMoreBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        binding.imageView2.setOnClickListener {
            onRequestPermissions()

        }
    }

    private fun onRequestPermissions() {
        if (Build.VERSION.SDK_INT<Build.VERSION_CODES.M){
            openGallery()
        }else{
            if (activity?.checkSelfPermission(android.Manifest.permission.ACCESS_MEDIA_LOCATION) == PackageManager.PERMISSION_GRANTED){
                openGallery()
            }else{
                requestMultiplePermissions.launch(
                    arrayOf(
                        android.Manifest.permission.ACCESS_FINE_LOCATION
                    )
                )
            }
        }

    }


    private fun openGallery() {
        val intent=Intent()
        intent.setType("image/*")
        intent.setAction(Intent.ACTION_GET_CONTENT)
        startForResult.launch(Intent.createChooser(intent,"Select Picture"))
    }


}