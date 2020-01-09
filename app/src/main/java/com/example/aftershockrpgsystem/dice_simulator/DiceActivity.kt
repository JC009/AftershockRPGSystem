package com.example.aftershockrpgsystem.dice_simulator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.aftershockrpgsystem.R

import kotlinx.android.synthetic.main.activity_dice.*
import kotlinx.android.synthetic.main.content_dice.*
import kotlin.random.Random

class DiceActivity : AppCompatActivity() {

    public var diceNumber = 1
    public var countNumber = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dice)
        setSupportActionBar(toolbar)

        button_d4.setOnClickListener {
            showNumber( 4)
        }

        button_d6.setOnClickListener {
            showNumber( 6)
        }

        button_d8.setOnClickListener {
            showNumber( 8)
        }

        button_d10.setOnClickListener {
            showNumber(10)
        }

        button_d12.setOnClickListener {
            showNumber( 12)
        }
        button_d20.setOnClickListener {
            showNumber( 20)
        }

        button_d100.setOnClickListener {
            showNumber( 100)
        }

        button_minusDiceNumber.setOnClickListener {
            if (diceNumber > 1)
            {
                diceNumber--
                textView_numberOfDice.text = diceNumber.toString()
            }
        }

        button_plusDiceNumber.setOnClickListener {
            if (diceNumber < 20)
            {
                diceNumber++
                textView_numberOfDice.text = diceNumber.toString()
            }
        }

        button_minusCount.setOnClickListener {
            if (countNumber > 1)
            {
                countNumber--
                textView_countValue.text = countNumber.toString()
            }
        }

        button_plusCount.setOnClickListener {
            if (countNumber < 12)
            {
                countNumber++
                textView_countValue.text = countNumber.toString()
            }
        }
    }

    private fun randomNumber (i: Int): Int
    {
        return Random.nextInt(1,i+1)
    }

    private fun  showNumber (int: Int)
    {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        var list = listOf<Int>()
        var count = 0
        var number: Int
        for (x in 0 until diceNumber)
        {
            number = randomNumber(int)
            list += number
            if (number >= countNumber)
                count++
        }

        if (switch_count.isChecked)
        {
            builder.setMessage("Your roll: \n" +
                    " ${list.sorted()} \n" +
                    "Numbers equal or above $countNumber: $count")
        } else
        {
            builder.setMessage("Your roll: \n ${list.sorted()}")
        }

        val dialog: AlertDialog? = builder.create()
        dialog?.show()
    }
}
