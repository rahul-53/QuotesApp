package com.rahul.quoteapp

import android.content.Context
import androidx.lifecycle.ViewModel
import com.google.gson.Gson


class MainViewModel( val context: Context):ViewModel() {
     var quoteList : Array<Quote> = emptyArray()
    private var index = 0

    init {
        quoteList = loadQuoteFromJSON()
    }

    private fun loadQuoteFromJSON():Array<Quote>{
        val inputStream = context.assets.open("quotes.json")
        val size:Int = inputStream.available()
        val buffer = ByteArray(size)

        inputStream.read(buffer)
        inputStream.close()

        val jsonData = String(buffer, Charsets.UTF_8)
        val gson =Gson()
        return gson.fromJson(jsonData, Array<Quote>::class.java)
    }


    fun getQuote() = quoteList[index]
    fun nextQuote() = quoteList[++index  % quoteList.size]
    fun prevQuote() = quoteList[(--index + quoteList.size) % quoteList.size]
}

