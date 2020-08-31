package com.gutani.dominandooandroid

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AbsListView
import android.widget.ArrayAdapter
import android.widget.ListView

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val listView = ListView(this)
        setContentView(listView)
        val statesList = resources.getStringArray(R.array.states)
        val listAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_single_choice, statesList)
        listView.adapter = listAdapter
        listView.choiceMode = AbsListView.CHOICE_MODE_SINGLE

        val state = intent.getStringExtra(EXTRA_STATE)

        if(state != null){
            val position = statesList.indexOf(state)
            listView.setItemChecked(position, true)
        }

        listView.setOnItemClickListener { adapterView, _, i, _ ->
            val result = statesList[i]
            val it = intent
            intent.putExtra(EXTRA_RESULT, result)
            setResult(Activity.RESULT_OK, intent)
            finish()

        }
    }

    companion object{
        const val EXTRA_STATE = "state"
        const val EXTRA_RESULT = "result"
    }
}