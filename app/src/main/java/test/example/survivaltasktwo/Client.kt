package test.example.survivaltasktwo

import com.google.gson.Gson
import okhttp3.FormBody
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import test.example.survivaltasktwo.response.Translate

object Client {
    private val okHttpClient = OkHttpClient()
    private val urlokhttp = "https://libretranslate.de/translate"
    private val gson = Gson()
      fun makerequest(q:String,source:String,target:String):Status<Translate>{
        val formBody=FormBody.Builder()
            .add("q",q)
            .add("source",source)
            .add("target",target).build()

    val request = Request.Builder().url("${urlokhttp}").post(formBody).build()
    val response = okHttpClient.newCall(request).execute()
       return if (response.isSuccessful) {
             val parserResponse = gson.fromJson(
              response.body?.string(),
                 Translate::class.java
            )
             Status.Success(parserResponse)
             } else {
                Status.Error(response.message)
                  }
}}








