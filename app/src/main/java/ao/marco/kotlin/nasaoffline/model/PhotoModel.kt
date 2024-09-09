package ao.marco.kotlin.nasaoffline.model

interface  IPhotoModel {
    val imgSrc: String?
    val fullName: String?
    val earthDate: String?
}

class PhotoModel(
    override val imgSrc: String?,
    override val fullName: String?,
    override val earthDate: String?
) : IPhotoModel {
    companion object {
        fun fromJson(map: Map<*, *>): IPhotoModel{
            return PhotoModel(
                imgSrc = map["img_src"].toString(),
                fullName = (map["camera"] as Map<*, *>)["full_name"].toString(),
                earthDate = map["earth_date"].toString(),
            );
        }
    }
}