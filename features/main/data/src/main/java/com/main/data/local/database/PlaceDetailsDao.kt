package com.main.data.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.main.data.local.PlaceDetailsDatabaseContract
import com.main.data.local.entities.PlaceDetailsEntity
import com.main.domain.models.places.PlaceDetails

@Dao
interface PlaceDetailsDao {

    @Query("SELECT * FROM ${PlaceDetailsDatabaseContract.TABLE_NAME} WHERE ${PlaceDetailsDatabaseContract.ID} = :id")
    fun getPlaceDetailsById(id: String): PlaceDetailsEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPlaceDetails(placeDetails: PlaceDetailsEntity)
}