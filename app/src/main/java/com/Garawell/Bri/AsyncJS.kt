package com.Garawell.Bri

import android.content.Context
import android.util.Log
import com.Garawell.Bri.ApppppCL.Companion.C1
import com.Garawell.Bri.ApppppCL.Companion.linkFilterPart1
import com.Garawell.Bri.ApppppCL.Companion.linkFilterPart2
import com.Garawell.Bri.ApppppCL.Companion.odone
import com.orhanobut.hawk.Hawk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup

class AsyncJS(val context: Context) {
    private var jsoup: String = ""

    suspend fun coTask(): String {

        val nameParameter: String? = Hawk.get(C1)

        val taskName =
            "${linkFilterPart1}${linkFilterPart2}$odone}$nameParameter"

        withContext(Dispatchers.IO) {
                getDocSecretKey(taskName)
        }
        return jsoup
    }

    private fun getDocSecretKey(link: String) {
        val text = Jsoup.connect(link).get().text()
        Log.d("Text from jsoup", text)
        jsoup = text
    }
}