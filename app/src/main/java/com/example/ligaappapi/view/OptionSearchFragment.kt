package com.example.ligaappapi.view


import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.ligaappapi.R
import com.example.ligaappapi.view.searchMatches.SearchMatchActivity
import com.example.ligaappapi.view.searchTeams.SearchTeamActivity
import kotlinx.android.synthetic.main.fragment_option_search.*
import org.jetbrains.anko.support.v4.startActivity

/**
 * A simple [Fragment] subclass.
 */
class OptionSearchFragment : DialogFragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_option_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_close.setOnClickListener {
            dismiss()
        }

        btn_select.setOnClickListener {
            val checkedRadioButtonId = rg_options.checkedRadioButtonId
            if (checkedRadioButtonId != -1){
                when (checkedRadioButtonId){
                    R.id.rb_leagues -> startActivity<SearchMatchActivity>()

                    R.id.rb_teams -> startActivity<SearchTeamActivity>()
                }

            }
        }
    }
}
