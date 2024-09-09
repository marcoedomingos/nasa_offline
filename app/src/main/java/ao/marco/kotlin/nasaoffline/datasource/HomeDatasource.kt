package ao.marco.kotlin.nasaoffline.datasource

import ao.marco.kotlin.nasaoffline.datasource.state.ImageFailState
import ao.marco.kotlin.nasaoffline.datasource.state.ImageInitialState
import ao.marco.kotlin.nasaoffline.datasource.state.ImageState
import ao.marco.kotlin.nasaoffline.datasource.state.ImageSuccessState
import ao.marco.kotlin.nasaoffline.model.IImageModel
import ao.marco.kotlin.nasaoffline.model.IPhotoModel
import ao.marco.kotlin.nasaoffline.model.ImageModel
import ao.marco.kotlin.nasaoffline.model.PhotoModel
import ao.marco.kotlin.nasaoffline.provider.INetworkProvider

class HomeDatasource(private var provider: INetworkProvider) {
    private val apiKey = ""

    suspend fun getImage(): ImageState {
        var state: ImageState = ImageInitialState()
        val json = provider.get(
            path = "/planetary/apod",
            headers = mapOf(),
            query = mapOf("api_key" to apiKey)
        )

        if (json.body != null) {
            json.body?.let {
                state = ImageSuccessState(imageModel = ImageModel.fromJson(it))
            }
        } else {
            json.message?.let {
                state = ImageFailState(message = it)
            }
        }
        return state;
    }

    suspend fun getPhotos(): ImageState {
        var state: ImageState = ImageInitialState()
        var list = mutableListOf<IPhotoModel>()
        val json = provider.get(
            path = "/mars-photos/api/v1/rovers/curiosity/photos",
            headers = mapOf(),
            query = mapOf("api_key" to apiKey, "sol" to "1000")
        )
        if (json.body != null) {
            json.body?.let { element ->
                println(element)
                (element["photos"] as List<*>).map {
                    (it as? Map<*, *>)?.let { map -> list.add(PhotoModel.fromJson(map)) }
                }
                state = ImageSuccessState(photos = list)
            }
        } else {
            json.message?.let {
                state = ImageFailState(message = it)
            }
        }
        return state
    }
}