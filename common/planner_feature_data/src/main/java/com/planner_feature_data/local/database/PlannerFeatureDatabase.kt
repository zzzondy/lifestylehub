package com.planner_feature_data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.planner_feature_data.local.models.PlanEntity

@Database(
    entities = [PlanEntity::class],
    version = PlannerFeatureDatabaseContract.DATABASE_VERSION,
    exportSchema = false,
)
abstract class PlannerFeatureDatabase : RoomDatabase() {

    abstract val plannerFeatureDao: PlannerFeatureDao

    companion object {

        fun create(context: Context) = Room.databaseBuilder(
            context,
            PlannerFeatureDatabase::class.java,
            PlannerFeatureDatabaseContract.DATABASE_NAME
        ).build()
    }
}