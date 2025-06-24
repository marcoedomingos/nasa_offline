package ao.marco.kotlin.nasaoffline.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import ao.marco.kotlin.nasaoffline.model.ImageModel

@Dao
interface IImageDao {

    @Query("SELECT * FROM imageModel")
    suspend fun getImage(): ImageModel

    @Insert
    suspend fun insert(vararg image: ImageModel)
}