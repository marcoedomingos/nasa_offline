package ao.marco.kotlin.nasaoffline.model

interface IImageModel {
    var url: String?;
    var hdUrl: String?;
    var title: String?;
    var date: String?;
    var explanation: String?;
}

class ImageModel(
    override var url: String?,
    override var hdUrl: String?,
    override var title: String?,
    override var date: String?,
    override var explanation: String?
) :IImageModel {
    companion object {
        fun fromJson(map: Map<String, Any>): IImageModel {
            return ImageModel(
                url = map["url"].toString(),
                hdUrl = map["hdurl"].toString(),
                title = map["title"].toString(),
                date = map["date"].toString(),
                explanation = map["explanation"].toString(),
            );
        }
    }
}