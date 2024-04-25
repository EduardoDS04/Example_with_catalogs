package com.example.srodenas.example_with_catalogs.ui.views.activities.fragments.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.srodenas.example_with_catalogs.R
import com.example.srodenas.example_with_catalogs.databinding.UserRegisterDialogBinding
import com.example.srodenas.example_with_catalogs.domain.users.models.User


class DialogRegisterUser (
    val onNewUserDialog: (User)-> Unit
): DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(activity)

            // Get the layout inflater
            val inflater = requireActivity().layoutInflater;

            // Inflate and set the layout for the dialog
            // Pass null as the parent view because its going in the dialog layout
            val viewDialogNewUser = inflater.inflate(R.layout.user_register_dialog, null)
            builder.setView(viewDialogNewUser)
                // Add action buttons
                .setPositiveButton("Registrar Usuario",
                    DialogInterface.OnClickListener { dialog, id ->
                        val newUser = recoverDataLayout(viewDialogNewUser) as User
                        if (
                            newUser.name.isEmpty() ||
                            newUser.email.isEmpty() ||
                            newUser.passw.isEmpty()

                        ){
                            Toast.makeText(activity, "Algún campo está vacío", Toast.LENGTH_LONG).show()
                            getDialog()?.cancel()
                        }else{
                            onNewUserDialog(newUser)
                        }
                    })
                .setNegativeButton("Cancelar",
                    DialogInterface.OnClickListener { dialog, id ->
                        getDialog()?.cancel()
                    })
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")


    }

    private fun recoverDataLayout(view: View): Any {
        val binding = UserRegisterDialogBinding.bind(view)
        return User(
            0,
            binding.txtViewName.text.toString(),
            binding.txtViewEmail.text.toString(),
            binding.txtViewPassword.text.toString(),
            binding.txtViewPhone.text.toString(),
            binding.txtViewUrlImage.text.toString()
        )
    }
}

