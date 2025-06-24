package ao.marco.kotlin.nasaoffline.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
class PhotoModel(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @ColumnInfo( name = "imgSrc") val imgSrc: String?,
    @ColumnInfo( name = "fullName") val fullName: String?,
    @ColumnInfo( name = "earthDate") val earthDate: String?
) {
    companion object {
        fun fromJson(map: Map<*, *>): PhotoModel{
            return PhotoModel(
                imgSrc = map["img_src"].toString(),
                fullName = (map["camera"] as Map<*, *>)["full_name"].toString(),
                earthDate = map["earth_date"].toString(),
            )
        }
    }
}