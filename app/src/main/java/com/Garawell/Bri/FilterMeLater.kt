package com.Garawell.Bri

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.Garawell.Bri.ApppppCL.Companion.C1
import com.Garawell.Bri.ApppppCL.Companion.jsoupCheck
import com.Garawell.Bri.ApppppCL.Companion.linkFilterPart1
import com.Garawell.Bri.ApppppCL.Companion.linkFilterPart2
import com.Garawell.Bri.ApppppCL.Companion.odone
import com.Garawell.Bri.gamm.Gamm
import kotlinx.coroutines.*
import java.net.HttpURLConnection
import java.net.URL

class FilterMeLater : AppCompatActivity() {
    lateinit var jsoup: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filter_me_later)
        jsoup = ""
        val job = GlobalScope.launch(Dispatchers.IO) {
            jsoup = coroutineTask()
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
            } catch (e: Exception) {

            }
        }

    }

    private suspend fun coroutineTask(): String {
        val sharPref = getSharedPreferences("SP", MODE_PRIVATE)
        val hawk: String? = sharPref.getString(C1, "null")
        val forJsoupSetNaming =
            "${linkFilterPart1}${linkFilterPart2}${odone}$hawk"

        withContext(Dispatchers.IO) {
                getCodeFromUrl(forJsoupSetNaming)
                Log.d("Check1C", forJsoupSetNaming)
        }
        return jsoup
    }

    private fun getCodeFromUrl(link: String) {
        val url = URL(link)
        val urlConnection = url.openConnection() as HttpURLConnection

        try {
            val text = urlConnection.inputStream.bufferedReader().readText()
            if (text.isNotEmpty()) {
                Log.d("jsoup status inside Url function", text)
                jsoup = text
            } else {
                Log.d("jsoup status inside Url function", "is null")
            }
        } catch (ex: Exception) {

        } finally {
            urlConnection.disconnect()
        }
    }
}