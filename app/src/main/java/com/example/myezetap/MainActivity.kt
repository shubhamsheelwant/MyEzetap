package com.example.myezetap

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.myezetap.Model.CustomUiResponse
import com.example.myezetap.NetworkModule.Api
import com.example.myezetap.NetworkModule.RetrofitClientInstance.retrofitInstance
import com.example.myezetap.View.SecondActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var myAPIService: Api
    private var editTextList: ArrayList<EditText>? = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        myAPIService = retrofitInstance!!.create(Api::class.java)
        callCustomUiNetworkCall()
    }

    private fun callCustomUiNetworkCall() {
        val call: Call<CustomUiResponse?> = myAPIService.fetchCustomUI()
        call.enqueue(object : Callback<CustomUiResponse?> {
            override fun onResponse(
                call: Call<CustomUiResponse?>?,
                response: Response<CustomUiResponse?>
            ) {
                renderUI(response.body())
            }

            override fun onFailure(call: Call<CustomUiResponse?>, throwable: Throwable) {
                Toast.makeText(this@MainActivity, throwable.message, Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun renderUI(response: CustomUiResponse?) {
        val parentLayout = LinearLayout(this)
        parentLayout.orientation = LinearLayout.VERTICAL
        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        if (response != null) {
            for (i in 0..response.uidata.size - 1) {
                when (response.uidata[i].uitype) {
                    resources.getString(R.string.label) -> {
                        val label = TextView(this)
                        label.text = response.uidata.get(i).value
                        label.layoutParams = params
                        parentLayout.addView(label)
                    }
                    resources.getString(R.string.edittext)  -> {
                        val label = EditText(this)
                        label.hint = response.uidata.get(i).hint
                        label.layoutParams = params
                        parentLayout.addView(label)
                        editTextList?.add(label)
                    }
                    resources.getString(R.string.button)  -> {
                        val label = Button(this)
                        label.text = response.uidata.get(i).value
                        label.layoutParams = params
                        parentLayout.addView(label)
                        label.setOnClickListener(this)
                    }
                }
                (parentLayout.parent as? ViewGroup)?.removeView(parentLayout)
                addContentView(parentLayout, params)
            }
        }
    }

    override fun onClick(v: View?) {
        val data: ArrayList<String> = ArrayList()
        for (i in 0..editTextList!!.size - 1) {
            data.add(editTextList!![i].text.toString())
        }
        val intent = Intent(this, SecondActivity::class.java)
        intent.putStringArrayListExtra(DATA, data)
        startActivity(intent)
        finish()
    }

    companion object{
        const val DATA: String = "Data"
    }

}