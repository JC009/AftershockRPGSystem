package com.example.aftershockrpgsystem.dice_simulator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.aftershockrpgsystem.R

import kotlinx.android.synthetic.main.activity_dice.*
import kotlinx.android.synthetic.main.content_dice.*
import kotlinx.android.synthetic.main.dice_dialog.*
import kotlin.random.Random

class DiceActivity : AppCompatActivity() {

    public var dialogText = "0"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dice)
        setSupportActionBar(toolbar)

        button_d4.setOnClickListener {
            showDialog(it, 4)
        }

        button_d6.setOnClickListener {
            showDialog(it, 6)
        }

        button_d8.setOnClickListener {
            showDialog(it, 8)
        }

        button_d10.setOnClickListener {
            showDialog(it, 10)
        }

        button_d12.setOnClickListener {
            showDialog(it, 12)
        }
        button_d20.setOnClickListener {
            showDialog(it, 20)
        }

        button_d100.setOnClickListener {
            showDialog(it, 100)
        }
    }

    private fun randomNumber (i: Int): Int
    {
        return Random.nextInt(1,i+1)
    }

    private fun showDialog (view: View, dice: Int)
    {
        val number = randomNumber(dice)



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
