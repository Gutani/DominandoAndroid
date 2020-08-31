package com.gutani.dominandooandroid

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    var state:String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val listView = ListView(this)
        setContentView(listView)
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, resources.getStringArray(R.array.intent_actions))
        listView.adapter = adapter
        listView.setOnItemClickListener { _, _, i, _ ->
            openIntentAtPosition(i)
        }
    }

    private fun openIntentAtPosition(position:Int){

        when(position){
            0 -> { //Orientation
                startActivity(Intent(this, OrientationActivity::class.java))
            }
            1 -> { //Activity Result
                val intent = Intent(this, ResultActivity::class.java)
                intent.putExtra(ResultActivity.EXTRA_RESULT, state)
                startActivityForResult(intent, REQUEST_STATE)
            }
            2 -> { //Testing TextView
                startActivity(Intent(this, TestTextViewActivity::class.java))
            }
            3 -> { //Intents Activity
                startActivity(Intent(this, IntentsActivity::class.java))
            }
            4 -> {

            }
            else -> finish()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK && requestCode == REQUEST_STATE){
            state = data?.getStringExtra(ResultActivity.EXTRA_RESULT)
            Toast.makeText(this, state.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(EXTRA_STATE, state)
    }

    companion object{
        private const val REQUEST_STATE = 1
        private const val EXTRA_STATE = "state"
    }
}