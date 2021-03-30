package com.app.marveltestapp.model.dataSourse.http

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {

    companion object {
        private const val TS = "1"
        private const val API_KEY = "06b7e0cd73ac86fe09d7dd7aa2cb31bb"
        private const val HASH = "b20b141e3443aa7f7bae2b6611f18d4e"
    }

    override fun intercept(chain: Interceptor.Chain): Response {

        var request = chain.request()
        val url = request.url().newBuilder()
            .addQueryParameter("ts", TS)
            .addQueryParameter("apikey", API_KEY)
            .addQueryParameter("hash", HASH)
            .build()

        request = request.newBuilder().url(url).build()
        return chain.proceed(request)
    }
}