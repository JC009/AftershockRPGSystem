package com.example.aftershockrpgsystem.calculator.ui.fragments

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView

import com.example.aftershockrpgsystem.R
import com.example.aftershockrpgsystem.ammo.ammoList
import kotlinx.android.synthetic.main.fragment_shotgun.*

class ShotgunFragment : Fragment() {

    companion object {
        fun newInstance() = ShotgunFragment()
    }

    private lateinit var viewModel: ShotgunViewModel

    private lateinit var stats: List<TextView>
    private lateinit var buttons: List<Button>
    private lateinit var ammo: List<List<Int>>

    private var enemyArmor = 0
    private var numberOfShots = 1
    private var numberOfPellets = 1
    private var hittingOn = 0
    private var damage = 0
    private val dice = 6

    private var currentAmmo = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_shotgun, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ShotgunViewModel::class.java)
        // TODO: Use the ViewModel

        stats = listOf(textView_precision, textView_armorPenetration,
            textView_damage, textView_numberShots)

        ammo = listOf(ammoList.GAUGE12LEAD().stats, ammoList.GAUGE12STEEL().stats,
            ammoList.GAUGE12SLUG().stats, ammoList.GAUGE12DRAGON().stats,
            ammoList.GAUGE12BIRDSHOT().stats)

        buttons = listOf(lead_button, steel_button, slug_button, dragon_button, birdshot_button)

        radioButton_short.isChecked = true

        for (x in buttons.indices){
            buttons[x].setBackgroundResource(android.R.drawable.btn_default)
        }

        onAmmoSelect(0)
        textView_ammunitionStatistics.text = "12 Gauge Lead Buckshot Statistics"

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

        lead_button.setOnClickListener {
            onAmmoSelect(0)
            textView_ammunitionStatistics.text = "12 Gauge Lead Buckshot Statistics"
        }

        steel_button.setOnClickListener {
            onAmmoSelect(1)
            textView_ammunitionStatistics.text = "12 Gauge Steel Buckshot Statistics"
        }

        slug_button.setOnClickListener {
            onAmmoSelect(2)
            textView_ammunitionStatistics.text = "12 Gauge Slug Statistics"
        }

        dragon_button.setOnClickListener {
            onAmmoSelect(3)
            textView_ammunitionStatistics.text = "Dragon's Breath Statistics"
        }

        birdshot_button.setOnClickListener {
            onAmmoSelect(4)
            textView_ammunitionStatistics.text = "12 Gauge Lead Birdshot Statistics"
        }

        radioButton_short.setOnCheckedChangeListener {
                buttonView, isChecked -> onAmmoSelect(currentAmmo); chanceToHit() }
        radioButton_medium.setOnCheckedChangeListener {
                buttonView, isChecked -> onAmmoSelect(currentAmmo); chanceToHit() }
        radioButton_long.setOnCheckedChangeListener {
                buttonView, isChecked -> onAmmoSelect(currentAmmo); chanceToHit() }
    }

    private fun onAmmoSelect (position: Int)
    {
        if (radioButton_short.isChecked)
        {
            textView_precision.text = ammo[position][0].toString()
            textView_armorPenetration.text = ammo[position][3].toString()
            textView_damage.text = ammo[position][6].toString()
            textView_numberShots.text = ammo[position][9].toString()
            damage = ammo[position][6]
            numberOfPellets = ammo[position][9]
        }
        if (radioButton_medium.isChecked)
        {
            textView_precision.text = ammo[position][1].toString()
            textView_armorPenetration.text = ammo[position][4].toString()
            textView_damage.text = ammo[position][7].toString()
            textView_numberShots.text = ammo[position][10].toString()
            damage = ammo[position][7]
            numberOfPellets = ammo[position][10]
        }
        if (radioButton_long.isChecked)
        {
            textView_precision.text = ammo[position][2].toString()
            textView_armorPenetration.text = ammo[position][5].toString()
            textView_damage.text = ammo[position][8].toString()
            textView_numberShots.text = ammo[position][11].toString()
            damage = ammo[position][8]
            numberOfPellets = ammo[position][11]
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
                textView_averageDamage.text = (damage * numberOfShots * numberOfPellets).toString()
            }
            else -> {
                val sum: Double = (damage.toDouble() * numberOfShots.toDouble() * numberOfPellets.toDouble()) * (dice + 1 - hittingOn.toDouble()) / dice
                textView_averageDamage.text = (sum.round(1)).toString()
            }
        }
    }

    private fun Double.round(decimals: Int): Double {
        var multiplier = 1.0
        repeat(decimals) { multiplier *= 10 }
        return kotlin.math.round(this * multiplier) / multiplier
    }

}
