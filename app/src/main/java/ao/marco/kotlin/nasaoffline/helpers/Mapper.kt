package ao.marco.kotlin.nasaoffline.helpers

abstract class Mapper<Response, Entity> {
    abstract fun map(data: Response): Entity
}
