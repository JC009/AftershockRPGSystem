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

class HandgunFragment : Fragment() {

    companion object {
        fun newInstance() = HandgunFragment()
    }

    private lateinit var viewModel: HandgunViewModel

    private lateinit var stats: List<TextView>
    private lateinit var buttons: List<Button>
    private lateinit var ammo: List<List<Int>>

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

        for (x in stats.indices){
            stats[x].text = "0"
        }

        seekBarArmor.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                // Display the current progress of SeekBar
                textView_enemyArmor.text = "Enemy Armor: $i"
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
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })


        mm9_button.setOnClickListener {
            onAmmoSelect(0)
        }

        acp45_button.setOnClickListener {
            onAmmoSelect(1)
        }

        mm10_button.setOnClickListener {
            onAmmoSelect(2)
        }

        ae50_button.setOnClickListener {
            onAmmoSelect(3)
        }

        mag357_button.setOnClickListener {
            onAmmoSelect(4)
        }

        mag44_button.setOnClickListener {
            onAmmoSelect(5)
        }

        mag500_button.setOnClickListener {
            onAmmoSelect(6)
        }

        radioButton_short.setOnCheckedChangeListener { buttonView, isChecked -> onAmmoSelect(currentAmmo) }
        radioButton_medium.setOnCheckedChangeListener { buttonView, isChecked -> onAmmoSelect(currentAmmo) }
        radioButton_long.setOnCheckedChangeListener { buttonView, isChecked -> onAmmoSelect(currentAmmo) }
    }

    private fun onAmmoSelect (position: Int)
    {
        if (radioButton_short.isChecked)
        {
            textView_precision.text = ammo[position][0].toString()
            textView_armorPenetration.text = ammo[position][3].toString()
            textView_damage.text = ammo[position][6].toString()
        }
        if (radioButton_medium.isChecked)
        {
            textView_precision.text = ammo[position][1].toString()
            textView_armorPenetration.text = ammo[position][4].toString()
            textView_damage.text = ammo[position][7].toString()
        }
        if (radioButton_long.isChecked)
        {
            textView_precision.text = ammo[position][2].toString()
            textView_armorPenetration.text = ammo[position][5].toString()
            textView_damage.text = ammo[position][8].toString()
        }

        currentAmmo = position
    }
}
