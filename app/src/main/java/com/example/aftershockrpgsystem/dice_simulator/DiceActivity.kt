package com.example.aftershockrpgsystem.dice_simulator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.aftershockrpgsystem.R

import kotlinx.android.synthetic.main.activity_dice.*
import kotlinx.android.synthetic.main.content_dice.*

class DiceActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dice)
        setSupportActionBar(toolbar)

        button_dice.setOnClickListener {
            showDialog(it)
        }
    }

    private fun showDialog (view: View)
    {
        val builder : AlertDialog.Builder = AlertDialog.Builder(this)
        val inflater : LayoutInflater = layoutInflater
        val view : View = inflater.inflate(R.layout.dice_dialog, null)

        builder.setView(view)
        /*
        builder.setNegativeButton("No"
        ) { _, _ ->
            //Toast.makeText(this@DiceActivity, "No clicked", Toast.LENGTH_LONG).show()
            //dialog!!.dismiss()
        }

         */

        builder.setPositiveButton("OK"
        ) { _, _ ->
            //Toast.makeText(this@DiceActivity, "Yes clicked", Toast.LENGTH_LONG).show()
        }

        val dialog : AlertDialog = builder.create()
        dialog.show()
    }

}
