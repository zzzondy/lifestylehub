package com.planner_feature_data.local.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.planner_feature_data.local.database.PlannerFeatureDatabaseContract
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Entity(
    tableName = PlannerFeatureDatabaseContract.TABLE_NAME,
    indices = [Index(PlannerFeatureDatabaseContract.ID)]
)
data class PlanEntity(
    @ColumnInfo(PlannerFeatureDatabaseContract.ID)
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    @ColumnInfo(PlannerFeatureDatabaseContract.NAME)
    val name: String,

    @ColumnInfo(PlannerFeatureDatabaseContract.DATE)
    val date: String,

    @ColumnInfo(PlannerFeatureDatabaseContract.NOTES)
    val notes: String?,

    @ColumnInfo(PlannerFeatureDatabaseContract.PLACE_ID)
    val placeId: String?,

    @ColumnInfo(PlannerFeatureDatabaseContract.PLACE_NAME)
    val placeName: String?,
)
