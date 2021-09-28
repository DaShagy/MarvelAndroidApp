package com.dashagy.marvelandroidapp.ui.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import coil.load
import com.dashagy.domain.entities.MarvelCharacter
import com.dashagy.marvelandroidapp.databinding.AlertDialogCharacterDetailsBinding

class CharacterDetailsAlertDialog (private val character: MarvelCharacter) : DialogFragment() {

    private var _binding: AlertDialogCharacterDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let{
            val inflater = requireActivity().layoutInflater
            _binding = AlertDialogCharacterDetailsBinding.inflate(inflater)

            binding.characterDescription.text = character.description
            binding.characterImage.load(character.image)

            val builder = AlertDialog.Builder(it)

            builder.setTitle(character.name)
                .setView(binding.root)
                .create()

        } ?: throw IllegalStateException("Activity cannot be null")
    }
}