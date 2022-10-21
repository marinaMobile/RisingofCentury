package com.Garawell.Bri

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.Garawell.Bri.ApppppCL.Companion.jsoupCheck
import com.Garawell.Bri.gamm.Gamm
import kotlinx.coroutines.*
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL

class FilterMeNow : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filter_me_now)

        val sharPref = getSharedPreferences("SP", MODE_PRIVATE)
        val nameTest: String? = sharPref.getString(ApppppCL.C1, "null")
        val deepTest: String? = sharPref.getString(ApppppCL.D1, "null")
        if (nameTest!!.contains("tdb2")){
            Intent(this, Weeeeeb::class.java)
                .also { startActivity(it) }
            finish()
        } else if(deepTest!!.contains("tdb2")){
            Intent(this, Weeeeeb::class.java)
                .also { startActivity(it) }
            finish()
        } else{
            Intent(this, Gamm::class.java)
                .also { startActivity(it) }
            finish()
        }

    }
}