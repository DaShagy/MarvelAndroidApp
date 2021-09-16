package com.dashagy.marvelandroidapp.activities

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.dashagy.domain.entities.MarvelCharacter
import com.dashagy.marvelandroidapp.adapters.CharacterListAdapter
import com.dashagy.marvelandroidapp.databinding.ActivityMainBinding
import com.dashagy.marvelandroidapp.utils.DataStatus
import com.dashagy.marvelandroidapp.viewmodels.CharacterViewModel
import kotlinx.android.synthetic.main.activity_main.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModel<CharacterViewModel>()

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

        viewModel.mainState.observe(::getLifecycle, ::updateUI)

        val recyclerView = binding.root.main_recyclerview
        recyclerView.adapter = adapter
        recyclerView.setHasFixedSize(true)

        //setFakeData(fakeRepository)
    }

    private fun updateUI(dataWrapper: DataStatus<MarvelCharacter>) {
        when (dataWrapper) {
            is DataStatus.Error -> {
                dataWrapper.error.message.let {
                    showMessage(this, it ?: "No se pudo recuperar el mensaje de error")
                }
            }
            is DataStatus.Loading -> {            }
            is DataStatus.Successful -> {
                dataWrapper.data.let {
                    showMessage(this, it.toString())
                }
            }
        }

    }

    private fun showMessage(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    //------
    private fun setFakeData(data: MutableList<String>){
        adapter.updateDataset(data)
        adapter.notifyDataSetChanged()
    }
    //------
}