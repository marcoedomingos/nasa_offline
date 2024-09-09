package ao.marco.kotlin.nasaoffline.datasource.state

import ao.marco.kotlin.nasaoffline.model.IImageModel
import ao.marco.kotlin.nasaoffline.model.IPhotoModel

abstract class ImageState {
}

class ImageInitialState : ImageState() {}

class ImageLoadingState : ImageState() {}

class ImageSuccessState(val imageModel: IImageModel? = null, val photos: List<IPhotoModel>? = mutableListOf()) :
    ImageState() {}

class ImageFailState(val message: String) : ImageState() {}