package com.gutani.dominandooandroid

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val listView = ListView(this)
        setContentView(listView)
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, resources.getStringArray(R.array.intent_actions))
        listView.adapter = adapter
        listView.setOnItemClickListener { adapterView, view, i, l ->
            openIntentAtPosition(i)
        }
    }

    private fun openIntentAtPosition(position:Int){
        val uri:Uri
        val intent:Intent
        when(position){
            0 -> { //Orientation
                startActivity(Intent(this, OrientationActivity::class.java))
            }
            1 -> { //Activity Result

            }
            2 -> { //Testing TextView

            }
            3 -> { //Intents Activity
                startActivity(Intent(this, IntentsActivity::class.java))
            }
            else -> finish()
        }
    }
}