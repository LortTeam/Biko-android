package hu.pe.biko.biko

import android.app.Dialog
import android.app.DialogFragment
import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View

/**
 * Created by nikita on 28.05.17.
 */

class FinishedDialog : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle): Dialog {
        val builder = AlertDialog.Builder(activity)
        val inflater = activity.layoutInflater
        val view = inflater.inflate(R.layout.finished_dialog, null)
        builder.setView(view)
        // Остальной код
        return builder.create()
    }
}
