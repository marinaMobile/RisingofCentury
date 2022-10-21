package com.Garawell.Bri

import android.content.Context
import android.util.Log
import com.Garawell.Bri.ApppppCL.Companion.C1
import com.Garawell.Bri.ApppppCL.Companion.D1
import com.orhanobut.hawk.Hawk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup

class AsyncJs (val context: Context) {
    private var jsoup: String = ""

    suspend fun coTask(): String {

        val nameParameter: String? = Hawk.get(C1)
        val appLinkParameter: String? = Hawk.get(D1)


        val taskName = "${ApppppCL.linkFilterPart1}${ApppppCL.linkFilterPart2}${ApppppCL.odone}$nameParameter"
        val taskLink = "${ApppppCL.linkFilterPart1}${ApppppCL.linkFilterPart2}${ApppppCL.odone}$appLinkParameter"

        withContext(Dispatchers.IO) {
            //changed logical null to string null
            if (nameParameter != "null") {
                getDocSecretKey(taskName)
                Log.d("Check1C", taskName)
            } else {
                getDocSecretKey(taskLink)
                Log.d("Check1C", taskLink)
            }
        }
        return jsoup
    }

    private fun getDocSecretKey(link: String) {
            val text = Jsoup.connect(link).get().text()
            Log.d("Text from jsoup", text)
            jsoup = text
    }
}