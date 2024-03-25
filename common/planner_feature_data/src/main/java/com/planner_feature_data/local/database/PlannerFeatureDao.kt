package com.planner_feature_data.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.planner_feature_data.local.models.PlanEntity

@Dao
interface PlannerFeatureDao {

    @Query("SELECT * FROM ${PlannerFeatureDatabaseContract.TABLE_NAME}")
    fun getAllPlans(): List<PlanEntity>

    @Insert
    fun insertNewPlan(planEntity: PlanEntity): Long

    @Query("DELETE FROM ${PlannerFeatureDatabaseContract.TABLE_NAME} WHERE ${PlannerFeatureDatabaseContract.ID} = :id")
    fun deleteById(id: Long)
}