package ru.vlyashuk.mapnotes.sql

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import ru.vlyashuk.mapnotes.db.MyDatabase

class AndroidDatabaseDriverFactory(private val context: Context) : DatabaseDriverFactory {
    override fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(MyDatabase.Schema, context, "launch.db")
    }
}