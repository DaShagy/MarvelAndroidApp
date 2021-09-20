package com.dashagy.marvelandroidapp.ui.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.dashagy.domain.entities.MarvelCharacter

class CharacterDetailsAlertDialog (private val character: MarvelCharacter) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let{
            val builder = AlertDialog.Builder(it)

            val title = character.name

            val message = character.description

            builder.setTitle(title)
                .setMessage(message)
                .create()

        } ?: throw IllegalStateException("Activity cannot be null")
    }
}