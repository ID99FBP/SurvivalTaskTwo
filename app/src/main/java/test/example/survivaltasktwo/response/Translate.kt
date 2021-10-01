package test.example.survivaltasktwo.response

import com.google.gson.annotations.SerializedName

data class Translate (
     @SerializedName("translatedText")val translatedText:String

)