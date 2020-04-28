package com.rootstrap.android.utils

import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import java.io.IOException

object MockServer {

    private val MOCK_WEB_SERVER_PORT = 8000

    private var mockServer = MockWebServer()

    fun stopServer() {
        try {
            mockServer.shutdown()
            mockServer = MockWebServer()
        } catch (ignored: IOException) {
        }
    }

    fun startServer() {
        try {
            mockServer.start(MOCK_WEB_SERVER_PORT)
        } catch (ignored: IOException) {
        }
    }

    fun server(): MockWebServer {
        return mockServer
    }

    fun successfulResponse() = MockResponse()
        .setResponseCode(200)
        .addHeader("Content-Type", "application/json; charset=utf-8")
        .addHeader("Connection", "close")
        .addHeader("Cache-Control", "no-cache")
        .setBody("{ }")

    fun notFoundResponse() = MockResponse()
        .addHeader("Connection", "close")
        .setResponseCode(404)
        .setBody("{ }")

    fun unauthorizedResponse() = MockResponse()
        .addHeader("Connection", "close")
        .setResponseCode(401)
        .setBody("{ }")
}
