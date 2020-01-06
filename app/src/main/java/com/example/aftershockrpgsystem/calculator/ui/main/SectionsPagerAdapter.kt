package com.example.aftershockrpgsystem.calculator.ui.main

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.aftershockrpgsystem.R
import com.example.aftershockrpgsystem.calculator.ui.fragments.Handgun
import com.example.aftershockrpgsystem.calculator.ui.fragments.Rifle
import com.example.aftershockrpgsystem.calculator.ui.fragments.Shotgun
import com.example.aftershockrpgsystem.calculator.ui.fragments.Test

private val TAB_TITLES = arrayOf(
    R.string.tab_text_1,
    R.string.tab_text_2,
    R.string.tab_text_3
)

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class SectionsPagerAdapter(private val context: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).

        return when (position){
            0 -> Handgun.newInstance("hg1","hg2")
            //1 -> Rifle.newInstance("rf1","rf2")
            //2 -> Shotgun.newInstance("sg1","sg2")
            1 -> Test()
            2 -> PlaceholderFragment()
            else -> Handgun.newInstance("else1", "else2")
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        return 3
    }
}