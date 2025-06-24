package ao.marco.kotlin.nasaoffline.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ImageModel(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @ColumnInfo( name = "url") var url: String?,
    @ColumnInfo( name = "hdUrl") var hdUrl: String?,
    @ColumnInfo( name = "title") var title: String?,
    @ColumnInfo( name = "date") var date: String?,
    @ColumnInfo( name = "explanation") var explanation: String?
) {
    companion object {
        fun fromJson(map: Map<String, Any>): ImageModel {
            return ImageModel(
                url = map["url"].toString(),
                hdUrl = map["hdurl"].toString(),
                title = map["title"].toString(),
                date = map["date"].toString(),
                explanation = map["explanation"].toString(),
            )
        }
    }
}