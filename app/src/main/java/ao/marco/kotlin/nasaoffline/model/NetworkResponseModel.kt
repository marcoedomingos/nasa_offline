package ao.marco.kotlin.nasaoffline.model

abstract class NetworkResponseModel(
    val message: String?,
    val body: Map<String, Any>?
)

class SuccessResponse(body: Map<String, Any>) : NetworkResponseModel(null, body)

class FailedResponse(message: String) : NetworkResponseModel(message, null)