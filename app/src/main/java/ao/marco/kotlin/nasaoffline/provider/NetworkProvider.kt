package ao.marco.kotlin.nasaoffline.provider

import ao.marco.kotlin.nasaoffline.BuildConfig
import ao.marco.kotlin.nasaoffline.model.INetworkResponseModel
import ao.marco.kotlin.nasaoffline.model.NetworkResponseModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import java.net.SocketException

interface INetworkProvider {
    suspend fun get(
        path: String,
        headers: Map<String, Any>?,
        query: Map<String, Any>?
    ): INetworkResponseModel
}

class NetworkProvider : INetworkProvider {
    private val client = OkHttpClient()
    override suspend fun get(
        path: String,
        headers: Map<String, Any>?,
        query: Map<String, Any>?
    ): INetworkResponseModel {
        try {
            return withContext(Dispatchers.IO) {
                client.newCall(executeRequest(path, headers = headers, query = query)).execute()
                    .use {
                        val gson = Gson()
                        val type = object : TypeToken<Map<String, Any>>() {}.type
                        val response: Map<String, Any> = gson.fromJson(it.body?.charStream(), type)
                        if (response.containsKey("error")) {
                            return@use NetworkResponseModel(message = (response["error"] as Map<*, *>)["message"].toString())
                        } else {
                            return@use NetworkResponseModel(body = response)
                        }
                    }
            }
        } catch (e: Exception) {
            return if (e is SocketException) {
                NetworkResponseModel(message = "Connection fail");
            } else {
                NetworkResponseModel(message = "Unknown Error");
            }
        }
    }

    private fun executeRequest(
        path: String,
        headers: Map<String, Any>?,
        query: Map<String, Any>?
    ): Request {
        var fullPath = "$path?"
        query?.map {
            fullPath += "${it.key}=${it.value}&"
        }
        var request = Request.Builder().url(BuildConfig.BASE_URL + fullPath)
        if (headers != null) {
            for (header in headers) {
                request = request.addHeader(header.key, header.value.toString())
            }
        }
        return request.build()
    }
}