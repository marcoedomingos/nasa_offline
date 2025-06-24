package ao.marco.kotlin.nasaoffline.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import ao.marco.kotlin.nasaoffline.model.PhotoModel

@Dao
interface IPhotoDao {

    @Query("SELECT * FROM photoModel")
    suspend fun getPhotos():MutableList<PhotoModel>

    @Insert
    fun insert(vararg photo: PhotoModel)
}