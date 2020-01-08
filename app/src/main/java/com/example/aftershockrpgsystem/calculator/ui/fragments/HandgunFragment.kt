package com.example.aftershockrpgsystem.calculator.ui.fragments

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*

import com.example.aftershockrpgsystem.R
import com.example.aftershockrpgsystem.ammo.ammoList
import kotlinx.android.synthetic.main.fragment_handgun.*
import kotlin.math.round

class HandgunFragment : Fragment() {

    companion object {
        fun newInstance() = HandgunFragment()
    }

    private lateinit var viewModel: HandgunViewModel

    private lateinit var stats: List<TextView>
    private lateinit var buttons: List<Button>
    private lateinit var ammo: List<List<Int>>
    private var enemyArmor = 0
    private var numberOfShots = 1
    private var hittingOn = 0
    private var damage = 0
    private val dice = 6

    private var currentAmmo = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_handgun, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(HandgunViewModel::class.java)
        // TODO: Use the ViewModel

        stats = listOf(textView_precision, textView_armorPenetration, textView_damage)

        buttons = listOf(mm9_button, acp45_button, mm10_button, ae50_button,
            mag357_button, mag44_button, mag500_button)

        ammo = listOf(ammoList.MM9().stats, ammoList.ACP45().stats, ammoList.MM10().stats,
            ammoList.AE50().stats, ammoList.MAG357().stats, ammoList.MAG44().stats, ammoList.MAG500().stats)

        radioButton_short.isChecked = true

        for (x in buttons.indices){
            buttons[x].setBackgroundResource(android.R.drawable.btn_default)
        }

        onAmmoSelect(0)
        textView_ammunitionStatistics.text = "9mm Parabellum Statistics"

        seekBarArmor.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                // Display the current progress of SeekBar
                textView_enemyArmor.text = "Enemy Armor: $i"
                enemyArmor = i
                chanceToHit()
                averageDamage()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })

        seekBarShots.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                // Display the current progress of SeekBar
                textView_shotsFired.text = "Shots on Target: $i"
                numberOfShots = i
                averageDamage()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })


        mm9_button.setOnClickListener {
            onAmmoSelect(0)
            textView_ammunitionStatistics.text = "9mm Parabellum Statistics"
        }

        acp45_button.setOnClickListener {
            onAmmoSelect(1)
            textView_ammunitionStatistics.text = ".45 ACP Statistics"
        }

        mm10_button.setOnClickListener {
            onAmmoSelect(2)
            textView_ammunitionStatistics.text = "10mm AUTO Statistics"
        }

        ae50_button.setOnClickListener {
            onAmmoSelect(3)
            textView_ammunitionStatistics.text = ".50 Action Express Statistics"
        }

        mag357_button.setOnClickListener {
            onAmmoSelect(4)
            textView_ammunitionStatistics.text = ".357 Magnum Statistics"
        }

        mag44_button.setOnClickListener {
            onAmmoSelect(5)
            textView_ammunitionStatistics.text = ".44 Magnum Statistics"
        }

        mag500_button.setOnClickListener {
            onAmmoSelect(6)
            textView_ammunitionStatistics.text = ".500 S&W Magnum Statistics"
        }

        radioButton_short.setOnCheckedChangeListener { buttonView, isChecked -> onAmmoSelect(currentAmmo); chanceToHit() }
        radioButton_medium.setOnCheckedChangeListener { buttonView, isChecked -> onAmmoSelect(currentAmmo); chanceToHit() }
        radioButton_long.setOnCheckedChangeListener { buttonView, isChecked -> onAmmoSelect(currentAmmo); chanceToHit() }
    }

    private fun onAmmoSelect (position: Int)
    {
        if (radioButton_short.isChecked)
        {
            textView_precision.text = ammo[position][0].toString()
            textView_armorPenetration.text = ammo[position][3].toString()
            textView_damage.text = ammo[position][6].toString()
            damage = ammo[position][6]
        }
        if (radioButton_medium.isChecked)
        {
            textView_precision.text = ammo[position][1].toString()
            textView_armorPenetration.text = ammo[position][4].toString()
            textView_damage.text = ammo[position][7].toString()
            damage = ammo[position][7]
        }
        if (radioButton_long.isChecked)
        {
            textView_precision.text = ammo[position][2].toString()
            textView_armorPenetration.text = ammo[position][5].toString()
            textView_damage.text = ammo[position][8].toString()
            damage = ammo[position][8]
        }

        currentAmmo = position

        chanceToHit()
        averageDamage()
    }

    private fun chanceToHit ()
    {
        val armorPen: Int
        val result: Int

        when {
            radioButton_short.isChecked -> {
                armorPen = ammo[currentAmmo][3]
                result = enemyArmor - armorPen
                when {
                    result <= 1 -> {
                        textView_hittingOn.text = "All"
                    }
                    result > 6 -> {
                        textView_hittingOn.text = "None"
                    }
                    else -> {
                        textView_hittingOn.text = result.toString()
                        hittingOn = result
                    }
                }
            }
            radioButton_medium.isChecked -> {
                armorPen = ammo[currentAmmo][4]
                result = enemyArmor - armorPen
                when {
                    result <= 1 -> {
                        textView_hittingOn.text = "All"
                    }
                    result > 6 -> {
                        textView_hittingOn.text = "None"
                    }
                    else -> {
                        textView_hittingOn.text = result.toString()
                        hittingOn = result
                    }
                }
            }
            radioButton_long.isChecked -> {
                armorPen = ammo[currentAmmo][5]
                result = enemyArmor - armorPen
                when {
                    result <= 1 -> {
                        textView_hittingOn.text = "All"
                    }
                    result > 6 -> {
                        textView_hittingOn.text = "None"
                    }
                    else -> {
                        textView_hittingOn.text = result.toString()
                        hittingOn = result
                    }
                }
            }
        }
    }

    private fun averageDamage ()
    {
        when (textView_hittingOn.text) {
            "None" -> {
                textView_averageDamage.text = "0"
            }
            "All" -> {
                textView_averageDamage.text = (damage * numberOfShots).toString()
            }
            else -> {
                val sum: Double = (damage.toDouble() * numberOfShots.toDouble()) * (dice + 1 - hittingOn.toDouble()) / dice
                textView_averageDamage.text = (sum.round(1)).toString()
            }
        }
    }

    private fun Double.round(decimals: Int): Double {
        var multiplier = 1.0
        repeat(decimals) { multiplier *= 10 }
        return round(this * multiplier) / multiplier
    }
}
