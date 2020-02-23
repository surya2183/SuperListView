package com.example.myapplication


import io.reactivex.Observable

import retrofit2.http.GET
import retrofit2.http.Path

const  val BASE_URL:String = "https://dl.dropboxusercontent.com/s/2iodh4vg0eortkl/facts.json/"
   // "https://api.cryptonator.com/api/full/"

interface FactsService {

    @GET( "facatsurl")
    fun getCoinData(): Observable<Facts>






}