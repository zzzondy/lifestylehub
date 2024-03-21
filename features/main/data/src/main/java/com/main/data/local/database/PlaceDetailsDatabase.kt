package com.main.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.main.data.local.PlaceDetailsDatabaseContract
import com.main.data.local.entities.PlaceDetailsEntity

@Database(
    entities = [PlaceDetailsEntity::class],
    version = PlaceDetailsDatabaseContract.DATABASE_VERSION,
    exportSchema = false
)
abstract class PlaceDetailsDatabase : RoomDatabase() {

    abstract val placeDetailsDao: PlaceDetailsDao

    companion object {

        fun create(context: Context) = Room.databaseBuilder(
            context,
            PlaceDetailsDatabase::class.java,
            PlaceDetailsDatabaseContract.DATABASE_NAME
        ).fallbackToDestructiveMigration().build()
    }

}