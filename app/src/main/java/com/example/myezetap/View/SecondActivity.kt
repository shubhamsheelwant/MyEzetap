package com.example.myezetap.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.example.myezetap.MainActivity.Companion.DATA
import com.example.myezetap.R

class SecondActivity : AppCompatActivity() {

    private var data: ArrayList<String>? = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        if (intent != null && intent.extras != null) {
            data = intent.extras!!.getStringArrayList(DATA)
        }
        initView()
    }

    private fun initView() {
        val parentLayout = LinearLayout(this)
        parentLayout.orientation = LinearLayout.VERTICAL
        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        for (i in 0 until data!!.size) {
            val nameTextView = TextView(this)
            nameTextView.layoutParams = params
            nameTextView.setText(data?.get(i))
            parentLayout.addView(nameTextView)
        }
        (parentLayout.parent as? ViewGroup)?.removeView(parentLayout)
        addContentView(parentLayout, params)
    }
}