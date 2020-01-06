package com.example.aftershockrpgsystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.aftershockrpgsystem.calculator.CalculatorActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button_calculator.setOnClickListener {
            // Handler code here.
            val intent = Intent(this, CalculatorActivity::class.java)
            startActivity(intent)
        }
    }


}
