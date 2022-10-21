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
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL

class FilterMeNow : AppCompatActivity() {
    lateinit var jsoup: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filter_me_now)
        jsoup = ""

        val job = GlobalScope.launch(Dispatchers.IO) {
            jsoup = coTask()
            Log.d("jsoup status from global scope", jsoup)
        }

        runBlocking {
            try {
                job.join()

                Log.d("jsoup status out of global scope", jsoup)


                if (jsoup == jsoupCheck) {
                    Intent(applicationContext, Gamm::class.java).also { startActivity(it) }
                } else {
                    Intent(applicationContext, Weeeeeb::class.java).also { startActivity(it) }
                }
                finish()
            } catch (_: Exception) {

            }
        }
    }

    private suspend fun coTask(): String {

        val nameParameter: String? = Hawk.get(C1)
        val appLinkParameter: String? = Hawk.get(D1)


        val taskName = "${ApppppCL.linkFilterPart1}${ApppppCL.linkFilterPart2}${ApppppCL.odone}$nameParameter"
        val taskLink = "${ApppppCL.linkFilterPart1}${ApppppCL.linkFilterPart2}${ApppppCL.odone}$appLinkParameter"

        withContext(Dispatchers.IO) {
            //changed logical null to string null
            if (nameParameter != "null") {
                getTheCypher(taskName)
                Log.d("Check1C", taskName)
            } else {
                getTheCypher(taskLink)
                Log.d("Check1C", taskLink)
            }
        }
        return jsoup
    }
    private fun getTheCypher(link: String) {
        val url = URL(link)
        val urlConnection = url.openConnection() as HttpURLConnection

        try {
            val text = urlConnection.inputStream.bufferedReader().readText()
            if (text.isNotEmpty()) {
                jsoup = text
            }
        }  finally {
            urlConnection.disconnect()
        }
    }
}