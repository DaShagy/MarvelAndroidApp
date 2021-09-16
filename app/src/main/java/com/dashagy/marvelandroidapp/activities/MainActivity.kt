package com.dashagy.marvelandroidapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dashagy.marvelandroidapp.adapters.CharacterListAdapter
import com.dashagy.marvelandroidapp.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.view.*

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: CharacterListAdapter

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    //------
    //private var fakeRepository = mutableListOf("John", "Jack", "Jill")
    //------

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = CharacterListAdapter()

        val recyclerView = binding.root.main_recyclerview
        recyclerView.adapter = adapter
        recyclerView.setHasFixedSize(true)

        //setFakeData(fakeRepository)
    }

    //------
    private fun setFakeData(data: MutableList<String>){
        adapter.updateDataset(data)
        adapter.notifyDataSetChanged()
    }
    //------
}