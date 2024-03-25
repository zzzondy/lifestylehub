package com.place_details.data.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.place_details.data.local.PlaceDetailsDatabaseContract
import com.place_details.data.local.entities.PlaceDetailsEntity

@Dao
interface PlaceDetailsDao {

    @Query("SELECT * FROM ${PlaceDetailsDatabaseContract.TABLE_NAME} WHERE ${PlaceDetailsDatabaseContract.ID} = :id")
    fun getPlaceDetailsById(id: String): PlaceDetailsEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPlaceDetails(placeDetails: PlaceDetailsEntity)
}