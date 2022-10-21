package com.Garawell.Bri

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.Garawell.Bri.ApppppCL.Companion.C1
import com.Garawell.Bri.ApppppCL.Companion.D1
import com.Garawell.Bri.ApppppCL.Companion.jsoupCheck
import com.Garawell.Bri.gamm.Gamm
import com.orhanobut.hawk.Hawk
import kotlinx.coroutines.*
import org.jsoup.Jsoup
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL

class FilterMeNow : AppCompatActivity() {
    lateinit var jsoup: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filter_me_now)
        jsoup = ""
        val asyncJs = AsyncJs(applicationContext)

        val job = GlobalScope.launch(Dispatchers.IO) {
            jsoup = asyncJs.coTask()
        }

        runBlocking {
                job.join()
                Log.d("jsoup status", jsoup)
                if (jsoup == jsoupCheck) {
                    Intent(applicationContext, Gamm::class.java).also { startActivity(it) }
                } else {
                    Intent(applicationContext, Weeeeeb::class.java).also { startActivity(it) }
                }
                finish()
        }
    }
}