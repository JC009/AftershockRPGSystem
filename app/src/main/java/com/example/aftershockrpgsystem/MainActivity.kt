package com.example.aftershockrpgsystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.aftershockrpgsystem.calculator.CalculatorActivity
import com.example.aftershockrpgsystem.dice_simulator.DiceActivity
import com.example.aftershockrpgsystem.items.ItemsActivity
import com.example.aftershockrpgsystem.rules.RulesActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button_items.setOnClickListener {
            val intent = Intent(this, ItemsActivity::class.java)
            startActivity(intent)
        }

        button_calculator.setOnClickListener {
            // Handler code here.
            val intent = Intent(this, CalculatorActivity::class.java)
            startActivity(intent)
        }

        button_roller.setOnClickListener {
            val intent = Intent(this, DiceActivity::class.java)
            startActivity(intent)
        }

        button_rules.setOnClickListener {
            val intent = Intent(this, RulesActivity::class.java)
            startActivity(intent)
        }
    }
}
