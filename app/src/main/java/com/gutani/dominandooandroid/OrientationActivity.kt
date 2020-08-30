package com.gutani.dominandooandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_orientation.*

class OrientationActivity : AppCompatActivity() {
    private var names = arrayListOf<String>()
    private var adapter:ArrayAdapter<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_orientation)
        if(savedInstanceState != null){
            names = savedInstanceState.getStringArrayList("names_list") as ArrayList<String>
        }
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, names)
        listNames.adapter = adapter
    }

    fun btnAddClick(v:View){
        names.add(edtName.text.toString())
        edtName.text.clear()
        adapter?.notifyDataSetChanged()
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        outState.putStringArrayList("names_list", names)
    }
}