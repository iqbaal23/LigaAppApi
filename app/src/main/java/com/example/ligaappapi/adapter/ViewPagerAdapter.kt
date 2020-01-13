package com.example.ligaappapi.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class ViewPagerAdapter(fragmentManager: FragmentManager?) : FragmentPagerAdapter(fragmentManager){
    private var fragmentList = arrayListOf<Fragment>()
    private var titleList = arrayListOf<String>()

    fun populateFragment(fragment: Fragment, title: String){
        fragmentList.add(fragment)
        titleList.add(title)
    }

    override fun getItem(posisition: Int) = fragmentList[posisition]

    override fun getCount() = fragmentList.size

    override fun getPageTitle(position: Int) = titleList[position]
}