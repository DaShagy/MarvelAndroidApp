package com.dashagy.marvelandroidapp.activities

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.dashagy.domain.entities.MarvelCharacter
import com.dashagy.marvelandroidapp.adapters.CharacterListAdapter
import com.dashagy.marvelandroidapp.databinding.ActivityMainBinding
import com.dashagy.marvelandroidapp.ui.dialogs.CharacterDetailsAlertDialog
import com.dashagy.marvelandroidapp.utils.DataStatus
import com.dashagy.marvelandroidapp.viewmodels.CharacterViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModel<CharacterViewModel>()

    private lateinit var adapter: CharacterListAdapter

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = CharacterListAdapter {
            character -> onListCharacterClicked(character)
        }

        viewModel.mainState.observe(::getLifecycle, ::updateUI)

        val recyclerView = binding.root.main_recyclerview
        recyclerView.adapter = adapter
        recyclerView.setHasFixedSize(true)

        val input = binding.searchCharacterEditText.text

        binding.buttonSearchLocal.setOnClickListener{
            onLocalSearchClicked(input)
        }

        binding.buttonSearchRemote.setOnClickListener{
            onRemoteSearchClicked(input)
        }
    }

    private fun updateUI(dataWrapper: DataStatus<List<MarvelCharacter>>) {
        when (dataWrapper) {
            is DataStatus.Error -> {
                hideProgress()
                dataWrapper.error.message.let {
                    showMessage(this, it ?: "No se pudo recuperar el mensaje de error")
                }
            }
            is DataStatus.Loading -> { showProgress() }
            is DataStatus.Successful -> {
                hideProgress()
                setCharacterList(dataWrapper.data)
            }
        }

    }

    private fun setCharacterList(data: List<MarvelCharacter>){
        adapter.updateDataset(data as MutableList<MarvelCharacter>)
        adapter.notifyDataSetChanged()
    }

    private fun showMessage(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    private fun onRemoteSearchClicked(input: Editable) {
        hideKeyboard()
        viewModel.onRemoteSearchClicked(input.toString())
    }

    private fun onLocalSearchClicked(input: Editable) {
        hideKeyboard()
        viewModel.onLocalSearchClicked(input.toString())
    }

    private fun showProgress() {
        progress.visibility = View.VISIBLE
        main_recyclerview.visibility = View.GONE
        buttonSearchLocal.visibility = View.GONE
        buttonSearchRemote.visibility = View.GONE
        searchCharacterEditText.visibility = View.GONE
    }

    private fun hideProgress() {
        progress.visibility = View.GONE
        main_recyclerview.visibility  = View.VISIBLE
        buttonSearchLocal.visibility = View.VISIBLE
        buttonSearchRemote.visibility = View.VISIBLE
        searchCharacterEditText.visibility = View.VISIBLE
    }

    private fun onListCharacterClicked (character: MarvelCharacter){
        val alertDialog = CharacterDetailsAlertDialog(character)
        alertDialog.show(supportFragmentManager, "recycler_view_item")
    }

    private fun hideKeyboard(){
        if (currentFocus != null){
            val inputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
        }
    }
}