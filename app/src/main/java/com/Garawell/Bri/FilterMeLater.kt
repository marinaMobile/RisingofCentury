package com.Garawell.Bri

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.Garawell.Bri.ApppppCL.Companion.jsoupCheck
import com.Garawell.Bri.gamm.Gamm
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class FilterMeLater : AppCompatActivity() {
    lateinit var jsoup: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filter_me_later)
        jsoup = ""
        val asyncJs = AsyncJS(applicationContext)

        val job = GlobalScope.launch(Dispatchers.IO) {
            jsoup = asyncJs.coTask()
        }

        runBlocking {
            job.join()
            if (jsoup == jsoupCheck) {
                Intent(applicationContext, Gamm::class.java).also { startActivity(it) }
            } else {
                Intent(applicationContext, Weeeeeb::class.java).also { startActivity(it) }
            }
            finish()
        }
    }
}