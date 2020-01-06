package com.example.aftershockrpgsystem.calculator.ui.main

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.aftershockrpgsystem.R
import com.example.aftershockrpgsystem.calculator.ui.fragments.handgun
import com.example.aftershockrpgsystem.calculator.ui.fragments.rifle
import com.example.aftershockrpgsystem.calculator.ui.fragments.shotgun

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
            0 -> handgun.newInstance("hg1","hg2")
            1 -> rifle.newInstance("rf1","rf2")
            2 -> shotgun.newInstance("sg1","sg2")
            else -> handgun.newInstance("else1", "else2")
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        return 3
    }
}