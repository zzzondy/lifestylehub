package com.auth.data.authorization

import android.content.Context
import android.content.SharedPreferences
import com.auth.data.BuildConfig
import com.auth.data.authorization.database.AuthDatabase
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory

class AuthDatabaseManager(
    authSharedPreferences: SharedPreferences,
    context: Context,
) {

    var authDatabase: AuthDatabase

    init {
        val passphrase = authSharedPreferences.getString(PASSPHRASE, "")
        authDatabase = if (passphrase.isNullOrBlank()) {
            authSharedPreferences.edit()
                .putString(PASSPHRASE, BuildConfig.USER_PASSPHRASE)
                .apply()

            val factory =
                SupportFactory(SQLiteDatabase.getBytes(BuildConfig.USER_PASSPHRASE.toCharArray()))
            AuthDatabase.create(context).openHelperFactory(factory).build()
        } else {
            val factory =
                SupportFactory(SQLiteDatabase.getBytes(passphrase.toCharArray()))
            AuthDatabase.create(context).openHelperFactory(factory).build()
        }
    }

    companion object {

        private const val PASSPHRASE = "USER_PASSPHRASE"
    }
}