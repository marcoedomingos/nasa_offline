package ao.marco.kotlin.nasaoffline.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ao.marco.kotlin.nasaoffline.dao.IImageDao
import ao.marco.kotlin.nasaoffline.dao.IPhotoDao
import ao.marco.kotlin.nasaoffline.model.ImageModel
import ao.marco.kotlin.nasaoffline.model.PhotoModel

@Database(entities = [ImageModel::class, PhotoModel::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun imageDao(): IImageDao
    abstract fun photoDao(): IPhotoDao
}