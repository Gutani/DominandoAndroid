package com.gutani.dominandooandroid

import android.app.SearchManager
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock
import android.provider.Settings
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import java.util.*

class IntentsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intents)
        val listView = ListView(this)
        setContentView(listView)

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, resources.getStringArray(R.array.intents_action_activity))
        listView.adapter = adapter
        listView.setOnItemClickListener { adapterView, view, i, l ->
            openIntentAtPosition(i)
        }
    }


    fun openIntentAtPosition(i:Int){
        val uri:Uri
        val intent:Intent
        when(i){
            0 -> {
                intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse("https://www.google.com")
                //if(intent.resolveActivity(packageManager) != null) startActivity(intent) else Toast.makeText(this, getString(R.string.error_intent), Toast.LENGTH_SHORT).show()
                startActivity(intent)
            }
            1 -> {
                if(ActivityCompat.checkSelfPermission(this, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CALL_PHONE), 0)
                }else{
                    callNumber()
                }
            }
            2 -> {
                uri = Uri.parse("geo:${10},${50}")
                intent = Intent(Intent.ACTION_VIEW, uri)
                startActivity(intent)
            }
            3 -> {
                intent = Intent(Intent.ACTION_VIEW).putExtra("sms_body","default content").setType("vnd.android-dir/mms-sms")
                startActivity(intent)
            }
            4 -> {
                intent = Intent().setAction(Intent.ACTION_SEND).putExtra(Intent.EXTRA_TEXT, "Compartilhando via Internet").setType("text/plain")
                startActivity(intent)
            }
            5 -> {
                startActivity(Intent(AlarmClock.ACTION_SET_ALARM).putExtra(AlarmClock.EXTRA_MESSAGE, "Value of The Message").putExtra(AlarmClock.EXTRA_HOUR, 19).putExtra(AlarmClock.EXTRA_MINUTES, 0))

            }
            6 -> {
                intent = Intent(Intent.ACTION_SEARCH).putExtra(SearchManager.QUERY, "Gustavo Antunes")
                startActivity(intent)
            }
            7 -> {
                intent = Intent(Settings.ACTION_SETTINGS)
                startActivity(intent)

            }
            8 -> {
                uri = Uri.parse("produto://iphone/novo")
                intent = Intent(Intent.ACTION_VIEW, uri)
                startActivity(intent)
            }
            else ->  finish()
        }
    }

   fun callNumber(){
       val uri = Uri.parse( "tel:62981512995")
       val intent = Intent(Intent.ACTION_CALL, uri)
       startActivity(intent)
   }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(grantResults.first() ==PackageManager.PERMISSION_GRANTED) callNumber()
    }


}