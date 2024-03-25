package com.auth.data.authorization.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.auth.data.authorization.AuthDatabaseContract
import com.auth.data.authorization.entities.UserEntity

@Database(
    entities = [UserEntity::class],
    version = AuthDatabaseContract.DATABASE_VERSION,
    exportSchema = false,
)
abstract class AuthDatabase : RoomDatabase() {

    abstract val authDatabaseDao: AuthDatabaseDao

    companion object {

        fun create(context: Context): Builder<AuthDatabase> = Room.databaseBuilder(
            context,
            AuthDatabase::class.java,
            AuthDatabaseContract.DATABASE_NAME
        ).fallbackToDestructiveMigration()
    }
}