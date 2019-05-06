package com.rootstrap.android.infraestructure.interceptors

import android.content.Context
import com.rootstrap.android.App
import com.rootstrap.android.infraestructure.interceptors.HeadersKey.Companion.ACCESS_TOKEN
import com.rootstrap.android.infraestructure.interceptors.HeadersKey.Companion.CLIENT
import com.rootstrap.android.infraestructure.interceptors.HeadersKey.Companion.UID
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class  InterceptorResponse(private val context: Context) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())
        prefer(response)
        return response
    }

    private fun prefer(response: Response) {
        val accessToken = response.header(ACCESS_TOKEN)
        val client = response.header(CLIENT)
        val uid = response.header(UID)

        if (preferValid(accessToken, client, uid)) {
            App.mE.saveHeaderAuthenticationParams(accessToken, client, uid)
        }
    }

    private fun preferValid(accessToken: String?, client: String?, uid: String?): Boolean {
        return accessToken != null && accessToken.isNotEmpty() &&
                client != null && client.isNotEmpty() &&
                uid != null && uid.isNotEmpty()
    }
}
