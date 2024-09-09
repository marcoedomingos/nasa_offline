package ao.marco.kotlin.nasaoffline.model

interface INetworkResponseModel {
    val message: String?
    val body: Map<String, Any>?
}

class NetworkResponseModel(
    override val message: String? = null,
    override val body: Map<String, Any>? = null,
) :
    INetworkResponseModel {
}