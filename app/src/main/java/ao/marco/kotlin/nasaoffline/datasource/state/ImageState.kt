package ao.marco.kotlin.nasaoffline.datasource.state

import ao.marco.kotlin.nasaoffline.model.ImageModel
import ao.marco.kotlin.nasaoffline.model.PhotoModel

abstract class ImageState {
}

class ImageInitialState : ImageState() {}

class ImageLoadingState : ImageState() {}

class ImageSuccessState(val imageModel: ImageModel? = null, val photos: MutableList<PhotoModel> = mutableListOf()) :
    ImageState() {}

class ImageFailState(val message: String) : ImageState() {}