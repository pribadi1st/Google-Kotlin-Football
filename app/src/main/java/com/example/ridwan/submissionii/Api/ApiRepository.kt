package com.example.ridwan.submissionii.Api

import java.net.URL

class ApiRepository{
    fun doRequest(url : String):String = URL(url).readText()
}